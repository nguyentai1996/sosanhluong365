package com.example.timviec365.model;

public class Career {
    public String idCat, nameCat;

    public Career() {
    }

    public Career(String idCat, String nameCat) {
        this.idCat = idCat;
        this.nameCat = nameCat;
    }

    public String getIdCat() {
        return idCat;
    }

    public void setIdCat(String idCat) {
        this.idCat = idCat;
    }

    public String getNameCat() {
        return nameCat;
    }

    public void setNameCat(String nameCat) {
        this.nameCat = nameCat;
    }

    public String toString(){
        return nameCat;
    }
}
