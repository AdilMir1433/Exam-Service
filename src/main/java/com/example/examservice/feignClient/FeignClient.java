package com.example.examservice.feignClient;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.cloud.openfeign.FeignClient (name = "user-service")
public interface FeignClient {
        @GetMapping("/users/auth/postexam")
        public String postExam();
    }
