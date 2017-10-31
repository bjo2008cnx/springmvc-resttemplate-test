package com.weimob.common.enums;

import com.weimob.common.lang.ErrorCode;

public enum RETURN_CODE implements ErrorCode{

	// common
	SUCCESS							   (0, 		"success"),
	SUBMIT_SUCCESS					   (1,		"操作成功"),
	FAIL							   (7999999,"fail"),
	SUBMIT_FAIL						   (7999998,"操作失败"),
	ARGS_EMPTY						   (7999997,"参数为空"),
	DATA_UNIQUE						   (7999996,"数据不唯一"),
	OBJECT_DELETED					   (7999995,"对象不存在"),
	ARGS_ERROR						   (7999994,"参数错误"),
	MERCHANT_ID_DISEXIST			   (7999993,"商户id不存在"),
	SERVICE_EXCEPTION				   (7999992,"服务异常"),
	GET_BUSINESS_EXCEPTION			   (7999991,"商户信息不存在"),
	REFLECT_METHOD_INVOCATION_EXCEPTION(7999990,"反射方法调用异常"),
	DB_SHARD_EXCEPTION				   (7999989,"分表异常"),
	VERSION_NOT_MATCH				   (7999988,"数据版本不匹配"),
	UNSUPPORTED_DATATYPEVALUE		   (7999987,"不支持的数据类型值"),
	UNKNOWNED_ERROR					   (7999986,"未知错误"),
	RESPONSE_NULL					   (7999985,"空响应"),
	BOWLFEE_NOT_MATCH				   (7999984,"商户已修改餐盒费"),
	RESPONSE_ORDERSTATUS_CHANGE					   (7999983,"该笔订单状态已做变更"),
	OFFER_PAY_RULE_HAD_CHANGE					   (7999982,"优惠买单规则已经变更"),


	SESSION_INVALID					   (7888880,"无效的session"),
	PUBLIC_ACCOUNT_NO_AUTHBIND					   (7888881,"未授权绑定公众号"),
	MINI_ACCOUNT_NO_AUTHBIND					   (7888882,"未授权绑定小程序"),
	BLACK_WHITE_INVALID					   (7888883, "商户ID在黑名单里或者不在白名单里"),
	SUITE_PACKAGE_INVALID					   (7888871,"对不起，您的账户已经到期，请联系当地代理商或微盟智慧餐厅客服购买正式版套餐或续费继续使用"),



	;
	
	class ErrorEntity {
		public int errcode;
		public String errmsg;
	}

	public ErrorEntity entity = new ErrorEntity();
	
	private RETURN_CODE(int errcode, String errmsg) {
		this.entity.errcode = errcode;
		this.entity.errmsg = errmsg;
	}
	
	public int getCode() {
		return this.entity.errcode;
	}
	
	public String getMsg() {
		return this.entity.errmsg;
	}

	@Override
	public String getDescription() {
		return this.getMsg();
	}

	@Override
	public Integer getKey() {
		return this.getCode();
	}
}
