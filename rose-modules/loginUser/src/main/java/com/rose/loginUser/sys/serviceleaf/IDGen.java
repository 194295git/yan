package com.rose.loginUser.sys.serviceleaf;

import com.sankuai.inf.leaf.common.Result;

public interface IDGen {
    Result get(String key);
    boolean init();
}
