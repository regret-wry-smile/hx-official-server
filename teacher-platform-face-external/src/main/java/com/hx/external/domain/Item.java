package com.hx.external.domain;

import java.util.List;

public class Item {

    private String title;

    private List<External> list;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<External> getList() {
        return list;
    }

    public void setList(List<External> list) {
        this.list = list;
    }
}
