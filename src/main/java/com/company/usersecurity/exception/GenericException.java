package com.company.usersecurity.exception;

import lombok.Getter;

@Getter
public class GenericException extends RuntimeException {
    private final int status;
    private final String code;
    private final String message;
    private final transient Object[] arguments;

    public GenericException(String code, String message, int status, Object[] arguments) {
        super(message);
        this.status = status;
        this.code = code;
        this.message = message;
        this.arguments = arguments == null ? new Object[0] : arguments;
    }

}
