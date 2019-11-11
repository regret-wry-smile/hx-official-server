package com.hx.pojo;

public class ProductWeb {
    private Integer id;

    private String webName;

    private String webBrief;

    private String website;

    private String webCategory;

    private String webPicture;

    private String remark;

    private String extend1;

    private String extend2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWebName() {
        return webName;
    }

    public void setWebName(String webName) {
        this.webName = webName == null ? null : webName.trim();
    }

    public String getWebBrief() {
        return webBrief;
    }

    public void setWebBrief(String webBrief) {
        this.webBrief = webBrief == null ? null : webBrief.trim();
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website == null ? null : website.trim();
    }

    public String getWebCategory() {
        return webCategory;
    }

    public void setWebCategory(String webCategory) {
        this.webCategory = webCategory == null ? null : webCategory.trim();
    }

    public String getWebPicture() {
        return webPicture;
    }

    public void setWebPicture(String webPicture) {
        this.webPicture = webPicture == null ? null : webPicture.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getExtend1() {
        return extend1;
    }

    public void setExtend1(String extend1) {
        this.extend1 = extend1 == null ? null : extend1.trim();
    }

    public String getExtend2() {
        return extend2;
    }

    public void setExtend2(String extend2) {
        this.extend2 = extend2 == null ? null : extend2.trim();
    }
}