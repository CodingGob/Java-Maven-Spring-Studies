package com.gobdev.spring_mongodb_social_api.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "text", "date" })
public class CommentDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id; //internal id for better updating/deletion
    private String text;
    private LocalDate date;
    private LocalDate updateDate;

    private AuthorDTO author;


    public CommentDTO() {}

    public CommentDTO(String text, LocalDate date, LocalDate updateDate, AuthorDTO author) {
        this.text = text;
        this.date = date;
        this.updateDate = updateDate;
        this.author = author;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }
}