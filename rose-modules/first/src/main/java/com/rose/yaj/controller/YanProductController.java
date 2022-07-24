package com.rose.yaj.controller;

import com.rose.yaj.common.Constants;
import com.rose.yaj.common.GenericResponse;
import com.rose.yaj.common.ServiceError;
import com.rose.yaj.entity.YanProduct;
import com.rose.yaj.exeptionHandler.RRException;
import com.rose.yaj.service.YanProductService;
import com.rose.yaj.util.PageQueryUtil;
import com.rose.yaj.util.PageResult;
import com.rose.yaj.util.Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author rose
 * @create 2022/6/18
 */
@Slf4j
@RestController
@RequestMapping("/yaj/yan-product")
@Api(tags = "产品相关接口" , description = "YanProductController | 产品模块")
public class YanProductController {

    @Autowired
    private YanProductService yanProductService;


    @GetMapping("/goods/detail/{goodsId}")
    @ApiOperation(value = "商品详情接口", notes = "传参为商品id")
    public GenericResponse goodsDetail(    @ApiParam(value = "商品id") @PathVariable("goodsId") Long goodsId) {

        log.info("goods detail api,goodsId={}", goodsId );
        //参数出现问题
        if (goodsId < 1) {
            return GenericResponse.response(ServiceError.PARAM_EXCEPTION);

        }
        YanProduct goods = yanProductService.getProductById(goodsId);
        //查询出现异常
        if (Constants.SELL_STATUS_UP != goods.getStatus()) {
            throw new RRException(ServiceError.GOODS_PUT_DOWN);
        }

        return GenericResponse.response(ServiceError.NORMAL,goods);
    }



    @GetMapping("/search")
    @ApiOperation(value = "商品搜索接口", notes = "根据关键字和分类id进行搜索")
    public GenericResponse search(@RequestParam(required = false) @ApiParam(value = "搜索关键字") String keyword,
                                                                    @RequestParam(required = false) @ApiParam(value = "分类id") Long goodsCategoryId,
                                                                    @RequestParam(required = false) @ApiParam(value = "orderBy") String orderBy,
                                                                    @RequestParam(required = false) @ApiParam(value = "页码") Integer pageNumber
                                                               ) {

        log.info("goods search api,keyword={},goodsCategoryId={},orderBy={},pageNumber={}", keyword, goodsCategoryId, orderBy, pageNumber);

        Map params = new HashMap(8);
        //两个搜索参数都为空，直接返回异常
//        if (goodsCategoryId == null && StringUtils.isEmpty(keyword)) {
//            throw new RRException(ServiceError.PARAM_EXCEPTION);
//        }
        if (pageNumber == null || pageNumber < 1) {
            pageNumber = 1;
        }
        params.put("goodsCategoryId", goodsCategoryId);
        params.put("page", pageNumber);
        params.put("limit", Constants.GOODS_SEARCH_PAGE_LIMIT);

        //对keyword做过滤 去掉空格
        if (!StringUtils.isEmpty(keyword)) {
            params.put("keyword", keyword);
        }
        if (!StringUtils.isEmpty(orderBy)) {
            params.put("orderBy", orderBy);
        }
        //搜索上架状态下的商品
        params.put("goodsSellStatus", Constants.SELL_STATUS_UP);
        //封装商品数据
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        PageResult res =yanProductService.searchProducts(pageUtil);
        return GenericResponse.response(ServiceError.NORMAL,res);
    }
}
