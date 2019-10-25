package com.hx.back.entity;

public class HxProCenter {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 产品中心id
     */
    private String proCenterId;

    /**
     * 软硬件分类。1.硬件；2.软件
     */
    private Integer hardSoftType;

    /**
     * 软硬件具体分类
     */
    private Integer hardSoftClass;

    /**
     * 产品图片
     */
    private String proImg;

    /**
     * 产品简介
     */
    private String hardSoftIntro;

    /**
     * 所属分类下的产品名称
     */
    private String proName;

    /**
     * 产品对应的页面
     */
    private String proDetil;

    /**
     * 点点击量
     */
    private String clinkNum;

    /**
     * 创建人员
     */
    private String createUser;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新人员
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 扩展字段1
     */
    private String extend1;

    /**
     * 扩展字段2
     */
    private String extend2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProCenterId() {
        return proCenterId;
    }

    public void setProCenterId(String proCenterId) {
        this.proCenterId = proCenterId;
    }

    public Integer getHardSoftType() {
        return hardSoftType;
    }

    public void setHardSoftType(Integer hardSoftType) {
        this.hardSoftType = hardSoftType;
    }

    public Integer getHardSoftClass() {
        return hardSoftClass;
    }

    public void setHardSoftClass(Integer hardSoftClass) {
        this.hardSoftClass = hardSoftClass;
    }

    public String getProImg() {
        return proImg;
    }

    public void setProImg(String proImg) {
        this.proImg = proImg;
    }

    public String getHardSoftIntro() {
        return hardSoftIntro;
    }

    public void setHardSoftIntro(String hardSoftIntro) {
        this.hardSoftIntro = hardSoftIntro;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProDetil() {
        return proDetil;
    }

    public void setProDetil(String proDetil) {
        this.proDetil = proDetil;
    }

    public String getClinkNum() {
        return clinkNum;
    }

    public void setClinkNum(String clinkNum) {
        this.clinkNum = clinkNum;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getExtend1() {
        return extend1;
    }

    public void setExtend1(String extend1) {
        this.extend1 = extend1;
    }

    public String getExtend2() {
        return extend2;
    }

    public void setExtend2(String extend2) {
        this.extend2 = extend2;
    }
}
