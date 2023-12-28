package com.rose.loginUser.sys.serviceleaf.impl;/**
 * @author rose
 * @create 2023/12/28
 */

        import com.rose.loginUser.sys.serviceleaf.IDGen;
        import com.rose.loginUser.sys.serviceleaf.SegmentService;
        import com.sankuai.inf.leaf.common.Result;
        import lombok.extern.slf4j.Slf4j;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

/**
 * @author rose<br>
 */
@Slf4j
@Service
public class SegmentServiceImpl implements SegmentService {


    private IDGen idGen;
    @Autowired
    public SegmentServiceImpl(IDGen idGen) throws Exception {
        this.idGen = idGen;

        if (this.idGen.init()) {
//                LOG.info("Segment Service Init Successfully");
        } else {
            throw new Exception("Segment Service Init Fail");
        }

    }
    @Override
    public Result getId(String key) {
        return idGen.get(key);
    }
}
