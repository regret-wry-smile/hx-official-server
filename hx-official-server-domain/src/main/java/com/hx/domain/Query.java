package com.hx.domain;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 查询参数
 */
public class Query extends LinkedHashMap<String, Object> {

	private static final long serialVersionUID = 1L;
	// 
	private int offset;
	// 每页条数
	private int limit;

	public Query(Map<String, Object> params) {
		if (params != null) {
			this.putAll(params);
			// 分页参数
			Object offset = params.get("offset");
			Object limit = params.get("limit");
			if (offset != null && limit != null) {
				this.offset = Integer.parseInt(offset.toString());
				this.limit = Integer.parseInt(limit.toString());
				this.put("offset", this.offset);
				this.put("page", this.offset / this.limit + 1);
				this.put("limit", this.limit);
			}
		}
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.put("offset", offset);
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
}
