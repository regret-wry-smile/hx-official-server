package com.hx.back.entity;

public class HxProTree {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 1,按产品；2，按行业
     */
    private Integer conditionId;

    /**
     * 按条件分类
     */
    private String conditionName;

    /**
     * 类型
     */
    private Integer code;

    /**
     * 名称
     */
    private String name;

    /**
     * 页码
     */
    private Integer offset;
    /**
     * 条数
     */
    private Integer limit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getConditionId() {
        return conditionId;
    }

    public void setConditionId(Integer conditionId) {
        this.conditionId = conditionId;
    }

    public String getConditionName() {
        return conditionName;
    }

    public void setConditionName(String conditionName) {
        this.conditionName = conditionName;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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