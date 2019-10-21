package com.hx.external.domain;

//TrialUsers的扩展类
public class TrialUsersDTO extends TrialUsers{

    //页数
    private Integer offset;

    //条数
    private Integer limit;


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
                "offset=" + offset +
                ", limit=" + limit +
                '}';
    }
}
