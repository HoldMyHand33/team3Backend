package com.team3.holdmyhand.domain.makeup;

import com.team3.holdmyhand.domain.makeup.dto.GetCommentRes;
import com.team3.holdmyhand.domain.makeup.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MakeUpService {
    private final MakeUpRepository makeUpRepository;

    public GetCommentRes getComment(int targetId, int typeId) {
        try {
            Optional<Comment> comment = makeUpRepository.findByTargetIdaAndTypeId(targetId, typeId);
            return new GetCommentRes(comment.get());
        } catch (NoSuchElementException noSuchElementException) {
            throw new NoSuchElementException(noSuchElementException.getMessage());
        }
    }
}
