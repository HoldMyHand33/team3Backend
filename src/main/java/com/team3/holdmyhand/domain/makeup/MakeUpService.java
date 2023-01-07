package com.team3.holdmyhand.domain.makeup;

import com.team3.holdmyhand.domain.makeup.dto.GetCommentRes;
import com.team3.holdmyhand.domain.makeup.dto.GetTargetsRes;
import com.team3.holdmyhand.domain.makeup.entity.Target;
import com.team3.holdmyhand.domain.makeup.entity.Type;
import com.team3.holdmyhand.domain.makeup.repository.TargetRepository;
import com.team3.holdmyhand.domain.makeup.repository.TypeRepository;
import com.team3.holdmyhand.domain.member.MemberRepository;
import com.team3.holdmyhand.domain.member.entity.Member;
import com.team3.holdmyhand.global.error.ErrorCode;
import com.team3.holdmyhand.global.error.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MakeUpService {
    private final TypeRepository typeRepository;
    private final TargetRepository targetRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public List<GetTargetsRes> getTargets(String email) {
        try {
            Member member = memberRepository.findByEmail(email)
                    .orElseThrow(() -> new BadRequestException(ErrorCode.MEMBER_NOT_FOUND));

            List<Target> targets = targetRepository.findAllBy();

            return targets.stream()
                    .map(d -> GetTargetsRes.builder()
                            .targetId(d.getTargetId())
                            .targetText(d.getTarget()).build())
                    .collect(Collectors.toList());
        } catch (NoSuchElementException noSuchElementException) {
            throw new NoSuchElementException(noSuchElementException.getMessage());
        }
    }

    @Transactional
    public GetCommentRes getComment(String email, int typeId) {
        try {
            Member member = memberRepository.findByEmail(email)
                    .orElseThrow(() -> new BadRequestException(ErrorCode.MEMBER_NOT_FOUND));

            Optional<Type> comment = typeRepository.findByTypeId(typeId);
            return new GetCommentRes(member, comment.get());
        } catch (NoSuchElementException noSuchElementException) {
            throw new NoSuchElementException(noSuchElementException.getMessage());
        }
    }
}
