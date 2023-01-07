package com.team3.holdmyhand.domain.member;

import com.team3.holdmyhand.global.CommonApiResponse;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/follow")
@RequiredArgsConstructor
@Api(tags = "팔로우")
public class FollowApiController {
    private final FollowService followService;

    @PostMapping("{fromUserId}/{toUserId}")
    public ResponseEntity<CommonApiResponse<String>> makeFollow(
            @PathVariable Long fromUserId,
            @PathVariable Long toUserId) {
        return ResponseEntity.ok(CommonApiResponse.of(followService.makeFollow(fromUserId, toUserId)));
    }
}
