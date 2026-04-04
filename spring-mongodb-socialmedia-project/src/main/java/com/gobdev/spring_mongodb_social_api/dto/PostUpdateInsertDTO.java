package com.gobdev.spring_mongodb_social_api.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PostUpdateInsertDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "The title cannot be empty")
    @Size(min = 1, max = 50, message = "The title must have between 1 and 50 characters")
    private String title;
    @NotBlank(message = "The body cannot be empty")
    @Size(min = 1, max = 3000, message = "The body must have between 1 and 3.000 characters")
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
