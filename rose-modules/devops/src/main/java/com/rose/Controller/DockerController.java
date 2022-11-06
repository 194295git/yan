package com.rose.Controller;

import com.github.dockerjava.api.DockerClient;
import com.rose.Constant.ContainerId;
import com.rose.common.base.GenericResponse;
import com.rose.common.base.ServiceError;
import com.rose.util.DockerProtocol;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * @author rose
 * @create 2022/11/5
 */
@Slf4j
@RestController
@RequestMapping("/dev")
public class DockerController {



    /**
     * 启动容器.
     * @return
     */
    @GetMapping("/containerStart")
    public GenericResponse containerStart(@RequestParam("type") String file) {
        try {
            DockerProtocol dockerProtocol = new DockerProtocol();
            DockerClient dockerClient = dockerProtocol.connectDocker();
            if(ContainerId.RABBITMQ.equals(file)){
                dockerProtocol.startContainer(dockerClient, ContainerId.RABBIT_ID);
            }
            if(ContainerId.REDIS.equals(file)){
                dockerProtocol.startContainer(dockerClient, ContainerId.REDIS_ID);
            }
            return GenericResponse.response(ServiceError.NORMAL);
        }catch(Exception e){
            return GenericResponse.response(ServiceError.DevopsError);
        }


    }
    @GetMapping("/containerStop")
    public GenericResponse containerStop(@RequestParam("type") String file) {
        try {
            DockerProtocol dockerProtocol = new DockerProtocol();
            DockerClient dockerClient = dockerProtocol.connectDocker();
            if(ContainerId.RABBITMQ.equals(file)){
                dockerProtocol.stopContainer(dockerClient, ContainerId.RABBIT_ID);
            }
            if(ContainerId.REDIS.equals(file)){
                dockerProtocol.stopContainer(dockerClient, ContainerId.REDIS_ID);
            }
            return GenericResponse.response(ServiceError.NORMAL);
        }catch(Exception e){
            return GenericResponse.response(ServiceError.DevopsError);
        }


    }


}
