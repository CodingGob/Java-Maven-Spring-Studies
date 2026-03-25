package com.gobdev.spring_mongodb_social_api.dto;

import java.io.Serializable;

public class CommentUpdateInsertDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String text;


    public CommentUpdateInsertDTO() {}

    public CommentUpdateInsertDTO(String text) {
        this.text = text;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}