package com.example.timviec365.model;

public class City {
    private String IdCity;
    private String NameCity;

    public City() {
    }

    public City(String idCity, String nameCity) {
        IdCity = idCity;
        NameCity = nameCity;
    }

    @Override
    public String toString() {
        return NameCity;
    }

    public String getIdCity() {
        return IdCity;
    }

    public void setIdCity(String idCity) {
        IdCity = idCity;
    }

    public String getNameCity() {
        return NameCity;
    }

    public void setNameCity(String nameCity) {
        NameCity = nameCity;
    }
}
