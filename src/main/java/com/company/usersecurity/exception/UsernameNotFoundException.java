package com.company.usersecurity.exception;

import static com.company.usersecurity.exception.ErrorCode.USER_NOT_FOUND;

public class UsernameNotFoundException extends GenericException{
    public UsernameNotFoundException(Object... arguments) {
        super(USER_NOT_FOUND.code, USER_NOT_FOUND.code,404,arguments);
    }

}
