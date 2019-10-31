package com.hx.back.entity;

public class HxProCenterDTO extends HxProCenter {

    /*分类具体名称*/
    private String hardSoftName;
    /*起始页码*/
    private Integer offset;
    /*单页数量*/
    private Integer limit;

    public String getHardSoftName() {
        return hardSoftName;
    }

    public void setHardSoftName(String hardSoftName) {
        this.hardSoftName = hardSoftName;
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
}
