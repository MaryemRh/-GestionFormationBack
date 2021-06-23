package com.grokonez.jwtauthentication.DTOS;


public class NotificationDTO {
    private int id;
    private String message;

    public NotificationDTO( ) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
