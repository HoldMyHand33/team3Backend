package com.team3.holdmyhand.global.error.exception;

import com.team3.holdmyhand.global.error.ErrorCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UnauthorizedException extends BusinessException {
    private String message;

    public UnauthorizedException(String message) {
        super(ErrorCode._BAD_REQUEST);
        this.message = message;
    }

    public UnauthorizedException(ErrorCode errorCode, String message) {
        super(errorCode);
        this.message = message;
    }

    public UnauthorizedException(ErrorCode errorCode) {
        super(errorCode);
    }
}