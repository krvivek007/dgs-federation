package com.vivek.dgs.book.datafetcher;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.vivek.dgs.book.model.Author;
import com.vivek.dgs.book.model.Book;

import java.util.Arrays;
import java.util.List;

@DgsComponent
public class AuthorResolver {

    private static final List<Author> AUTHORS = Arrays.asList(
            new Author(101, "Robert C. Martin", "unclebob@example.com"),
            new Author(102, "Joshua Bloch", "bloch@example.com")
    );

    @DgsData(parentType = "Book", field = "author")
    public Author getAuthor(DgsDataFetchingEnvironment dfe) {
        Book book = dfe.getSource();
        return AUTHORS.stream()
                .filter(a -> a.getId().equals(book.getAuthorId()))
                .findFirst()
                .orElse(null);
    }
}
