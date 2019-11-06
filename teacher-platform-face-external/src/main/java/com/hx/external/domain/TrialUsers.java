package com.hx.external.domain;

import com.hx.external.conf.PhoneNum;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;

public class TrialUsers {

    private Integer id;

    //企业名称
    private String enterpriseName;

    //试用项目名称
    @NotBlank(message = "项目名称不能为空!")
    private String projectName;

    //客户姓名
    @NotBlank(message = "姓名不能为空!")
    private String name;

    //客户邮箱
    @NotBlank(message = "email不能为空!")
    @Email(message = "email不合法!")
    private String email;

    //客户手机号
    @NotBlank(message = "手机号不能为空!")
    @PhoneNum()
    private String phone;

    //申请时间
    private Date date;

    //备注
    private String remakes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRemakes() {
        return remakes;
    }

    public void setRemakes(String remakes) {
        this.remakes = remakes;
    }

}
