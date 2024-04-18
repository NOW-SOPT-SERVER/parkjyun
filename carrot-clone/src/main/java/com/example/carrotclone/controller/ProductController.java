package com.example.carrotclone.controller;

import com.example.carrotclone.common.code.success.ProductSuccessCode;
import com.example.carrotclone.common.dto.CarrotResponse;
import com.example.carrotclone.controller.headers.CustomHeaders;
import com.example.carrotclone.dto.request.ProductCreateRequest;
import com.example.carrotclone.service.ProductCommandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductCommandService productCommandService;

    @PostMapping
    public CarrotResponse<?> createProduct(@RequestBody @Valid ProductCreateRequest request,
                                           @RequestHeader(CustomHeaders.MEMBER_ID) Long memberId) {
        productCommandService.createProduct(request, memberId);
        return CarrotResponse.success(ProductSuccessCode.PRODUCT_CREATE_SUCCESS);
    }
}
