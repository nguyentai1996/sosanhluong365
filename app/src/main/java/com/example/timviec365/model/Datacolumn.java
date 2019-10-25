
package com.example.timviec365.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datacolumn {

    @SerializedName("kq")
    @Expose
    private Boolean kq;
    @SerializedName("data")
    @Expose
    private Data data;

    public Boolean getKq() {
        return kq;
    }

    public void setKq(Boolean kq) {
        this.kq = kq;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
