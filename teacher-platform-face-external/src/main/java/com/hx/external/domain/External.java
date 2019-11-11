package com.hx.external.domain;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class External {

        //编号
        private Integer id;

        //项目模块
        private String projectType;

        //文件名称
        private String interfaceName;

        //文件地址
        @NotBlank(message = "请上传文件!")
        private String interfaceAddress;

        //创建时间
        private Date createDate;

        //修改时间
        private Date updateDate;

        //备注
        private String remake;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getProjectType() {
            return projectType;
        }

        public void setProjectType(String projectType) {
            this.projectType = projectType;
        }

        public String getInterfaceName() {
            return interfaceName;
        }

        public void setInterfaceName(String interfaceName) {
            this.interfaceName = interfaceName;
        }

        public String getInterfaceAddress() {
            return interfaceAddress;
        }

        public void setInterfaceAddress(String interfaceAddress) {
            this.interfaceAddress = interfaceAddress;
        }

        public Date getCreateDate() {
            return createDate;
        }

        public void setCreateDate(Date createDate) {
            this.createDate = createDate;
        }

        public Date getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(Date updateDate) {
            this.updateDate = updateDate;
        }

        public String getRemake() {
            return remake;
        }

        public void setRemake(String remake) {
            this.remake = remake;
        }
    }

