package com.example.carrotclone.service;

import com.example.carrotclone.common.code.failure.MemberFailureCode;
import com.example.carrotclone.common.exception.MemberException;
import com.example.carrotclone.dto.request.ProductCreateRequest;
import com.example.carrotclone.entity.Member;
import com.example.carrotclone.entity.Product;
import com.example.carrotclone.entity.TradeType;
import com.example.carrotclone.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductCommandService {

    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    public void createProduct(ProductCreateRequest request, Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberFailureCode.MEMBER_NOT_FOUND));

        if (request.tradeType().getValue().equals(TradeType.SELL.getValue())) {
            Product saleProduct = Product.createSaleProduct(member, request.title(), request.price(), request.canReceivePriceOffer(), request.description());
            productRepository.save(saleProduct);
        }

        if (request.tradeType().getValue().equals(TradeType.DONATE.getValue())) {
            Product donateProduct = Product.createDonateProduct(member, request.title(), request.price(), request.canReceiveDonateOffer(), request.description());
            productRepository.save(donateProduct);
        }
    }
}
