package com.hx.external.domain;

public class Module {

        private Integer id;

        //项目名称
        private String projectName;

        //项目类型
        private String projectType;

        //备注
        private String remakes;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getProjectName() {
            return projectName;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }

        public String getProjectType() {
            return projectType;
        }

        public void setProjectType(String projectType) {
            this.projectType = projectType;
        }

        public String getRemakes() {
            return remakes;
        }

        public void setRemakes(String remakes) {
            this.remakes = remakes;
        }
}
