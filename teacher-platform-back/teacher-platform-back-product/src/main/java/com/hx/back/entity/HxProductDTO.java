package com.hx.back.entity;

/**
 * 产品扩展类
 */
public class HxProductDTO extends HxProduct{

    /**
     * id数组
     */
    private int[] ids;

    /**
     * 页码
     */
    private Integer offset;
    /**
     * 条数
     */
    private Integer limit;
    /**
     * 按产品分类名称
     */
    private String proWithName;

    /**
     * 按行业分类名称
     */
    private String busWithName;

    public int[] getIds() {
        return ids;
    }

    public void setIds(int[] ids) {
        this.ids = ids;
    }


    public String getProWithName() {
        return proWithName;
    }

    public void setProWithName(String proWithName) {
        this.proWithName = proWithName;
    }

    public String getBusWithName() {
        return busWithName;
    }

    public void setBusWithName(String busWithName) {
        this.busWithName = busWithName;
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
