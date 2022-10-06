package com.example.algaworks.spring.cloud.stream.webpush.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedEvent {
    private String guid;
    private String title;
    private String autor;
    private String description;
    private String link;
    private String pubDate;
}
