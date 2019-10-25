
package com.example.timviec365.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Documment {

    @SerializedName("db_category")
    @Expose
    private List<DbCategory> dbCategory = null;
    @SerializedName("db_city")
    @Expose
    private List<DbCity> dbCity = null;
    @SerializedName("array_capbac")
    @Expose
    private ArrayCapbac arrayCapbac;
    @SerializedName("array_hinh_thuc")
    @Expose
    private ArrayHinhThuc arrayHinhThuc;
    @SerializedName("array_ngoai_ngu")
    @Expose
    private ArrayNgoaiNgu arrayNgoaiNgu;
    @SerializedName("array_kha")
    @Expose
    private List<String> arrayKha = null;
    @SerializedName("array_xl")
    @Expose
    private List<String> arrayXl = null;
    @SerializedName("array_gioi_tinh")
    @Expose
    private ArrayGioiTinh arrayGioiTinh;
    @SerializedName("array_hoc_van")
    @Expose
    private ArrayHocVan arrayHocVan;
    @SerializedName("array_hoc_van_uv")
    @Expose
    private ArrayHocVanUv arrayHocVanUv;
    @SerializedName("array_kinh_nghiem")
    @Expose
    private List<String> arrayKinhNghiem = null;
    @SerializedName("array_kinh_nghiem_uv")
    @Expose
    private List<String> arrayKinhNghiemUv = null;
    @SerializedName("array_cach_nop")
    @Expose
    private ArrayCachNop arrayCachNop;
    @SerializedName("array_muc_luong")
    @Expose
    private List<String> arrayMucLuong = null;
    @SerializedName("array_do_tuoi")
    @Expose
    private List<String> arrayDoTuoi = null;
    @SerializedName("array_quy_mo")
    @Expose
    private ArrayQuyMo arrayQuyMo;
    @SerializedName("array_quy_mo_com")
    @Expose
    private ArrayQuyMoCom arrayQuyMoCom;
    @SerializedName("array_loai_hinh")
    @Expose
    private ArrayLoaiHinh arrayLoaiHinh;
    @SerializedName("array_ngon_ngu")
    @Expose
    private List<String> arrayNgonNgu = null;

    public List<DbCategory> getDbCategory() {
        return dbCategory;
    }

    public void setDbCategory(List<DbCategory> dbCategory) {
        this.dbCategory = dbCategory;
    }

    public List<DbCity> getDbCity() {
        return dbCity;
    }

    public void setDbCity(List<DbCity> dbCity) {
        this.dbCity = dbCity;
    }

    public ArrayCapbac getArrayCapbac() {
        return arrayCapbac;
    }

    public void setArrayCapbac(ArrayCapbac arrayCapbac) {
        this.arrayCapbac = arrayCapbac;
    }

    public ArrayHinhThuc getArrayHinhThuc() {
        return arrayHinhThuc;
    }

    public void setArrayHinhThuc(ArrayHinhThuc arrayHinhThuc) {
        this.arrayHinhThuc = arrayHinhThuc;
    }

    public ArrayNgoaiNgu getArrayNgoaiNgu() {
        return arrayNgoaiNgu;
    }

    public void setArrayNgoaiNgu(ArrayNgoaiNgu arrayNgoaiNgu) {
        this.arrayNgoaiNgu = arrayNgoaiNgu;
    }

    public List<String> getArrayKha() {
        return arrayKha;
    }

    public void setArrayKha(List<String> arrayKha) {
        this.arrayKha = arrayKha;
    }

    public List<String> getArrayXl() {
        return arrayXl;
    }

    public void setArrayXl(List<String> arrayXl) {
        this.arrayXl = arrayXl;
    }

    public ArrayGioiTinh getArrayGioiTinh() {
        return arrayGioiTinh;
    }

    public void setArrayGioiTinh(ArrayGioiTinh arrayGioiTinh) {
        this.arrayGioiTinh = arrayGioiTinh;
    }

    public ArrayHocVan getArrayHocVan() {
        return arrayHocVan;
    }

    public void setArrayHocVan(ArrayHocVan arrayHocVan) {
        this.arrayHocVan = arrayHocVan;
    }

    public ArrayHocVanUv getArrayHocVanUv() {
        return arrayHocVanUv;
    }

    public void setArrayHocVanUv(ArrayHocVanUv arrayHocVanUv) {
        this.arrayHocVanUv = arrayHocVanUv;
    }

    public List<String> getArrayKinhNghiem() {
        return arrayKinhNghiem;
    }

    public void setArrayKinhNghiem(List<String> arrayKinhNghiem) {
        this.arrayKinhNghiem = arrayKinhNghiem;
    }

    public List<String> getArrayKinhNghiemUv() {
        return arrayKinhNghiemUv;
    }

    public void setArrayKinhNghiemUv(List<String> arrayKinhNghiemUv) {
        this.arrayKinhNghiemUv = arrayKinhNghiemUv;
    }

    public ArrayCachNop getArrayCachNop() {
        return arrayCachNop;
    }

    public void setArrayCachNop(ArrayCachNop arrayCachNop) {
        this.arrayCachNop = arrayCachNop;
    }

    public List<String> getArrayMucLuong() {
        return arrayMucLuong;
    }

    public void setArrayMucLuong(List<String> arrayMucLuong) {
        this.arrayMucLuong = arrayMucLuong;
    }

    public List<String> getArrayDoTuoi() {
        return arrayDoTuoi;
    }

    public void setArrayDoTuoi(List<String> arrayDoTuoi) {
        this.arrayDoTuoi = arrayDoTuoi;
    }

    public ArrayQuyMo getArrayQuyMo() {
        return arrayQuyMo;
    }

    public void setArrayQuyMo(ArrayQuyMo arrayQuyMo) {
        this.arrayQuyMo = arrayQuyMo;
    }

    public ArrayQuyMoCom getArrayQuyMoCom() {
        return arrayQuyMoCom;
    }

    public void setArrayQuyMoCom(ArrayQuyMoCom arrayQuyMoCom) {
        this.arrayQuyMoCom = arrayQuyMoCom;
    }

    public ArrayLoaiHinh getArrayLoaiHinh() {
        return arrayLoaiHinh;
    }

    public void setArrayLoaiHinh(ArrayLoaiHinh arrayLoaiHinh) {
        this.arrayLoaiHinh = arrayLoaiHinh;
    }

    public List<String> getArrayNgonNgu() {
        return arrayNgonNgu;
    }

    public void setArrayNgonNgu(List<String> arrayNgonNgu) {
        this.arrayNgonNgu = arrayNgonNgu;
    }

}
