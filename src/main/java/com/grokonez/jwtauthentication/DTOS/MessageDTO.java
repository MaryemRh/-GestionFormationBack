package com.grokonez.jwtauthentication.DTOS;

public class MessageDTO {

	private int id;
    private String message;

    public MessageDTO( ) {
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
