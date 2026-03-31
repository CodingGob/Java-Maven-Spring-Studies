package com.gobdev.spring_mongodb_social_api.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;

import com.gobdev.spring_mongodb_social_api.domain.Post;

public class PostPageDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Post> content;
    private int totalPages;
    private long totalElements;
    private int currentPage;

    
    public PostPageDTO(Page<Post> page) {
        this.content = page.getContent();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        this.currentPage = page.getNumber();
    }


    public List<Post> getContent() {
        return content;
    }

    public void setContent(List<Post> content) {
        this.content = content;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
