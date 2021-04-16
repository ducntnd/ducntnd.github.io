package com.company;

import java.util.ArrayList;

public class Mark {
    private String MSV;
    private String monthi;
    private double mark;

    public String getMSV() {
        return MSV;
    }

    public void setMSV(int i, ArrayList<Students> stdL) {
        this.MSV = stdL.get(i).getMSV();
    }

    public String getMonthi() {
        return monthi;
    }

    public void setMonthi(String monthi) {
        this.monthi = monthi;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }
}
