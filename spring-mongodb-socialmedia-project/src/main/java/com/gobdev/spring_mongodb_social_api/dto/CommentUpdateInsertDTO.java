package com.gobdev.spring_mongodb_social_api.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CommentUpdateInsertDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "You cannot insert an empty comment")
    @Size(min = 1, max = 1000, message = "The comment must have between 1 and 1.000 characters")
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