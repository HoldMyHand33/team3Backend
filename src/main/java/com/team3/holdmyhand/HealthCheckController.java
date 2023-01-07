package com.team3.holdmyhand;

import com.team3.holdmyhand.global.config.CommonApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HealthCheckController {
    @GetMapping
    public CommonApiResponse<Boolean> healthCheck() {
        return CommonApiResponse.of(true);
    }
}
