package com.sicimike.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * 服务降级
 * @author sicimike
 * @create 2018-12-06 17:36
 */
@RestController
@DefaultProperties(defaultFallback = "defaultFallback") //指定默认降级处理方法
public class HystrixController {

    /**
     * 目标服务不可用，或者抛出异常时会触发降级
     * @return
     */
    //@HystrixCommand(fallbackMethod = "fallback")  //单个服务指定降级方法
    @HystrixCommand
    @GetMapping("/getProductInfoList")
    public String getProductInfoList(){
        RestTemplate restTemplate = new RestTemplate();
        return  restTemplate.postForObject("http://127.0.0.1:8082/product/listForOrder", Arrays.asList("157875196366160022"), String.class);
    }

    /**
     * 服务降级处理
     * @return
     */
    private String fallback(){
        return "排队中，请等候...";
    }

    private String defaultFallback(){
        return "默认降级处理：排队中，请稍等...";
    }


}
