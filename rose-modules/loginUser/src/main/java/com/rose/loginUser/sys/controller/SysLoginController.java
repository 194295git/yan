/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.rose.loginUser.sys.controller;


import com.google.gson.Gson;
import com.rose.common.base.GenericResponse;
import com.rose.common.base.R;
import com.rose.common.base.ServiceError;
import com.rose.common.constant.RedisPrefix;
import com.rose.common.utils.CommonUser;
import com.rose.common.utils.JwtTokenUtil;
import com.rose.loginUser.sys.entity.SysUserEntity;
import com.rose.loginUser.sys.feign.FirstLoginFeign;
import com.rose.loginUser.sys.form.SysLoginForm;
import com.rose.loginUser.sys.form.SysRegisterForm;
import com.rose.loginUser.sys.service.ShiroService;
import com.rose.loginUser.sys.service.SysCaptchaService;
import com.rose.loginUser.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Set;

/**
 * 登录相关
 *
 * @author Mark 1942951600@qq.com
 */
@RestController
@Api(tags = "登录相关接口" , description = "LoginController | 登录模块")
public class SysLoginController extends AbstractController {

	@Autowired
	private SysUserService sysUserService;


	@Autowired
	private SysCaptchaService sysCaptchaService;

	@Autowired
	private FirstLoginFeign firstLoginFeign;

	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private ShiroService shiroService;

	@ApiOperation("shiro的登录方法【废弃】")
	@RequestMapping(value = "/shrio/login", method = RequestMethod.POST)
	public GenericResponse login(@RequestBody SysRegisterForm user) {
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			return GenericResponse.response(ServiceError.NORMAL);
		} catch (Exception e) {
//			e.printStackTrace();
			return GenericResponse.response(ServiceError.LOGIN_ERROR);
		}
	}


	/**
	 * 很明显这块设计到事务了；要么就写成保存sys_user失败了然后就不调用后面的方法这样子；
	 * 看起来也还不错；应该还用不到分布式事务。
	 *
	 * @param dto
	 * @return
	 * @throws Exception
	 */
//	@ApiOperation("使用邮箱和密码注册")
////	@PostMapping("/sys/registByWeb")
////	public GenericResponse registByWeb(@RequestBody SysRegisterForm dto) throws Exception {
//////		String uuid = UUID.randomUUID().toString() +new Random().nextInt();
//////		SysUserPermission sysUserEntity = new SysUserPermission();
//////		sysUserEntity.setPassword(dto.getPassword());
//////		sysUserEntity.setUsername(dto.getUsername());
//////		sysUserEntity.setOpenid(uuid);
//////		sysUserService.saveUser(sysUserEntity);
//////		//还需要调用first服务，给器存入一个openid来唯一关联；目前感觉这样设计合适。可以说兼容两套系统
//////		//2023-1-22得出结论这块是需要待优化的。先更新一下数据库吧。然后区分角色和功能。
//////		//最起码这些角色隶属于普通基本模块的角色。
//////		RegisterFeign registerFeign = new RegisterFeign();
//////		registerFeign.setOpenid(uuid);
//////		registerFeign.setUsername(dto.getUsername());
//////		firstLoginFeign.registByOpenid(registerFeign);
//////		return GenericResponse.response(ServiceError.NORMAL);
////	}

	/**
	 * 验证码
	 */
	@ApiOperation("获取验证码")
	@GetMapping("captcha.jpg")
	public void captcha(HttpServletResponse response, String uuid)throws IOException {
		response.setHeader("Cache-Control", "no-store, no-cache");
		response.setContentType("image/jpeg");

		//获取图片验证码
		BufferedImage image = sysCaptchaService.getCaptcha(uuid);

		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);
		IOUtils.closeQuietly(out);
	}

	/**
	 * 登录
	 */
	@ApiOperation("shiro的登录方法【启用】")
	@PostMapping("/sys/login")
	public GenericResponse login(@RequestBody SysLoginForm form)throws IOException {
//		boolean captcha = sysCaptchaService.validate(form.getUuid(), form.getCaptcha());
//		if(!captcha){
//			return R.error("验证码不正确");
//		}

		//用户信息
		SysUserEntity user = sysUserService.queryByUserName(form.getUsername());
		CommonUser commonUser = new CommonUser();
		commonUser.setOpenid(user.getOpenid());
		commonUser.setUserId(user.getUserId());
		//账号不存在、密码错误
		if(user == null || !user.getPassword().equals(new Sha256Hash(form.getPassword(), user.getSalt()).toHex())) {
			return GenericResponse.response(ServiceError.LOGIN_ERROR_USERNAME);

		}
		String token = "";
		try {
			token = JwtTokenUtil.generateToken(commonUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		//账号锁定
//		if(user.getStatus() == 0){
//			return GenericResponse.response(ServiceError.LOGIN_ERROR_HASLOCKED);
//		}
		Set<String> permsSet = shiroService.getUserPermissions(user.getUserId());
		Gson gson = new Gson();
//		redisTemplate.opsForValue().set(user.getUserId().toString(), gson.toJson(permsSet));
		try{
			redisTemplate.opsForValue().set(RedisPrefix.SHIROPERFIX +user.getOpenid(), gson.toJson(permsSet));
		}catch(Exception e){
			throw e;
		}

		return GenericResponse.response(ServiceError.NORMAL, token);
		//生成token，并保存到数据库
//		R r = sysUserTokenService.createToken(user.getUserId());
//		return r;
	}


	/**
	 * 退出
	 * 1.需要将redis 里面的信息给清空了。
	 * 2.
	 */
	@ApiOperation("shiro的登出【有问题】")
	@PostMapping("/sys/logout")
	public R logout() {
//		sysUserTokenService.logout(getUserId());
		return R.ok();

	}
	
}
