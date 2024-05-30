package com.java1504.ManagerUsers.response;

import java.time.LocalDateTime;

public class ResponseError extends ResponseData{
    public ResponseError(int status, String message,LocalDateTime localDateTime) {
        super(status, message,localDateTime);
    }
}
