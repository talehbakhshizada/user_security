package com.company.usersecurity.exception;

import static com.company.usersecurity.exception.ErrorCode.EXISTS_BY_USERNAME;

public class ExistsByUsernameException extends GenericException{
    public ExistsByUsernameException(Object... arguments) {
        super(EXISTS_BY_USERNAME.code, EXISTS_BY_USERNAME.code, 404, arguments);
    }
}
