package com.algaworks.example.auth.post.api;

import com.algaworks.example.auth.post.domain.Post;

public class PostSummaryResponse {
    private Long id;
    private String title;

    public PostSummaryResponse(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static PostSummaryResponse of(Post post) {
        return new PostSummaryResponse(post.getId(), post.getTitle());
    }
    
}
