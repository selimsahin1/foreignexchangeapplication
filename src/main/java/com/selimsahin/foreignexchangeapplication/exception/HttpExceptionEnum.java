package com.selimsahin.foreignexchangeapplication.exception;

public enum HttpExceptionEnum implements ExceptionCode {

    HTTP_OPERATION_SUCCESS("0", "Operation success."),

    HTTP_OPERATION_FAILED("1", "Operation failed,"),

    HTTP_INVALID_PARAMETER("4", "Invalid parameter."),

    HTTP_ENTER_CORRECT_CURRENCY("5", "Enter correnct currency.");

    private String code;

    private String desc;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    HttpExceptionEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "HttpExceptionEnum{" +
                "code='" + code + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
