
package com.example.timviec365.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DbCity {

    @SerializedName("cit_id")
    @Expose
    private String citId;
    @SerializedName("cit_name")
    @Expose
    private String citName;

    public String getCitId() {
        return citId;
    }

    public void setCitId(String citId) {
        this.citId = citId;
    }

    public String getCitName() {
        return citName;
    }

    public void setCitName(String citName) {
        this.citName = citName;
    }

}
