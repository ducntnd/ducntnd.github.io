package com.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentsManager {
    ArrayList<Students> student;

    public ArrayList<Students> getStudent() {
        return student;
    }

    static List<List<String>> rowsList = new ArrayList<>();

    public StudentsManager() {
        this.student = new ArrayList<>();
    }

    public boolean AddStudents(Students std) {
        if (std != null) {
            student.add(std);
            return true;
        } else return false;
    }

    public static void menuStudents() {
        System.out.println("=======================================");
        System.out.println();
        System.out.println("Quản lý danh sách sinh viên");
        System.out.println();
        System.out.println("1.\tXem danh sách sinh viên");
        System.out.println("2.\tCập nhật thông tin sinh viên");
        System.out.println("3.\tThêm mới một sinh viên");
        System.out.println("0.\tTrở về menu chính");
        System.out.println();
        System.out.print("#Chọn: ");
    }

    public void updateSTD(ArrayList<Class> cl) {
        if(student!=null&&student.size()>0) {
            Scanner sc = new Scanner(System.in);
            System.out.println("=======================================");
            System.out.println("Chọn lớp");
            for (int i = 0; i < cl.size(); i++) {
                System.out.print((i + 1) + ". " + cl.get(i).getMalop() + "\t");
            }
            System.out.println();
            int chonMalop = 0;
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
            int choose = 0;
            ArrayList<Students> StdInClass = new ArrayList<>();
            for (int i = 0; i < student.size(); i++) {
                if (student.get(i).getMalop().equalsIgnoreCase(cl.get(chonMalop - 1).getMalop())) {
                    System.out.print((z++) + ". " + student.get(i).getMSV() + "\t");
                    StdInClass.add(student.get(i));
                }
            }
            System.out.println();
            int chonMSV = 0;
            while (chonMSV == 0 || chonMSV > StdInClass.size()) {
                System.out.print("#Chọn: ");
                chonMSV = sc.nextInt();
                if (chonMSV == 0 || chonMSV > StdInClass.size())
                    System.out.print("Chọn sai rồi, chọn lại nào: ");
                System.out.println();
            }
            System.out.println();
            String MSV = StdInClass.get(chonMSV - 1).getMSV();
            for (int i = 0; i < student.size(); i++) {
                if (MSV.equalsIgnoreCase(student.get(i).getMSV())) {
                    student.set(i, inputUpdateSV(cl, MSV));
                    break;
                }
            }
        }else {
            System.out.println("Chưa có sinh viên để chỉnh sửa");
        }
    }

    public Students inputSV(ArrayList<Class> cl) {
        if(cl!=null&&cl.size()>0) {
            Students std = new Students();
            Scanner sc = new Scanner(System.in);
            System.out.println("======== Thêm mới một sinh viên ======== ");
            System.out.println();
            System.out.print("- Nhập mã sinh viên: ");
            String MSV = sc.nextLine();
            if (student.size() != 0) {
                for (int i = 0; i < this.student.size(); i++) {
                    if (student.get(i).getMSV().equalsIgnoreCase(MSV)) {
                        System.out.print("Mã SV đã có rồi, mời nhập lại");
                        MSV = sc.nextLine();
                        i = -1;
                    }
                }
            }
            std.setMSV(MSV);
            System.out.print("- Nhập tên SV: ");
            std.setTenSV(sc.nextLine());
            System.out.print("- Nhập địa chỉ: ");
            std.setDiachi(sc.nextLine());
            System.out.print("- Nhập ngày sinh: ");
            int n = 0;
            int z = 0;
            while (n == 0) {
                String date = sc.nextLine();
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                df.setLenient(false);
                try {
                    df.parse(date);
                    std.setNgaysinh(date);
                    n++;
                } catch (ParseException e) {
                    System.out.println("Ngày tháng không hợp lệ, mời nhập lại");
                }
            }
            System.out.println("- Chọn mã lớp: ");
            for (int i = 0; i < cl.size(); i++) {
                System.out.print((i + 1) + ". " + cl.get(i).getMalop() + "\t");
            }
            System.out.println();
            System.out.print("#Chọn: ");
            while (z == 0) {
                int j = sc.nextInt();
                if (j == 0 || j > cl.size()) {
                    System.out.println("Chọn sai rồi, chọn lại đi");
                } else {
                    std.setMalop(j - 1, cl);
                    z++;
                }
            }
            return std;
        }else {
            System.out.println("Chưa có lớp, không thể cho sinh viên, mời nhập lớp trước");
            return null;
        }
    }

    public static Students inputUpdateSV(ArrayList<Class> cl, String MSV) {
        Students std = new Students();
        Scanner sc = new Scanner(System.in);
        std.setMSV(MSV);
        System.out.println("======== Chỉnh sửa sinh viên mã số: " + MSV + " ======== ");
        System.out.println();
        System.out.print("- Chỉnh tên SV: ");
        std.setTenSV(sc.nextLine());
        System.out.print("- Chỉnh địa chỉ: ");
        std.setDiachi(sc.nextLine());
        System.out.print("- Chỉnh ngày sinh: ");
        int n = 0;
        int z = 0;
        while (n == 0) {
            String date = sc.nextLine();
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            df.setLenient(false);
            try {
                df.parse(date);
                std.setNgaysinh(date);
                n++;
            } catch (ParseException e) {
                System.out.println("Ngày tháng không hợp lệ, mời nhập lại");
            }
        }
        System.out.println("- Chọn mã lớp: ");
        for (int i = 0; i < cl.size(); i++) {
            System.out.print((i + 1) + ". " + cl.get(i).getMalop() + "\t");
        }
        System.out.println();
        System.out.print("#Chọn: ");
        while (z == 0) {
            int j = sc.nextInt();
            if (j == 0 || j > cl.size()) {
                System.out.println("Chọn sai rồi, chọn lại đi");
            } else {
                std.setMalop(j - 1, cl);
                z++;
            }
        }
        return std;
    }

    public void displayStudents() {
        rowsList.removeAll(rowsList);
        ConsoleTable consoleTable = new ConsoleTable();

        List<String> headerList = new ArrayList<>();
        headerList.add("Mã SV");
        headerList.add("Họ và tên");
        headerList.add("Địa chỉ");
        headerList.add("Ngày sinh");
        headerList.add("Lớp");

        for (Students std : student) {
            if (std != null) {
                outputInfo(std);
            }
        }

        System.out.println(consoleTable.generateTable(headerList, rowsList));
    }

    public static void outputInfo(Students std) {
        List<String> row = new ArrayList<>();
        row.add(std.getMSV());
        row.add(std.getTenSV());
        row.add(std.getDiachi());
        row.add(std.getNgaysinh());
        row.add(std.getMalop());
        rowsList.add(row);
    }
}
