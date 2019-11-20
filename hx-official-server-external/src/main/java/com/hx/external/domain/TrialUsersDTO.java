package com.hx.external.domain;

import javax.validation.constraints.NotNull;

//TrialUsers的扩展类
public class TrialUsersDTO extends TrialUsers{

    //验证码
    private Integer code;

    //页数
    private Integer offset;

    //条数
    private Integer limit;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "TrialUsersDTO{" +
                "code=" + code +
                ", offset=" + offset +
                ", limit=" + limit +
                '}';
    }
}
