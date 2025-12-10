package com.vivek.dgs.purchase.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Book {
    @JsonProperty("__typename")
    private final String typename = "Book";
    private Integer id;

    public Book(Integer id) {
        this.id = id;
    }

    public Integer getId() { return id; }
    public String getTypename() { return typename; }
}
