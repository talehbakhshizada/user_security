package com.company.usersecurity.exception;

import static com.company.usersecurity.exception.ErrorCode.INVALID_PASSWORD;

public class InvalidPasswordException extends GenericException{
    public InvalidPasswordException(Object... arguments) {
        super(INVALID_PASSWORD.code, INVALID_PASSWORD.code, 400, arguments);
    }

}
