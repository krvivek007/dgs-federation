package com.vivek.dgs.book.datafetcher;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import com.vivek.dgs.book.model.Book;

import java.util.Arrays;
import java.util.List;

@DgsComponent
public class BookDataFetcher {

    private static final List<Book> BOOKS = Arrays.asList(
            new Book(1, "Clean Code", 101),
            new Book(2, "Effective Java", 102)
    );

    @DgsQuery
    public List<Book> books() {
        return BOOKS;
    }

    @DgsQuery
    public Book book(@InputArgument("id") Integer id) {
        return BOOKS.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
