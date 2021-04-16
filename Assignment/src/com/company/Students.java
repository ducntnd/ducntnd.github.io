package com.company;

import java.util.ArrayList;

public class Students {
    private String MSV;
    private String tenSV;
    private String diachi;
    private String ngaysinh;
    private String Malop;

    public String getMSV() {
        return MSV;
    }

    public void setMSV(String MSV) {
        this.MSV = MSV;
    }

    public String getTenSV() {
        return tenSV;
    }

    public void setTenSV(String tenSV) {
        this.tenSV = tenSV;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getMalop() {
        return Malop;
    }

    public void setMalop(int i, ArrayList<Class> classL) {
        this.Malop = classL.get(i).getMalop();
    }
}
