package com.hx.domain;

import java.util.HashMap;
import java.util.Map;

public class R extends HashMap<String, Object> {
	public static final long serialVersionUID = 1L;
	//成功
	public static final int SUCCESS_CODE = 0;
	//错误
	public static final int ERROR_CODE = 500;
	//未登陆
	public static final int NOT_LOGIN_CODE = 401;
	//未授权
	public static final int UNAUTHORIZED_CODE = 403;
	//找不到资源
	public static final int NOT_FOUND_RESOURCE_CODE = 404;
	public R() {
		put("code", SUCCESS_CODE);
		put("msg", "操作成功");
		put("data", "");
		put("detail", "");
	}

	public static R error() {
		return error(ERROR_CODE, "操作失败");
	}

	public static R error(String msg) {
		return error(ERROR_CODE, msg);
	}
	public static R error(String msg,String detail) {
		return error(ERROR_CODE, msg,detail);
	}

	public static R error(int code, String msg) {
		R r = new R();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}
	public static R error(int code, String msg,String detail) {
		R r = new R();
		r.put("code", code);
		r.put("msg", msg);
		r.put("detail", detail);
		return r;
	}

	public static R ok(String msg) {
		R r = new R();
		r.put("msg", msg);
		return r;
	}
	public static R ok(Object obj) {
		R r = new R();
		r.put("data", obj);
		return r;
	}
	public static R ok(Object obj ,Integer total){
		R r = R.ok(obj);
		r.put("total", total);
		return r;
	}

	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.putAll(map);
		return r;
	}

	public static R ok() {
		return new R();
	}

	@Override
	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}
	public int getCode(){
		return (int)this.get("code");
	}
	public Object getData(){
		return this.get("data");
	}
}
