package com.vivek.dgs.purchase.datafetcher;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.vivek.dgs.purchase.model.Book;
import com.vivek.dgs.purchase.model.Buyer;
import com.vivek.dgs.purchase.model.PaymentMode;
import com.vivek.dgs.purchase.model.Purchase;

@DgsComponent
public class PurchaseResolver {

    @DgsData(parentType = "Book", field = "purchase")
    public Purchase getPurchase(DgsDataFetchingEnvironment dfe) {
        Book src = dfe.getSource();
        return findPurchase(src);
    }

    private Purchase findPurchase(Book book) {

        // Static Buyer data
        Buyer buyerJohn = new Buyer(1001, "John Doe", "john@example.com", "+91-9999999999");
        Buyer buyerMary = new Buyer(1002, "Mary Jane", "mary@example.com", "+91-8888888888");

        // Dummy static purchases mapped to book IDs
        if (book.getId() == 1) {
            return new Purchase(
                    5001L,
                    399.00,
                    "INR",
                    PaymentMode.UPI,
                    buyerJohn,
                    "2024-12-11"
            );
        }

        if (book.getId() == 2) {
            return new Purchase(
                    5002L,
                    499.00,
                    "INR",
                    PaymentMode.CREDIT_CARD,
                    buyerMary,
                    "2024-12-08"
            );
        }
        // No purchase found for this book
        return null;
    }
}
