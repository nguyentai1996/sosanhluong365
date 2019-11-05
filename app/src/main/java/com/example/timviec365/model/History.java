package com.example.timviec365.model;

public class History {
    private String idMain;
    private String key;
    private String idCity;
    private String nameCity;

    public History(){

    }


    public History(String idMain, String key, String idCity, String nameCity) {
        this.idMain = idMain;
        this.key = key;
        this.idCity = idCity;
        this.nameCity = nameCity;
    }

    @Override
    public String toString() {
        return idMain;
    }

    public String getIdMain() {
        return idMain;
    }

    public void setIdMain(String idMain) {
        this.idMain = idMain;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getIdCity() {
        return idCity;
    }

    public void setIdCity(String idCity) {
        this.idCity = idCity;
    }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }
}


