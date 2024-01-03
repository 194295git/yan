package com.rose.loginUser.sys.controller;

import com.rose.common.base.GenericResponse;
import com.rose.common.base.ServiceError;
import com.rose.loginUser.sys.serviceleaf.SegmentService;
import com.sankuai.inf.leaf.common.Result;
import com.sankuai.inf.leaf.common.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class LeafController {
    private Logger logger = LoggerFactory.getLogger(LeafController.class);

    @Autowired
    private SegmentService segmentService;


    @RequestMapping(value = "/api/segment/get/{key}")
    public GenericResponse getSegmentId(@PathVariable("key") String key) {
        return GenericResponse.response(ServiceError.NORMAL, get(key, segmentService.getId(key)));
    }



    private String get(@PathVariable("key") String key, Result id) {
        Result result;
        if (key == null || key.isEmpty()) {
//            throw new NoKeyException();

        }
        result = id;
        if (result.getStatus().equals(Status.EXCEPTION)) {
//            throw new LeafServerException(result.toString());
        }
        return String.valueOf(result.getId());
    }
}
