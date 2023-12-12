package stu.edu.vn.onthicuoiky.models;

import java.io.Serializable;

public class SinhVien implements Serializable {
    int MaSV;
    String TenSV;

    String gioitinh;

    public SinhVien(int maSV, String tenSV, String gioitinh) {
        MaSV = maSV;
        TenSV = tenSV;
        this.gioitinh = gioitinh;
    }

    public int getMaSV() {
        return MaSV;
    }

    public void setMaSV(int maSV) {
        MaSV = maSV;
    }

    public String getTenSV() {
        return TenSV;
    }

    public void setTenSV(String tenSV) {
        TenSV = tenSV;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    @Override
    public String toString() {
        return "SinhVien{" +
                "MaSV=" + MaSV +
                ", TenSV='" + TenSV + '\'' +
                ", gioitinh='" + gioitinh + '\'' +
                '}';
    }
}
