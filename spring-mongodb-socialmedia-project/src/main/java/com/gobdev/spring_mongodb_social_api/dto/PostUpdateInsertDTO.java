package com.gobdev.spring_mongodb_social_api.dto;

import java.io.Serializable;

public class PostUpdateInsertDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title;
    private String body;


    public PostUpdateInsertDTO() {}

    public PostUpdateInsertDTO(String title, String body) {
        this.title = title;
        this.body = body;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
