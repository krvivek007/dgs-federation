package com.vivek.dgs.purchase.datafetcher;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsEntityFetcher;
import com.vivek.dgs.purchase.model.Book;

import java.math.BigInteger;
import java.util.Map;

@DgsComponent
public class BookEntityResolver {

    @DgsEntityFetcher(name = "Book")
    public Book resolveBook(Map<String, Object> values) {
        Object idVal = values.get("id");
        Integer id = idVal instanceof Number ? ((Number) idVal).intValue() : null;
        return new Book(id);
    }
}
