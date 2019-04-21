package com.company.Entities;
import com.company.Util.*;

import java.util.Map;

public class ResponseEntity {
    private int code;
    private String status;
    private Map<String, Object> data;
    private String message;

    public ResponseEntity() {

    }

    public ResponseEntity(int code, Map<String, Object> data) {
        this.code = code;
        this.status = code == StatusCodes.OK ? "success" : "error";
        this.data = data;
        this.message = ErrorMessages.getMessageFromCode(code);
    }

    public void setCode(int code) {
        this.code = code;
        this.status = code == StatusCodes.OK ? "success" : "error";
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public String getStatus() {
        return this.status;
    }

    public Map<String, Object> getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }
}
