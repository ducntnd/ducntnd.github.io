package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MarkManager {
    ArrayList<Mark> markL;
    static List<List<String>> rowList = new ArrayList<>();
    ConsoleTable consoleTable = new ConsoleTable();
    List<String> headerList;
    Scanner sc = new Scanner(System.in);

    public MarkManager() {
        markL = new ArrayList<>();
    }

    public ArrayList<Mark> getMarkL() {
        return markL;
    }

    public void setMarkL(ArrayList<Mark> markL) {
        this.markL = markL;
    }

    public boolean addMark(Mark mark) {
        if (mark != null) {
            markL.add(mark);
            return true;
        } else {
            return false;
        }
    }

    public Mark inputMark(ArrayList<Students> std, ArrayList<Class> cl){
        Scanner sc = new Scanner(System.in);
        Mark mark = new Mark();
        System.out.println("");
        System.out.println("Chọn lớp");
        for (int i = 0; i < cl.size(); i++) {
            System.out.print((i + 1) + ". " + cl.get(i).getMalop() + "\t");
        }
        int chonMalop = 0;
        System.out.println();
        while (chonMalop == 0 || chonMalop > cl.size()) {
            System.out.print("#Chọn: ");
            chonMalop = sc.nextInt();
            if (chonMalop == 0 || chonMalop > cl.size()) {
                System.out.print("Chọn sai rồi, chọn lại nào: ");
                System.out.println();
            }
        }
        System.out.println("----------------------");
        System.out.println("Chọn sinh viên của lớp " + cl.get(chonMalop - 1).getMalop());
        int z = 1;
        ArrayList<Students> StdInClass = new ArrayList<>();
        for (int i = 0; i < std.size(); i++) {
            if (std.get(i).getMalop().equalsIgnoreCase(cl.get(chonMalop - 1).getMalop())) {
                System.out.print((z++) + ". " + std.get(i).getMSV() + "\t");
                StdInClass.add(std.get(i));
            }
        }
        System.out.println();
        int chonMSV = 0;
        while (chonMSV == 0 || chonMSV > StdInClass.size()) {
            System.out.print("#Chọn: ");
            chonMSV = sc.nextInt();
            if (chonMSV == 0 || chonMSV > StdInClass.size()) {
                System.out.print("Chọn sai rồi, chọn lại nào: ");
                System.out.println();
            }
        }
        mark.setMSV(chonMSV - 1, StdInClass);
        System.out.print("- Nhập môn thi: ");
        sc.nextLine();
        mark.setMonthi(sc.nextLine());
        int m=0;
        while (m==0) {
            System.out.print("- Nhập điểm: ");
            try {
                mark.setMark(sc.nextDouble());;
                if(mark.getMark()>=0&&mark.getMark()<=20) {
                    m++;
                }else {
                    System.out.println("Nhập điểm không hợp lệ");
                }
            }catch(Exception ex){
                System.out.println("Nhập số chứ không phải chữ, mời nhập lại");
            }
        }
        return mark;
    }

    public static void MarkMenu() {
        System.out.println("=======================================");
        System.out.println();
        System.out.println("Quản lý điểm thi");
        System.out.println();
        System.out.println("1.\tNhập điểm thi");
        System.out.println("2.\tHiển thị điểm thi theo lớp");
        System.out.println("3.\tHiển thị điểm thi theo môn");
        System.out.println("0.\tTrở về menu chính");
        System.out.println();
        System.out.print("#Chọn: ");
    }

    public void displayMarkTable() {
        headerList = new ArrayList<>();
        headerList.add("Mã lớp");
        headerList.add("Tên sinh viên");
        headerList.add("Môn thi");
        headerList.add("Điểm");
        headerList.add("Đánh giá");
    }

    public void outputInfo(Mark mark, Students std) {
        List<String> row = new ArrayList<>();
        row.add(std.getMalop());
        row.add(std.getTenSV());
        row.add(mark.getMonthi());
        row.add(Double.toString(mark.getMark()));
            if (mark.getMark() >= 10) {
                row.add("Đỗ");
            } else {
                row.add("Trượt");
            }
        rowList.add(row);
    }

    public void displayMarkbyClass(ArrayList<Students> stdL, ArrayList<Class> cl) {
        rowList.removeAll(rowList);
        System.out.println("--------- Điểm thi theo lớp ----------------");
        System.out.println();
        System.out.println("Chọn mã lớp");
        for (int i = 0; i < cl.size(); i++) {
            System.out.print((i + 1) + ". " + cl.get(i).getMalop() + "\t");
        }
        int chonMalop = 0;
        System.out.println();
        while (chonMalop == 0 || chonMalop > cl.size()) {
            System.out.print("#Chọn: ");
            chonMalop = sc.nextInt();
            if (chonMalop == 0 || chonMalop > cl.size()) {
                System.out.print("Chọn sai rồi, chọn lại nào: ");
                System.out.println();
            }
        }
        displayMarkTable();
        for (Students std : stdL) {
            if (std.getMalop().equalsIgnoreCase(cl.get(chonMalop - 1).getMalop())) {
                for (Mark mark : markL) {
                    if (mark.getMSV().equalsIgnoreCase(std.getMSV())) {
                        outputInfo(mark, std);
                    }
                }
            }
        }
        System.out.println(consoleTable.generateTable(headerList, rowList));
    }

    public void displayMarkbySubject(ArrayList<Students> stdL) {
        rowList.removeAll(rowList);
        System.out.println("--------- Điểm thi theo môn ----------------");
        System.out.println();
        displayMarkTable();
        System.out.print("- Nhập môn thi: ");
        String monthi = sc.nextLine();
        for (Mark mark : markL) {
            if (monthi.equalsIgnoreCase(mark.getMonthi())) {
                for (Students std : stdL) {
                    if(std.getMSV().equalsIgnoreCase(mark.getMSV())) {
                        outputInfo(mark, std);
                    }
                }
            }
        }
        System.out.println(consoleTable.generateTable(headerList, rowList));
    }

}
