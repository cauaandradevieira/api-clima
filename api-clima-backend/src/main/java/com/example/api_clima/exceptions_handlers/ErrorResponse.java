package com.example.api_clima.exceptions_handlers;

import java.time.Instant;

public class ErrorResponse
{
    private String message;
    private Integer status;
    private Instant date;

    public ErrorResponse(String message, Integer status)
    {
        this.message = message;
        this.status = status;
        this.date = Instant.now();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }
}
