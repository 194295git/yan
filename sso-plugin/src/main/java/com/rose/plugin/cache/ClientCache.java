package com.rose.plugin.cache;

/**
 * sso客户端缓存
 *
 * @author fengshuonan
 * @date 2018-02-04 14:17
 */
public interface ClientCache {

    /**
     * 判断是否存在失效的token
     */
    boolean containsInvalidKey(String key);

    /**
     * 添加失效的缓存
     */
    void addInvalidKey(String key);

    /**
     * 删除失效的缓存
     */
    void removeInvalidKey(String key);
}
