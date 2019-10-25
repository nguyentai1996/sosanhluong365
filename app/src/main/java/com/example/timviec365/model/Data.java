
package com.example.timviec365.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("RenderLuong")
    @Expose
    private List<RenderLuong> renderLuong = null;
    @SerializedName("RenderKinhNghiem")
    @Expose
    private List<RenderKinhNghiem> renderKinhNghiem = null;
    @SerializedName("RenderBangCap")
    @Expose
    private List<RenderBangCap> renderBangCap = null;

    public List<RenderLuong> getRenderLuong() {
        return renderLuong;
    }

    public void setRenderLuong(List<RenderLuong> renderLuong) {
        this.renderLuong = renderLuong;
    }

    public List<RenderKinhNghiem> getRenderKinhNghiem() {
        return renderKinhNghiem;
    }

    public void setRenderKinhNghiem(List<RenderKinhNghiem> renderKinhNghiem) {
        this.renderKinhNghiem = renderKinhNghiem;
    }

    public List<RenderBangCap> getRenderBangCap() {
        return renderBangCap;
    }

    public void setRenderBangCap(List<RenderBangCap> renderBangCap) {
        this.renderBangCap = renderBangCap;
    }

}
