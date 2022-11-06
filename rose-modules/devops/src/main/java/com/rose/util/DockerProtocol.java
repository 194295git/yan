package com.rose.util;

import com.alibaba.fastjson.JSONObject;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.LoadImageCmd;
import com.github.dockerjava.api.command.PullImageCmd;
import com.github.dockerjava.api.model.*;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.okhttp.OkDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author rose
 * @create 2022/11/5
 */
@Component
public class DockerProtocol{


    public DockerClient connectDocker(){
        DockerClient dockerClient = DockerClientBuilder.getInstance("tcp://192.168.56.20:2375").build();
        Info info = dockerClient.infoCmd().exec();
        String infoStr = JSONObject.toJSONString(info);
        System.out.println("docker的环境信息如下：=================");
        System.out.println(info);
        return dockerClient;
    }

    //开始容器
    public void startContainer(DockerClient client,String containerId){
        client.startContainerCmd(containerId).exec();
    }
    //停止容器
    public void stopContainer(DockerClient client,String containerId){
        client.stopContainerCmd(containerId).exec();
    }
    //加载镜像
    public LoadImageCmd loadImage(DockerClient client, String filePath){
        LoadImageCmd loadImageCmd = null;
        try {
            loadImageCmd = client.loadImageCmd(new FileInputStream(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return loadImageCmd;
    }

    /**
     * repository 镜像名称:tag名称
     **/
    public PullImageCmd pullImage(DockerClient client, String repository){
        PullImageCmd pullImageCmd = client.pullImageCmd(repository);
        return pullImageCmd;
    }


    //路径挂载
//    public CreateContainerResponse createContainers(DockerClient client, String containerName, String imageName){
//        HostConfig hostConfig = newHostConfig();
//        Bind bind = new Bind("服务器路径",new Volume("容器路径"));
//        hostConfig.setBinds(bind);
//        CreateContainerResponse container = client.createContainerCmd(imageName)
//                .withName(containerName)
//                .withHostConfig(hostConfig)
//                .exec();
//        return container;
//    }


    //删除镜像
    public void removeImage(DockerClient client,String imageId){
        client.removeImageCmd(imageId).exec();
    }



    public static void main(String[] args) {
        DockerProtocol docker = new DockerProtocol();

        DockerClient dockerClient = docker.connectDocker();
//        docker.startContainer(dockerClient,"21e7d7460196");
        docker.startContainer(dockerClient,"38dc3681aa87");


    }

}