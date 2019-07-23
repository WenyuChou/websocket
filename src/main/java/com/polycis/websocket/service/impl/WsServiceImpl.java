package com.polycis.websocket.service.impl;

import com.polycis.websocket.feignclient.JavaUtil;
import com.polycis.websocket.service.WsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : Wenyu Zhou
 * @version : v1.0
 * @date : 2019/7/4
 * description : 描述
 */
@Service
public class WsServiceImpl implements WsService {
    @Autowired
    private JavaUtil feign;

    @Override
    public String feigntest() {
        return feign.test();
    }
}
