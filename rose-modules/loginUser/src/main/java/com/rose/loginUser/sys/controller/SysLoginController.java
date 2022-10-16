/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.rose.loginUser.sys.controller;


import com.rose.common.base.GenericResponse;
import com.rose.common.base.ServiceError;
import com.rose.common.utils.CommonUser;
import com.rose.common.utils.JwtTokenUtil;
import com.rose.loginUser.common.utils.R;
import com.rose.loginUser.sys.entity.SysUserEntity;
import com.rose.loginUser.sys.feign.FirstLoginFeign;
import com.rose.loginUser.sys.feign.dto.RegisterFeign;
import com.rose.loginUser.sys.form.SysLoginForm;
import com.rose.loginUser.sys.form.SysRegisterForm;
import com.rose.loginUser.sys.service.SysCaptchaService;
import com.rose.loginUser.sys.service.SysUserService;
import com.rose.loginUser.sys.service.SysUserTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

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
	private SysUserTokenService sysUserTokenService;

	@Autowired
	private SysCaptchaService sysCaptchaService;

	@Autowired
	private FirstLoginFeign firstLoginFeign;

	/**
	 * 很明显这块设计到事务了；要么就写成保存sys_user失败了然后就不调用后面的方法这样子；
	 * 看起来也还不错；应该还用不到分布式事务。
	 *
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@ApiOperation("使用邮箱和密码注册")
	@PostMapping("/sys/registByWeb")
	public GenericResponse registByWeb(@RequestBody SysRegisterForm dto) throws Exception {
		String uuid = UUID.randomUUID().toString() +new Random().nextInt();
		SysUserEntity sysUserEntity = new SysUserEntity();
		sysUserEntity.setPassword(dto.getPassword());
		sysUserEntity.setUsername(dto.getUsername());
		sysUserEntity.setOpenid(uuid);
		sysUserService.saveUser(sysUserEntity);
		//还需要调用first服务，给器存入一个openid来唯一关联；目前感觉这样设计合适。可以说兼容两套系统
		RegisterFeign registerFeign = new RegisterFeign();
		registerFeign.setOpenid(uuid);
		registerFeign.setUsername(dto.getUsername());
		firstLoginFeign.registByOpenid(registerFeign);
		return GenericResponse.response(ServiceError.NORMAL);
	}

	/**
	 * 验证码
	 */
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
		//账号锁定
		if(user.getStatus() == 0){
			return GenericResponse.response(ServiceError.LOGIN_ERROR_HASLOCKED);
		}
		return GenericResponse.response(ServiceError.NORMAL, token);
		//生成token，并保存到数据库
//		R r = sysUserTokenService.createToken(user.getUserId());
//		return r;
	}


	/**
	 * 退出
	 */
	@PostMapping("/sys/logout")
	public R logout() {
		sysUserTokenService.logout(getUserId());
		return R.ok();
	}
	
}
