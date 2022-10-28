package com.algaworks.example.s3.domain.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Ebook {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "char(36)")
    @Type(type = "org.hibernate.type.UUIDCharType")
    @EqualsAndHashCode.Include
    private UUID id;

    @CreationTimestamp
    private OffsetDateTime createdAt;

    private String title;

    private String autor;

    @OneToOne(cascade = CascadeType.ALL)
    private FileReference cover;

    @OneToOne(cascade = CascadeType.ALL)
    private FileReference attachment;

    protected Ebook() {
        //JPA/Hibernate
    }

    public Ebook(UUID id, OffsetDateTime createdAt,
                 String title, String autor,
                 FileReference cover, FileReference attachment) {
        Objects.requireNonNull(title);
        Objects.requireNonNull(autor);
        Objects.requireNonNull(cover);
        Objects.requireNonNull(attachment);

        this.id = id;
        this.createdAt = createdAt;
        this.title = title;
        this.autor = autor;
        this.cover = cover;
        this.attachment = attachment;
    }

    public void update(Ebook ebookUpdated) {
        Objects.requireNonNull(ebookUpdated);
        this.title = ebookUpdated.title;
        this.autor = ebookUpdated.autor;
        this.cover = ebookUpdated.cover;
        this.attachment = ebookUpdated.attachment;
    }
}
