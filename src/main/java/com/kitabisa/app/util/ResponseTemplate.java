package com.kitabisa.app.util;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseTemplate {

    private Boolean status;
    private String message;
    private Integer code;

    public ResponseTemplate(Boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseTemplate(Boolean status, String message, Integer code) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

}
