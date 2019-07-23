package com.polycis.websocket.pojo;

/**
* @ClassName: CommonSubCode
* @Description: 返回码 码
* @author weiao
* @date 2017年1月11日 下午8:58:53
 */
public enum CommonSubCode {
	ISP_UNKNOW_ERROR(20000,"服务暂不可用(业务系统不可用)"),
	AOP_UNKNOW_ERROR(20000,"服务暂不可用(网关自身的未知错误)"),
	AOP_INVALID_AUTH_TOKEN(20001,"无效的访问令牌"),
	AOP_AUTH_TOKEN_TIME_OUT(20001,"访问令牌已过期"),
	AOP_INVALID_APP_AUTH_TOKEN(20001,"无效的应用授权令牌"),
	AOP_APP_AUTH_TOKEN_TIME_OUT(20001,"应用授权令牌已过期"),
	ISV_MISSING_METHOD(40001,"缺少方法名参数"),
	ISV_MISSING_SIGNATURE(40001,"缺少签名参数"),
	ISV_MISSING_SIGNATURE_KEY(40001,"缺少签名配置"),
	ISV_MISSING_APP_ID(40001,"缺少APPID参数"),
	ISV_MISSING_TIMESTAMP(40001,"缺少时间戳参数"),
	ISV_MISSING_VERSION(40001,"缺少版本参数"),
	ISV_INVALID_PARAMETER(40002,"参数无效"),
	ISV_UPLOAD_FAIL(40002,"文件上传失败"),
	ISV_INVALID_FILE_EXTENSION(40002,"文件扩展名无效"),
	ISV_INVALID_FILE_SIZE(40002,"文件大小无效"),
	ISV_INVALID_METHOD(40002,"不存在的方法名"),
	ISV_INVALID_FORMAT(40002,"无效的数据格式"),
	ISV_INVALID_SIGNATURE_TYPE(40002,"无效的签名类型"),
	ISV_INVALID_SIGNATURE(40002,"无效签名"),
	ISV_INVALID_ENCRYPT(40002,"解密异常"),
	ISV_INVALID_APP_ID(40002,"无效的APPID参数"),
	ISV_INVALID_TIMESTAMP(40002,"非法的时间戳参数"),
	ISV_INVALID_CHARSET(40002,"字符集错误"),
	ISV_DECRYPTION_ERROR_NOT_VALID_ENCRYPT_KEY(40002,"解密出错, 未配置加密密钥或加密密钥格式错误"),
	ISV_DECRYPTION_ERROR_UNKNOWN(40002,"解密出错，未知异常"),
	ISV_NOT_SUPPORT_APP_AUTH(40002,"本接口不支持第三方代理调用"),
	ISV_INSUFFICIENT_USER_PERMISSIONS(40006,"用户权限不足"),
	INVALID_CODE(0,"提示码参数非法");
	private CommonSubCode(int key,String value) {
		this.key=key;
		this.value=value;
	}
	private int key;
	private String value;

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


	public static CommonSubCode get(int  key) {
		for (CommonSubCode dot : CommonSubCode.values()) {
			if (key == dot.getKey()) {
				return dot;
			}
		}
		return INVALID_CODE;
	}


	public static CommonSubCode get(String value) {
		for (CommonSubCode dot : CommonSubCode.values()) {
			if (value.equals(dot.getValue())) {
				return dot;
			}
		}
		return INVALID_CODE;
	}



}
