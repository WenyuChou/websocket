package com.polycis.websocket.pojo;

import lombok.Data;

import javax.websocket.Session;
import java.util.Date;

/**
 * @author : Wenyu Zhou
 * @version : v1.0
 * @date : 2019/7/3
 * description : 描述
 */
@Data
public class User {
    /**用uuid做主键(必填)*/
    private String uuid;
    /**页面用户名称（可不填）*/
    private String username;
    /**页面所属项目名称(必填)*/
    private String project;
    /**websocket中的session，认证时不填，服务自行维护*/
    private Session session;
    /**最后更新时间，用于心跳检测*/
    private Date modifyTime;
    /**要发送的数据*/
    private String msg;
}
