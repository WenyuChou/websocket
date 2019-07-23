package com.polycis.websocket.ws;

import com.polycis.websocket.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : Wenyu Zhou
 * @version : v2.0 (本版本已经适用于聊天系统，可定点发送text信息)
 * @date : 2019/4/26
 * description : websocket协议。注册时需要在路径中声明用户id
 */
@Slf4j
@ServerEndpoint(value = "/websocket/{uuid}")
@Component
public class MyWebSocket {

    /**
     * ConcurrentHashMap包的线程安全Map，用来存放每个客户端对应的Session对象。
     */
    public static ConcurrentHashMap<String, User> webSocketSet = new ConcurrentHashMap<>();

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private String uuid;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(@PathParam("uuid") String uuid, Session session) {
        this.uuid = uuid;
        //加入set中
        if(webSocketSet.containsKey(uuid)){
            User user = webSocketSet.get(uuid);
            user.setUuid(uuid);
            user.setSession(session);
            webSocketSet.put(uuid, user);
            //在线数加1
            log.info("有新连接加入！当前在线人数为" + webSocketSet.size());
        }else {
            try {
                session.getBasicRemote().sendText("无连接权限");
                session.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //sendMessage("连接成功", userId);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        //从set中删除
        webSocketSet.remove(this.uuid);
        //在线数减1
        log.info("有一连接关闭！当前在线人数为" + webSocketSet.size());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        webSocketSet.get(this.uuid).setModifyTime(new Date());
        log.info("来自客户端" + this.uuid + "的消息:" + message);
    }

    /**
     * 发生错误时调用
     * log wrong
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error(session.getId() + "发生错误");
        error.printStackTrace();
    }

    /**
     * 给指定用户发
     *
     * @param message 发送消息内容
     * @param userId  用户id（open时注册）
     */
    public static void sendMessage(String message, String userId) {
        try {
            if (webSocketSet.containsKey(userId)) {
                webSocketSet.get(userId).getSession().getBasicRemote().sendText(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //this.session.getAsyncRemote().sendText(message);
    }

    /**
     * 群发自定义消息
     * @param message 发送信息
     * @param project 所属项目
     * @return success / fail
     */
    public static String sendInfoAll(String message,String project) {
        if(project == null){
            return "fail";
        }
        for (Map.Entry<String, User> item : webSocketSet.entrySet()) {
            if(project.equals(item.getValue().getProject()) || "all".equals(project)){
                sendMessage(message, item.getKey());
            }
        }
        return "success";
    }
}
