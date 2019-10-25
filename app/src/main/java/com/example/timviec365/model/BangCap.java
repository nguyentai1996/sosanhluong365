package com.example.timviec365.model;

public class BangCap {
    public  String id_Exprence,name_Exprence;

    public BangCap(String id_Exprence, String name_Exprence) {
        this.id_Exprence = id_Exprence;
        this.name_Exprence = name_Exprence;
    }

    public String toString(){
        return name_Exprence;
    }

    public String getId_Exprence() {
        return id_Exprence;
    }

    public void setId_Exprence(String id_Exprence) {
        this.id_Exprence = id_Exprence;
    }

    public String getName_Exprence() {
        return name_Exprence;
    }

    public void setName_Exprence(String name_Exprence) {
        this.name_Exprence = name_Exprence;
    }
}
