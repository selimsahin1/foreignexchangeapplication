package com.selimsahin.foreignexchangeapplication.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

public class BaseResponse implements Serializable {

    protected String resultCode;

    protected String resultDesc;

    protected String message;

    public BaseResponse() {
        this.resultCode = HttpExceptionEnum.HTTP_OPERATION_SUCCESS.getCode();
        this.resultDesc = HttpExceptionEnum.HTTP_OPERATION_FAILED.getDesc();
    }

    public BaseResponse(ExceptionCode code) {
        if (null != code) {
            this.resultCode = code.getCode();
            this.resultDesc = code.getDesc();
        }
        this.message = null;
    }

    public BaseResponse(ExceptionCode code, String message) {
        this(code, message, null);
    }

    public BaseResponse(ExceptionCode code, String message, String argument) {
        if (null != code) {
            this.resultCode = code.getCode();
            this.resultDesc = code.getDesc();
        }
        this.message = message;

        if (StringUtils.isNotEmpty(this.resultDesc) && this.resultDesc.contains("%1s") && !StringUtils.isEmpty(
                argument)) {
            this.resultDesc = String.format(this.resultDesc, argument);
            this.message = this.message != null ? this.message : argument;
        }
    }

    public BaseResponse(String resultCode, String resultDesc) {
        this.resultCode = resultCode;
        this.resultDesc = resultDesc;
        this.message = null;
    }

    @JsonProperty("resultcode")
    public String getResultCode() {
        return resultCode;
    }

    @JsonProperty("resultcode")
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    @JsonProperty("resultdesc")
    public String getResultDesc() {
        return resultDesc;
    }

    @JsonProperty("resultdesc")
    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public String getMessage() {
        if ("0".equals(resultCode)) {
            return null;
        }
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "resultCode='" + resultCode + '\'' +
                ", resultDesc='" + resultDesc + '\'' +
                '}';
    }
}
