
package com.example.timviec365.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataNews {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("alias")
    @Expose
    private String alias;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("created_day")
    @Expose
    private String createdDay;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("aliascat")
    @Expose
    private String aliascat;
    @SerializedName("idcat")
    @Expose
    private String idcat;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreatedDay() {
        return createdDay;
    }

    public void setCreatedDay(String createdDay) {
        this.createdDay = createdDay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAliascat() {
        return aliascat;
    }

    public void setAliascat(String aliascat) {
        this.aliascat = aliascat;
    }

    public String getIdcat() {
        return idcat;
    }

    public void setIdcat(String idcat) {
        this.idcat = idcat;
    }

}
