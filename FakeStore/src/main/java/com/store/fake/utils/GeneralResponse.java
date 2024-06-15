package com.store.fake.utils;

import java.time.LocalDateTime;

public class GeneralResponse {
    private int status;
    private String message;
    private Object data;
    private LocalDateTime requestDate;

    public GeneralResponse() {
    }

    public GeneralResponse(int status, String message, Object data, LocalDateTime requestDate) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.requestDate = requestDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }
}
