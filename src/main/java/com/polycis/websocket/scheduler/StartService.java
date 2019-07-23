package com.polycis.websocket.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author : Wenyu Zhou
 * @version : v1.0
 * @date : 2019/4/18
 * description : 项目初始化后运行订阅功能
 */
@Component
@Order(value = 1)
@Slf4j
public class StartService implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args){
        log.info("项目初始化完成");
    }
}
