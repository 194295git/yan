package com.rose.yaj.controller;

import java.util.Arrays;
import java.util.Map;

import com.baomidou.mybatisplus.extension.api.R;
import com.rose.yaj.entity.YanUserAddressEntity;
import com.rose.yaj.service.YanUserAddressService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;




/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2022-06-21 12:52:33
 */
@RestController
@RequestMapping("generator/yanuseraddress")
public class YanUserAddressController {
    @Autowired
    private YanUserAddressService yanUserAddressService;

//    /**
//     * 列表
//     */
//    @RequestMapping("/list")
//    @RequiresPermissions("generator:yanuseraddress:list")
//    public R list(@RequestParam Map<String, Object> params){
//        PageUtils page = yanUserAddressService.queryPage(params);
//
//        return R.ok().put("page", page);
//    }
//
//
//    /**
//     * 信息
//     */
//    @RequestMapping("/info/{addressId}")
//    @RequiresPermissions("generator:yanuseraddress:info")
//    public R info(@PathVariable("addressId") Long addressId){
//		YanUserAddressEntity yanUserAddress = yanUserAddressService.getById(addressId);
//
//        return R.ok().put("yanUserAddress", yanUserAddress);
//    }
//
//    /**
//     * 保存
//     */
//    @RequestMapping("/save")
//    @RequiresPermissions("generator:yanuseraddress:save")
//    public R save(@RequestBody YanUserAddressEntity yanUserAddress){
//		yanUserAddressService.save(yanUserAddress);
//
//        return R.ok();
//    }
//
//    /**
//     * 修改
//     */
//    @RequestMapping("/update")
//    @RequiresPermissions("generator:yanuseraddress:update")
//    public R update(@RequestBody YanUserAddressEntity yanUserAddress){
//		yanUserAddressService.updateById(yanUserAddress);
//
//        return R.ok();
//    }
//
//    /**
//     * 删除
//     */
//    @RequestMapping("/delete")
//    @RequiresPermissions("generator:yanuseraddress:delete")
//    public R delete(@RequestBody Long[] addressIds){
//		yanUserAddressService.removeByIds(Arrays.asList(addressIds));
//
//        return R.ok();
//    }

}
