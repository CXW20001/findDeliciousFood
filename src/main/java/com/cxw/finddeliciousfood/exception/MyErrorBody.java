package com.cxw.finddeliciousfood.exception;

import lombok.Data;

@Data
public class MyErrorBody {
    private int httpStatusCode;
    private String ErrorMessage;

    public MyErrorBody(int httpStatusCode, String ErrorMessage) {
        this.httpStatusCode = httpStatusCode;
        this.ErrorMessage = ErrorMessage;
    }
}
