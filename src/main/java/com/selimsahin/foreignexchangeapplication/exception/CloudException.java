package com.selimsahin.foreignexchangeapplication.exception;

import lombok.Getter;

public class CloudException extends RuntimeException implements StandardException {
    @Getter
    private ExceptionCode code;

    public CloudException(String message) {
        super(message);
        this.code = HttpExceptionEnum.HTTP_OPERATION_FAILED;
    }

    public CloudException(ExceptionCode code, String message) {
        super(code.getCode().concat(":").concat(message));
        this.code = code;
    }

    public CloudException(ExceptionCode code, String message, Throwable throwable) {
        super(message, throwable);
        this.code = code;
    }

    public CloudException(ExceptionCode code) {
        super(code.getDesc());
        this.code = code;
    }

    @Override
    public ExceptionCode exceptionCode() {
        return this.code;
    }
}
