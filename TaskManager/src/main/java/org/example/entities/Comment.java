package org.example.entities;

import java.util.Date;
import java.util.UUID;

public class Comment {
    private String id;
    private String content;
    private User author;
    private Date createdAt;

    public Comment(String content, User author) {
        this.content = content;
        this.author = author;
        this.id = UUID.randomUUID().toString();
        this.createdAt = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
