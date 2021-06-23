package com.grokonez.jwtauthentication.DTOS;

public class DirectionEmployeeDTO {

    private int idDirection;
    private String nomDirection;

    public DirectionEmployeeDTO(int id, String nom) {
        this.idDirection = id;
        this.nomDirection = nom;
    }

    public int getIdDirection() {
        return idDirection;
    }

    public void setIdDirection(int idDirection) {
        this.idDirection = idDirection;
    }

    public String getNomDirection() {
        return nomDirection;
    }

    public void setNomDirection(String nomDirection) {
        this.nomDirection = nomDirection;
    }
}
