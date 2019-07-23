package com.polycis.websocket.feignclient;

import com.polycis.websocket.feignclient.hystrix.JavaUtilHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author : Wenyu Zhou
 * @version : v1.0
 * @date : 2019/7/4
 * description : 描述
 */
@FeignClient(value = "service-java-util" , fallback = JavaUtilHystrix.class)
public interface JavaUtil {
    /**
     * 1、调用远程服务超时后，断路器打开，调用getOneFallBack (如果远程服务挂了，会立马调用getOneFallBack，超时时间不起作用)
     * 2、超时时间为2000毫秒(默认1秒)
     */
    @PostMapping(value = "/download/test",produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
    String test();
}
