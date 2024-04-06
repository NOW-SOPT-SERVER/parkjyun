package org.sopt.practice.controller;

import org.sopt.practice.controller.dto.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestContorller {
    @GetMapping
    public String test() {
        return "TEST API";
    }

    @GetMapping("/json")
    public ApiResponse testJson() {
        return ApiResponse.create("TEST API");
    }

}
