package com.algaworks.example.auth.post.api;

import com.algaworks.example.auth.post.domain.Post;

import java.time.OffsetDateTime;

import static com.algaworks.example.auth.post.api.EditorResponse.anonymousEditor;

public class PostDetailedResponse {
    private Long id;
    private OffsetDateTime createdAt;
    private String title;
    private EditorResponse editor;
    private String content;

    public PostDetailedResponse(Long id, OffsetDateTime createdAt, String title, String content, EditorResponse editor) {
        this.id = id;
        this.createdAt = createdAt;
        this.title = title;
        this.content = content;
        this.editor = editor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public EditorResponse getEditor() {
        return editor;
    }

    public void setEditor(EditorResponse editor) {
        this.editor = editor;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static PostDetailedResponse of(Post post) {
        return new PostDetailedResponse(
                post.getId(), 
                post.getCreatedAt(), 
                post.getTitle(), 
                post.getContent(), 
                anonymousEditor(post.getEditorId()));
    }

    public static PostDetailedResponse of(Post post, EditorResponse editor) {
        return new PostDetailedResponse(
                post.getId(), 
                post.getCreatedAt(), 
                post.getTitle(), 
                post.getContent(), 
                editor);
    }
    
}
