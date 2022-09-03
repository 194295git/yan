package com.rose.plugin.util;

import com.rose.plugin.constants.SsoConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;


/**
 * http工具类
 *
 * @author fengshuonan
 * @date 2018-02-03 21:06
 */
public class HttpUtil {

    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    /**
     * 编码url
     */
    public static String encodeUrl(String url) {
        try {
            return URLEncoder.encode(url, SsoConstants.URL_ENCODE_CHARSET);
        } catch (UnsupportedEncodingException e) {
            logger.error("编码url出错!", e);
            return null;
        }
    }

    /**
     * 解码url
     */
    public static String decodeURL(String url) {
        try {
            return URLDecoder.decode(url, SsoConstants.URL_ENCODE_CHARSET);
        } catch (UnsupportedEncodingException e) {
            logger.error("解码url出错!", e);
            return null;
        }
    }

    /**
     * 获取请求完整路径
     */
    public static String getRequestFullPath(HttpServletRequest request) {
        String result = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getRequestURI();
        String queryurl = request.getQueryString();
        if (null != queryurl) {
            result += "?" + queryurl;
        }
        return result;
    }

    /**
     * 获取请求完整路径(不带参数)
     */
    public static String getRequestFullPathNoParam(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getRequestURI();
    }

    /**
     * 获取请求路径(具体到context)
     */
    public static String getRequestContextPath(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

}
