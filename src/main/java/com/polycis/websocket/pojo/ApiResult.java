package com.polycis.websocket.pojo;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * @author sunyaming
 * @ClassName: ApiResultHead
 * @Description: 接口结果输出格式基类
 * @date 2017年1月11日 下午6:01:46
 */
public class ApiResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /* 网关返回码 */


    private int code = 10000;
	/* 网关返回码描述 */

    private String msg = "服务调用成功";

    /* 业务返回码 */
    private int sub_code;

    /* 业务返回码信息 */
    private String sub_msg;

    private T data;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getSub_code() {
        return sub_code;
    }

    public void setSub_code(int sub_code) {
        this.sub_code = sub_code;
    }

    public String getSub_msg() {
        return sub_msg;
    }

    public void setSub_msg(String sub_msg) {
        this.sub_msg = sub_msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setCodeMsg(CommonCode code) {
        this.code = code.getKey();
        this.msg = code.getValue();
    }

    public void setSubCodeMsg(CommonSubCode sub_code) {
        this.sub_code = sub_code.getKey();
        this.sub_msg = sub_code.getValue();
    }

    /**
     * code msg 强制使用定义 sub_code sub_msg 为空
     *
     */
    public ApiResult(CommonCode code) {
        super();
        this.code = code.getKey();
        this.msg = CommonCode.get(code.getKey()).getValue();
    }

    /**
     * code msg sub_code sub_msg强制使用定义
     *
     * @param code
     * @param sub_code
     */
    public ApiResult(CommonCode code, CommonSubCode sub_code) {
        super();
        this.code = code.getKey();
        this.msg = code.getValue();
        this.sub_code = sub_code.getKey();
        this.sub_msg = sub_code.getValue();
    }

    public ApiResult(CommonCode code, int sub_code, String sub_msg) {
        super();
        this.code = code.getKey();
        this.msg = code.getValue();
        this.sub_code = sub_code;
        this.sub_msg = sub_msg;
    }

    public ApiResult() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }


}
