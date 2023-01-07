package com.team3.holdmyhand.domain.makeup;

import com.team3.holdmyhand.domain.makeup.dto.GetCommentRes;
import com.team3.holdmyhand.global.CommonApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/makeups")
@Api(tags = "화해하자")
public class MakeUpController {
    private final MakeUpService makeUpService;

    /* 화해하자 멘트 받아오기 API */
    @GetMapping("/{targetId}/{typeId}")
    @ApiOperation(value = "대상, 유형별 화해 멘트 받아오기")
    public ResponseEntity<CommonApiResponse<GetCommentRes>> getComment(
        @ApiIgnore Authentication authentication,
        @PathVariable int targetId, @PathVariable int typeId) {
        // 현재 로그인 되어 있는 유저 정보 가져오기
        String email = authentication.getName();

        return ResponseEntity.ok(CommonApiResponse.of(makeUpService.getComment(email, targetId, typeId)));
    }
}
