package com.example.lab3.Models;
import java.util.Map;

public class ResponseModel {
    private int code;
    private String status;
    private Map<String, Object> data;
    private String message;

    public ResponseModel() {

    }

    public ResponseModel(int code, String status, Map<String, Object> data, String message) {
        this.code = code;
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setStatus(String status) {
        this.status = status;
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
