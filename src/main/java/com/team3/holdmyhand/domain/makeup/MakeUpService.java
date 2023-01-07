package com.team3.holdmyhand.domain.makeup;

import com.team3.holdmyhand.domain.makeup.dto.GetCommentRes;
import com.team3.holdmyhand.domain.makeup.entity.Type;
import com.team3.holdmyhand.domain.member.MemberRepository;
import com.team3.holdmyhand.domain.member.entity.Member;
import com.team3.holdmyhand.global.error.ErrorCode;
import com.team3.holdmyhand.global.error.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MakeUpService {
    private final MakeUpRepository makeUpRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public GetCommentRes getComment(String email, int typeId) {
        try {
            Member member = memberRepository.findByEmail(email)
                    .orElseThrow(() -> new BadRequestException(ErrorCode.MEMBER_NOT_FOUND));

            Optional<Type> comment = makeUpRepository.findByTypeId(typeId);
            return new GetCommentRes(member, comment.get());
        } catch (NoSuchElementException noSuchElementException) {
            throw new NoSuchElementException(noSuchElementException.getMessage());
        }
    }
}
