package com.model;

import lombok.Data;

@Data
public class ActionUser {
    private String name;

    public ActionUser(String name) {
        this.name = name;
    }
}
