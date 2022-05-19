package com.algaworks.example.auth.post.api;

import com.algaworks.example.auth.post.client.UserResponse;

public class EditorResponse {
    private Long id;
    private String name;

    public EditorResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static EditorResponse of(UserResponse editor) {
        return new EditorResponse(editor.getId(),editor.getName());
    }
    
    public static EditorResponse anonymousEditor(Long id){
        return new EditorResponse(id,"Anônimo");
    }
}
