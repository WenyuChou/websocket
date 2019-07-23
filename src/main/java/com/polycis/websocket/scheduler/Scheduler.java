package com.polycis.websocket.scheduler;

import com.polycis.websocket.pojo.User;
import com.polycis.websocket.ws.MyWebSocket;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.Map;


/**
 * @author : Wenyu Zhou
 * @version : v1.0
 * @date : 2019/4/18
 * description : 定时器
 */
@Component
public class Scheduler {

    /**每隔60秒执行一次*/
    @Scheduled(fixedRate = 60000)
    public void sendTotalInfo() {
        Date now = new Date();
        for (Map.Entry<String, User> item : MyWebSocket.webSocketSet.entrySet()) {
            if((now.getTime() - item.getValue().getModifyTime().getTime())/1000 > 60){
                try {
                    item.getValue().getSession().getBasicRemote().sendText("连接超时");
                    item.getValue().getSession().close();
                    MyWebSocket.webSocketSet.remove(item.getKey());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*//每天3：05执行
    @Scheduled(cron = "0 05 03 ? * *")
    public void testTasks() {
        System.out.println("定时任务执行时间：" + dateFormat.format(new Date()));
    }*/

}
