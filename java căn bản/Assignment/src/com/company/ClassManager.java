package com.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ClassManager{
    ArrayList<Class> classL;

    static List<List<String>> rowsList = new ArrayList<>();

    public ClassManager() {
        this.classL = new ArrayList<>();
    }

    public ArrayList<Class> getClassL() {
        return classL;
    }

    public boolean addClass(Class cl) {
        if (cl != null) {
            classL.add(cl);
            return true;
        } else {
            return false;
        }
    }

    public static void menuClassManager() {
        System.out.println("=======================================");
        System.out.println();
        System.out.println("Quản lý lớp");
        System.out.println();
        System.out.println("1.\tXem danh sách lớp");
        System.out.println("2.\tCập nhật thông tin lớp");
        System.out.println("3.\tThêm mới một lớp");
        System.out.println("0.\tTrở về menu chính");
        System.out.println();
        System.out.print("#Chọn: ");
    }

    public void updateClass() {
        if(classL!=null&&classL.size()>0) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Chọn Mã lớp cần sửa");
            for (int i = 0; i < classL.size(); i++) {
                System.out.println((i + 1) + ". " + classL.get(i).getMalop());
            }
            int chonMalop = 0;
            while (chonMalop == 0 || chonMalop > classL.size()) {
                System.out.print("#Chọn: ");
                chonMalop = sc.nextInt();
                if (chonMalop == 0 || chonMalop > classL.size()) {
                    System.out.print("Chọn sai rồi, chọn lại nào: ");
                    System.out.println();
                }
            }
            classL.set(chonMalop - 1, inputUpdateClass(classL.get(chonMalop - 1).getMalop()));
        }else {
            System.out.println("Chưa có lớp để chỉnh sửa");
        }
    }


    public Class inputClass(){
        Class cl = new Class();
        Scanner sc = new Scanner(System.in);
        System.out.println("======== Thêm mới một lớp ======== ");
        System.out.println();
        System.out.print("- Nhập mã lớp: ");
        String Malop = sc.nextLine();
        if (classL.size()!=00) {
            for (int i = 0; i < classL.size(); i++) {
                if (classL.get(i).getMalop().equalsIgnoreCase(Malop)) {
                    System.out.print("Mã đã có rồi, mời nhập lại: ");
                    Malop = sc.nextLine();
                    i = -1;
                }
            }
        }
        cl.setMalop(Malop);
        System.out.print("- Nhập giảng viên: ");
        cl.setGiangvien(sc.nextLine());
        System.out.print("- Mô tả lớp: ");
        cl.setMotalop(sc.nextLine());
        int k=0;
        String thoigianhoc="";
        Date date=new Date();
        while (k==0){
            System.out.print("- Nhập thời gian bắt đầu học: ");
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
            try{
                date = formatter.parse(sc.nextLine());
                String timestart=formatter.format(date);
                thoigianhoc+=timestart+" - ";
                k++;
            }catch (ParseException e) {
                System.out.println("Nhập không đúng cú pháp, mời nhập lại");
            }
        }
        Date date1=new Date();
        while (k==1){
            System.out.print("Nhập thời gian tan học: ");
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
            try{
                date1 = formatter.parse(sc.nextLine());
                if(date1.compareTo(date)>0) {
                    String timeend = formatter.format(date1);
                    thoigianhoc += timeend;
                    k++;
                }else {
                    System.out.println("Thời gian kết thúc phải lớn hơn thời gian học");
                }
            }catch (ParseException e) {
                System.out.println("Nhập không đúng cú pháp, mời nhập lại");
            }
        }
        cl.setThoigianhoc(thoigianhoc);
        int m=0;
        int[] ngayhoc;
        int n=0;
        while (m==0) {
            System.out.print("- Nhập số buổi học: ");
            String d=sc.nextLine();
            try {
                n = Integer.parseInt(d);
                m++;
            }catch(NumberFormatException ex)
            {
                System.out.println("Nhập số chứ không phải chữ, mời nhập lại");
            }
        }
        ngayhoc = new int[n];
        int z=0;
        while(z==0) {
            try {
                System.out.println("Nhập các ngày học: ");
                for (int i = 0; i < n; i++) {
                    String d = sc.nextLine();
                    ngayhoc[i] = Integer.parseInt(d);
                }
                cl.setNgayhoc(ngayhoc);
                z++;
            }catch(NumberFormatException ex)
            {
                System.out.println("Nhập số chứ không phải chữ, mời nhập lại");
            }
        }
        return cl;
    }

    public static Class inputUpdateClass(String Malop) {
        Class cl = new Class();
        Scanner sc = new Scanner(System.in);
        cl.setMalop(Malop);
        System.out.println("======== Chỉnh sửa mã lớp " + Malop + " ======== ");
        System.out.println();
        System.out.print("- Chỉnh giảng viên: ");
        cl.setGiangvien(sc.nextLine());
        System.out.print("- Chỉnh Mô tả lớp: ");
        cl.setMotalop(sc.nextLine());
        int k=0;
        String thoigianhoc="";
        Date date=new Date();
        while (k==0){
            System.out.print("- Chỉnh thời gian bắt đầu học: ");
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
            try{
                date = formatter.parse(sc.nextLine());
                String timestart=formatter.format(date);
                thoigianhoc+=timestart+" - ";
                k++;
            }catch (ParseException e) {
                System.out.println("Nhập không đúng cú pháp, mời nhập lại");
            }
        }
        Date date1=new Date();
        while (k==1){
            System.out.print("Chỉnh thời gian tan học: ");
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
            try{
                date1 = formatter.parse(sc.nextLine());
                if(date1.compareTo(date)>0) {
                    String timeend = formatter.format(date1);
                    thoigianhoc += timeend;
                    k++;
                }else {
                    System.out.println("Thời gian kết thúc phải lớn hơn thời gian học");
                }
            }catch (ParseException e) {
                System.out.println("Nhập không đúng cú pháp, mời nhập lại");
            }
        }
        cl.setThoigianhoc(thoigianhoc);
        int m=0;
        int[] ngayhoc;
        int n=0;
        while (m==0) {
            System.out.print("- Chỉnh số buổi học: ");
            String d=sc.nextLine();
            try {
                n = Integer.parseInt(d);
                m++;
            }catch(NumberFormatException ex)
            {
                System.out.println("Nhập số chứ không phải chữ, mời nhập lại");
            }
        }
        ngayhoc = new int[n];
        int z=0;
        while(z==0) {
            try {
                System.out.println("Chỉnh các ngày học: ");
                for (int i = 0; i < n; i++) {
                    String d = sc.next();
                    ngayhoc[i] = Integer.parseInt(d);
                }
                cl.setNgayhoc(ngayhoc);
                z++;
            }catch(NumberFormatException ex)
            {
                System.out.println("Nhập số chứ không phải chữ, mời nhập lại");
            }
        }
        return cl;
    }

    public void displayClass() {
        rowsList.removeAll(rowsList);
        ConsoleTable consoleTable = new ConsoleTable();
        List<String> headerList = new ArrayList<>();
        headerList.add("Mã lớp");
        headerList.add("Mô tả lớp");
        headerList.add("Giảng viên");
        headerList.add("Giờ học");
        headerList.add("Ngày học");

        for (Class cl : classL) {
            if (cl != null) {
                outputInfo(cl);
            }
        }

        System.out.println(consoleTable.generateTable(headerList, rowsList));
    }

    public void outputInfo(Class cl) {
        List<String> row = new ArrayList<>();
        row.add(cl.getMalop());
        row.add(cl.getMotalop());
        row.add(cl.getGiangvien());
        String cacngayhoc = "";
        for (int i = 0; i < cl.getNgayhoc().length; i++) {
            if (i == cl.getNgayhoc().length - 1) {
                cacngayhoc += cl.getNgayhoc()[i];
            } else
                cacngayhoc += cl.getNgayhoc()[i] + " - ";
        }
        row.add(cl.getThoigianhoc());
        row.add(cacngayhoc);
        rowsList.add(row);
    }

}
