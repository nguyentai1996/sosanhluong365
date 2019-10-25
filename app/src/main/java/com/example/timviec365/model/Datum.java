
package com.example.timviec365.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("x")
    @Expose
    private Integer x;
    @SerializedName("y")
    @Expose
    private Double y;
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("indexLabelFontColor")
    @Expose
    private String indexLabelFontColor;
    @SerializedName("indexLabel")
    @Expose
    private String indexLabel;
    @SerializedName("indexLabelFontSize")
    @Expose
    private Integer indexLabelFontSize;
    @SerializedName("markerColor")
    @Expose
    private String markerColor;
    @SerializedName("markerType")
    @Expose
    private String markerType;
    @SerializedName("markerBorderColor")
    @Expose
    private String markerBorderColor;
    @SerializedName("markerBorderThickness")
    @Expose
    private Integer markerBorderThickness;
    @SerializedName("markerSize")
    @Expose
    private Integer markerSize;

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getIndexLabelFontColor() {
        return indexLabelFontColor;
    }

    public void setIndexLabelFontColor(String indexLabelFontColor) {
        this.indexLabelFontColor = indexLabelFontColor;
    }

    public String getIndexLabel() {
        return indexLabel;
    }

    public void setIndexLabel(String indexLabel) {
        this.indexLabel = indexLabel;
    }

    public Integer getIndexLabelFontSize() {
        return indexLabelFontSize;
    }

    public void setIndexLabelFontSize(Integer indexLabelFontSize) {
        this.indexLabelFontSize = indexLabelFontSize;
    }

    public String getMarkerColor() {
        return markerColor;
    }

    public void setMarkerColor(String markerColor) {
        this.markerColor = markerColor;
    }

    public String getMarkerType() {
        return markerType;
    }

    public void setMarkerType(String markerType) {
        this.markerType = markerType;
    }

    public String getMarkerBorderColor() {
        return markerBorderColor;
    }

    public void setMarkerBorderColor(String markerBorderColor) {
        this.markerBorderColor = markerBorderColor;
    }

    public Integer getMarkerBorderThickness() {
        return markerBorderThickness;
    }

    public void setMarkerBorderThickness(Integer markerBorderThickness) {
        this.markerBorderThickness = markerBorderThickness;
    }

    public Integer getMarkerSize() {
        return markerSize;
    }

    public void setMarkerSize(Integer markerSize) {
        this.markerSize = markerSize;
    }

}
