package com.polycis.websocket.controller;

import com.alibaba.fastjson.JSONObject;
import com.polycis.websocket.pojo.ApiResult;
import com.polycis.websocket.pojo.CommonCode;
import com.polycis.websocket.pojo.User;
import com.polycis.websocket.service.WsService;
import com.polycis.websocket.ws.MyWebSocket;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author : Wenyu Zhou
 * @version : v1.0
 * @date : 2019/7/3
 * description : 描述
 */
@RestController
@RequestMapping("/websocket")
public class WsController {
    @Resource
    private WsService service;

    @GetMapping(value = "/test")
    public String feigntest(){
        return service.feigntest();
    }

    /**
     * 页面websocket鉴权
     * @param user 页面用户信息
     * @return api
     */
    @PostMapping("/user")
    public ApiResult addUser(@RequestBody User user) {
        ApiResult api = new ApiResult(CommonCode.SUCCESS);
        if (MyWebSocket.webSocketSet.containsKey(user.getUuid())) {
            api.setCodeMsg(CommonCode.OPER_FAILURE);
            api.setMsg("该用户id已经存在，添加失败");
        } else {
            user.setModifyTime(new Date());
            MyWebSocket.webSocketSet.put(user.getUuid(), user);
        }
        return api;
    }

    /**
     * 发送websocket消息
     * @param user msg必填，uuid/project 二者选一必填
     * @return api
     */
    @PutMapping("/user")
    public ApiResult putUser(@RequestBody User user) {
        ApiResult api = new ApiResult(CommonCode.SUCCESS);
        if (user.getUuid() != null || user.getProject() != null) {
            if (user.getUuid() != null) {
                MyWebSocket.sendMessage(user.getMsg(), user.getUuid());
            } else if (user.getProject() != null) {
                MyWebSocket.sendInfoAll(user.getMsg(),user.getProject());
            }
            return api;
        } else {
            api.setCodeMsg(CommonCode.PARAMETER_LOSE);
            return api;
        }
    }

    /**
     * 页面用户查询
     *
     * @param user uuid 和 project中必填一项
     * @return api
     */
    @GetMapping("/user")
    public ApiResult getUser(@RequestBody User user) {
        ApiResult<List<User>> api = new ApiResult<>(CommonCode.SUCCESS);
        List<User> userList = new ArrayList<>();
        if (user.getUuid() != null) {
            if (MyWebSocket.webSocketSet.containsKey(user.getUuid())) {
                userList.add(MyWebSocket.webSocketSet.get(user.getUuid()));
            }
            api.setData(userList);
        }
        if (user.getProject() != null) {
            for (Map.Entry<String, User> item : MyWebSocket.webSocketSet.entrySet()) {
                if (user.getProject().equals(item.getValue().getProject())) {
                    userList.add(item.getValue());
                }
            }
            api.setData(userList);
        }
        return api;
    }

}
