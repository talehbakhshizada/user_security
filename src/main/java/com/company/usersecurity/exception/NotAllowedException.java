package com.company.usersecurity.exception;

import static com.company.usersecurity.exception.ErrorCode.NOT_ALLOWED;

public class NotAllowedException extends GenericException{
    public NotAllowedException(Object... arguments) {
        super(NOT_ALLOWED.code, NOT_ALLOWED.code,400,arguments);
    }
}
