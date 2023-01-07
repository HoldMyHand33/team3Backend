package com.team3.holdmyhand.domain.member;

import com.team3.holdmyhand.domain.member.dto.FollowResponseDto;
import com.team3.holdmyhand.global.CommonApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

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

    /*@GetMapping
    @ApiOperation(value = "팔로우 현황")
    public ResponseEntity<CommonApiResponse<List<FollowResponseDto>>> showFollow(
            @ApiIgnore Authentication authentication) {
        return ResponseEntity.ok(CommonApiResponse.of(followService.showFollowers(authentication.getName())));
    }*/
}
