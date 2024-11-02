package org.musify.security;

public class AuthResponse {
    private String message;
    private String username;
    private String token;

    // Constructor
    public AuthResponse(String message, String username, String token) {
        this.message = message;
        this.username = username;
        this.token = token;
    }

    // Getters y Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
