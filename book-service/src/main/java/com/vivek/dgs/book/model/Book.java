package com.vivek.dgs.book.model;

public class Book {
    private Integer id;
    private String title;
    private Integer authorId;

    public Book(Integer id, String title, Integer authorId) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
    }

    public Integer getId() { return id; }
    public String getTitle() { return title; }
    public Integer getAuthorId() { return authorId; }
}
