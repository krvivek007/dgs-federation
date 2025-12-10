package com.vivek.dgs.purchase.model;

public class Purchase {
    private Long id;
    private Double price;
    private String currency;
    private PaymentMode paymentMode;
    private Buyer buyer;
    private String purchasedOn;   // could be LocalDateTime if preferred

    public Purchase(Long id, Double price, String currency,
                    PaymentMode paymentMode, Buyer buyer, String purchasedOn) {
        this.id = id;
        this.price = price;
        this.currency = currency;
        this.paymentMode = paymentMode;
        this.buyer = buyer;
        this.purchasedOn = purchasedOn;
    }

    public Long getId() { return id; }
    public Double getPrice() { return price; }
    public String getCurrency() { return currency; }
    public PaymentMode getPaymentMode() { return paymentMode; }
    public Buyer getBuyer() { return buyer; }
    public String getPurchasedOn() { return purchasedOn; }
}
