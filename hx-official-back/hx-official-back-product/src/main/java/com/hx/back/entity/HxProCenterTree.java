package com.hx.back.entity;

public class HxProCenterTree {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 硬软件分类。1.硬件2.软件
     */
    private Integer hardSoftType;

    /**
     * 硬软件分类code
     */
    private Integer hardSoftCode;

    /**
     * 硬软件分类名称
     */
    private String hardSoftName;

    /**
     * 扩展字段
     */
    private String extend;

    /*起始页码*/
    private Integer offset;
    /*单页数量*/
    private Integer limit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHardSoftType() {
        return hardSoftType;
    }

    public void setHardSoftType(Integer hardSoftType) {
        this.hardSoftType = hardSoftType;
    }

    public Integer getHardSoftCode() {
        return hardSoftCode;
    }

    public void setHardSoftCode(Integer hardSoftCode) {
        this.hardSoftCode = hardSoftCode;
    }

    public String getHardSoftName() {
        return hardSoftName;
    }

    public void setHardSoftName(String hardSoftName) {
        this.hardSoftName = hardSoftName;
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
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
