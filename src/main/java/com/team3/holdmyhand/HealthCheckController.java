package com.team3.holdmyhand;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class HealthCheckController {

    @GetMapping
    public ResponseEntity<String> test() {
        System.out.println("성공1234");
        return ResponseEntity.ok("테스트1234");
    }
}

