package com.algaworks.example.s3.domain.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@Entity
public class Ebook {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "char(36)")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @CreationTimestamp
    private OffsetDateTime createdAt;

    private String title;

    private String autor;

    protected Ebook() {
        //JPA/Hibernate
    }

    public Ebook(UUID id, OffsetDateTime createdAt,
                 String title, String autor) {
        Objects.requireNonNull(title);
        Objects.requireNonNull(autor);
        this.id = id;
        this.createdAt = createdAt;
        this.title = title;
        this.autor = autor;
    }

    public void update(Ebook ebookUpdated) {
        this.title = ebookUpdated.title;
        this.autor = ebookUpdated.autor;
    }
}
