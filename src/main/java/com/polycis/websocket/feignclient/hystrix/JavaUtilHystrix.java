package com.polycis.websocket.feignclient.hystrix;

import com.polycis.websocket.feignclient.JavaUtil;
import org.springframework.stereotype.Component;

/**
 * @author : Wenyu Zhou
 * @version : v1.0
 * @date : 2019/7/4
 * description : 描述
 */
@Component
public class JavaUtilHystrix implements JavaUtil {
    @Override
    public String test() {
        return "熔断器开启";
    }
}
