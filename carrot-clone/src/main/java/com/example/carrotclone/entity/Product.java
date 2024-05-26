package com.example.carrotclone.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Table(name = "product")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "title", nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "trade_type", nullable = false)
    private TradeType tradeType;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_status", nullable = false)
    private ProductStatus productStatus;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "can_receive_price_offer")
    private boolean canReceivePriceOffer;

    @Column(name = "can_receive_donate_offer")
    private boolean canReceiveDonateOffer;

    @Column(name = "can_receive_chat", nullable = false)
    private String description;

    @Builder(access = AccessLevel.PRIVATE)
    private Product(Member member, String title, TradeType tradeType,
                    ProductStatus productStatus, int price, boolean canReceivePriceOffer,
                    boolean canReceiveDonateOffer, String description) {
        this.member = member;
        this.title = title;
        this.tradeType = tradeType;
        this.productStatus = productStatus;
        this.price = price;
        this.canReceivePriceOffer = canReceivePriceOffer;
        this.canReceiveDonateOffer = canReceiveDonateOffer;
        this.description = description;
    }

    public static Product createSaleProduct(Member member, String title, int price, boolean canReceivePriceOffer, String description) {
        return Product.builder()
                .member(member)
                .title(title)
                .tradeType(TradeType.SELL)
                .productStatus(ProductStatus.ONSALE)
                .price(price)
                .canReceivePriceOffer(canReceivePriceOffer)
                .canReceiveDonateOffer(false)
                .description(description)
                .build();

    }

    public static Product createDonateProduct(Member member, String title, int price, boolean canReceiveDonateOffer, String description) {
        return Product.builder()
                .member(member)
                .title(title)
                .tradeType(TradeType.DONATE)
                .productStatus(ProductStatus.ONSALE)
                .price(price)
                .canReceivePriceOffer(false)
                .canReceiveDonateOffer(canReceiveDonateOffer)
                .description(description)
                .build();

    }
}
