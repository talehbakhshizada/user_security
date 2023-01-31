package com.company.usersecurity.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ErrorCode {

    USER_NOT_FOUND("USER-NOT-FOUND"),
    INVALID_PASSWORD("PASSWORD-IS-INVALID"),
    EXISTS_BY_USERNAME("EXISTS-BY-USERNAME"),
    INVALID_CREDENTIALS("INVALID-CREDENTIALS"),
    EXPIRED_JWT("EXPIRED-JWT"),
    NOT_ALLOWED("NOT-ALLOWED");
    public final String code;
}

