package com.algaworks.example.auth.post.api;

import javax.validation.constraints.NotBlank;

public class PostRequest {

    @NotBlank
    private String title;
    
    @NotBlank
    private String content;
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
