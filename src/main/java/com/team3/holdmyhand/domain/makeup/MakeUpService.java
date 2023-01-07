package com.team3.holdmyhand.domain.makeup;

import com.team3.holdmyhand.domain.makeup.dto.GetCommentRes;
import com.team3.holdmyhand.domain.makeup.entity.Comment;
import com.team3.holdmyhand.domain.member.MemberRepository;
import com.team3.holdmyhand.domain.member.entity.Member;
import com.team3.holdmyhand.global.config.security.dto.TokenResponseDto;
import com.team3.holdmyhand.global.config.security.jwt.JwtFilter;
import com.team3.holdmyhand.global.config.security.jwt.TokenProvider;
import com.team3.holdmyhand.global.error.ErrorCode;
import com.team3.holdmyhand.global.error.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import springfox.documentation.annotations.ApiIgnore;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MakeUpService {
    private final MakeUpRepository makeUpRepository;
    private final MemberRepository memberRepository;

    public GetCommentRes getComment(String email, int targetId, int typeId) {
        try {
            Member member = memberRepository.findByEmail(email)
                    .orElseThrow(() -> new BadRequestException(ErrorCode.MEMBER_NOT_FOUND));

            Optional<Comment> comment = makeUpRepository.findCommentByTargetIdAndTypeId(targetId, typeId);
            return new GetCommentRes(member, comment.get());
        } catch (NoSuchElementException noSuchElementException) {
            throw new NoSuchElementException(noSuchElementException.getMessage());
        }
    }
}
