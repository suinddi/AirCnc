package com.aircnc.web.enums;

public enum UserLoginResult {
    DELETE,
    EMAIL_NOT_VERIFIED,  //이메일 인증이 안됨
    FAILURE,
    SUCCESS,
    SUSPENDED
}
