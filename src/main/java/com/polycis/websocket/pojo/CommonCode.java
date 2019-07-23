package com.polycis.websocket.pojo;

import java.util.ArrayList;
import java.util.List;

/**
* @ClassName: CommonCode
* @Description: 返回码 主码
* @author weitao
* @date 2017年1月11日 下午8:58:53
 */
public enum CommonCode {
	SUCCESS(10000,"服务调用成功"),
    NO_DATA(10001,"未查询到数据"),
	ERROR(20000,"服务不可用"),
	TOKEN_INVALID(20001,"token无效"),
	PARAMETER_LOSE(40001,"缺少必选参数"),
	PARAMETER_INVALID(40002,"非法的参数"),
	AUTH_LIMIT(40006,"权限不足"),
	CODE_INVALID(0,"提示码参数非法"),
    INSERT_ERROR(20000,"数据新增失败"),
	DELETE_ERROR(20000,"数据删除失败"),
	UPDATE_ERROR(20000,"数据修改失败"),
	OPER_FAILURE(30000, "操作失败");


	private int key;
	private String value;


	public static CommonCode get(int  key) {
		for (CommonCode dot : CommonCode.values()) {
			if (key == dot.getKey()) {
				return dot;
			}
		}
		return CODE_INVALID;
	}


	public static CommonCode get(String value) {
		for (CommonCode dot : CommonCode.values()) {
			if (value.equals(dot.getValue())) {
				return dot;
			}
		}
		return CODE_INVALID;
	}

	public static List<Integer> getAllKey() {
		List<Integer> list =new ArrayList<Integer>();
		for (CommonCode dot : CommonCode.values()) {
			list.add(dot.getKey());
		}
		return list;
	}

	public static List<String> getAllValue() {
		List<String> list =new ArrayList<String>();
		for (CommonCode dot : CommonCode.values()) {
			list.add(dot.getValue());
		}
		return list;
	}



	private CommonCode(int key, String value) {
		this.key = key;
		this.value = value;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}


}
