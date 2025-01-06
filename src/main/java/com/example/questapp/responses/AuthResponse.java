package com.example.questapp.responses;

import lombok.Data;

@Data
public class AuthResponse {
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    String message;
    Long userId;
}
