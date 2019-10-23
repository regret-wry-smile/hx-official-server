package com.hx.external.domain;

public class ExternalDTO extends External{

    //页数
    private Integer offset;

    //条数
    private Integer limit;

    //项目名称
    private String projectName;

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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Override
    public String toString() {
        return "ExternalDTO{" +
                "offset=" + offset +
                ", limit=" + limit +
                ", projectName='" + projectName + '\'' +
                '}';
    }
}
