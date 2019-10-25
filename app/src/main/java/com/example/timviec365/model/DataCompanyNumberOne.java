
package com.example.timviec365.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataCompanyNumberOne {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("ngaytao")
    @Expose
    private String ngaytao;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("companyname")
    @Expose
    private String companyname;
    @SerializedName("mucluong")
    @Expose
    private String mucluong;
    @SerializedName("urllogo")
    @Expose
    private String urllogo;
    @SerializedName("diachi")
    @Expose
    private String diachi;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(String ngaytao) {
        this.ngaytao = ngaytao;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getMucluong() {
        return mucluong;
    }

    public void setMucluong(String mucluong) {
        this.mucluong = mucluong;
    }

    public String getUrllogo() {
        return urllogo;
    }

    public void setUrllogo(String urllogo) {
        this.urllogo = urllogo;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

}
