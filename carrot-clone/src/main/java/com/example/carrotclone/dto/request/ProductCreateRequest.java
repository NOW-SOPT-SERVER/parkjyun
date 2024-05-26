package com.example.carrotclone.dto.request;

import com.example.carrotclone.entity.TradeType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductCreateRequest(@NotBlank String title, @NotNull TradeType tradeType, int price,
                                   Boolean canReceivePriceOffer, Boolean canReceiveDonateOffer, @NotBlank String description) {
}
