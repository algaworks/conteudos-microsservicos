package com.example.springamqp.aula1.outbox;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "tb_outbox_message")
public class OutboxMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private OffsetDateTime createdAt = OffsetDateTime.now();

    private String destination;

    @Column(columnDefinition = "text")
    private String content;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    private int tentatives;

    public void increaseTentatives() {
        tentatives++;
    }

    enum Status {
        PENDING, SENT, ERROR;
    }

    protected OutboxMessage() {
    }

    protected OutboxMessage(String destination, String content) {
        this.destination = destination;
        this.content = content;
    }

    public OutboxMessage(Long id, OffsetDateTime createdAt, String content, String destination, Status status, int tentatives) {
        this.id = id;
        this.createdAt = createdAt;
        this.content = content;
        this.destination = destination;
        this.status = status;
        this.tentatives = tentatives;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getTentatives() {
        return tentatives;
    }

    public void setTentatives(int tentatives) {
        this.tentatives = tentatives;
    }
}
