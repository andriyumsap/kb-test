package com.kitabisa.app.util;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DataResponse<T> extends ResponseTemplate {

    private final static String SUCCESS_MESSAGE = "Success";
    private final static Integer SUCCESS_CODE = 200;
    private final static Boolean SUCCESS_STATUS = true;

    private T data;

    public DataResponse() {
        super(SUCCESS_STATUS, SUCCESS_MESSAGE, SUCCESS_CODE);
    }

    public DataResponse(T data) {
        super(SUCCESS_STATUS, SUCCESS_MESSAGE, SUCCESS_CODE);
        this.data = data;
    }

    public DataResponse(T data, Boolean status, String msg) {
        super(status, msg, SUCCESS_CODE);
        this.data = data;
    }

    public DataResponse(T data, Integer code, String msg) {
        super(SUCCESS_STATUS, msg, code);
        this.data = data;
    }

    public DataResponse(T data, Integer code, String msg, Boolean status) {
        super(status, msg, code);
        this.data = data;
    }
    
    public DataResponse(Boolean status, String msg) {
        super(status, msg, SUCCESS_CODE);
    }

}
