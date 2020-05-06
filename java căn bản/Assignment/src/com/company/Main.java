package com.company;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

public class Main{
    static ClassManager cl = new ClassManager();
    static StudentsManager std = new StudentsManager();
    static MarkManager mark = new MarkManager();

    public static void main(String[] args) throws IOException{
        System.out.println("--- Chào mừng đến với Techmaster Vietnam ---");
        System.out.println("");
        Gson gson=new Gson();
        File dir=new File("Data");
        if(!dir.exists()){
            dir.mkdir();
        }

        File file=new File("Data/"+"student.dat");
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        File file1=new File("Data/"+"class.dat");
        if (!file1.exists()){
            try {
                file1.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        File file2=new File("Data/"+"mark.dat");
        if (!file2.exists()){
            try {
                file2.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileReader fr=new FileReader("Data/student.dat");
        int i;
        String filestudent="";
        while((i=fr.read())!=-1) {
            filestudent+=(char) i;
        }
        fr.close();

        FileReader fr1=new FileReader("Data/class.dat");
        int z;
        String fileClass="";
        while((z=fr1.read())!=-1) {
            fileClass+=(char) z;
        }
        fr1.close();

        FileReader fr2=new FileReader("Data/mark.dat");
        int k;
        String fileMark="";
        while((k=fr2.read())!=-1) {
            fileMark+=(char) k;
        }
        fr2.close();

        if(filestudent!="") {
            Type objectype = new TypeToken<ArrayList<Students>>() {
            }.getType();
            std.student = gson.fromJson(filestudent, objectype);
        }

        if(fileClass!="") {
            Type objectype2 = new TypeToken<ArrayList<Class>>() {
            }.getType();
            cl.classL = gson.fromJson(fileClass, objectype2);
        }

        if(fileMark!="") {
            Type objectype3 = new TypeToken<ArrayList<Mark>>() {
            }.getType();
            mark.markL = gson.fromJson(fileMark, objectype3);
        }

        Scanner sc = new Scanner(System.in);
        mainmenu();
        int n;
        int choose;
        do {
            n = sc.nextInt();
            switch (n) {
                case 1:
                    std.menuStudents();
                    do {
                        choose = sc.nextInt();
                        switch (choose) {
                            case 1:
                                std.displayStudents();
                                System.out.println("Nhập bất kỳ để tiếp tục");
                                sc.nextLine();
                                sc.nextLine();
                                std.menuStudents();
                                break;
                            case 2:
                                boolean check = false;
                                while (check == false) {
                                    std.updateSTD(cl.getClassL());
                                    check = continuemenu();
                                }
                                std.menuStudents();
                                break;
                            case 3:
                                Students stds = new Students();
                                check = false;
                                while (check == false) {
                                    stds = std.inputSV(cl.getClassL());
                                    check = continuemenu();
                                }
                                boolean addstd = std.AddStudents(stds);
                                if (addstd == true) {
                                    System.out.println("Add thành công");
                                } else {
                                    System.out.println("Add thất bại");
                                }
                                std.menuStudents();
                                break;
                            case 0:
                                mainmenu();
                                break;
                        }
                    } while (choose != 0);
                    break;
                case 2:
                    cl.menuClassManager();
                    do {
                        choose = sc.nextInt();
                        switch (choose) {
                            case 1:
                                cl.displayClass();
                                System.out.println("Nhập bất kỳ để tiếp tục");
                                sc.nextLine();
                                sc.nextLine();
                                cl.menuClassManager();
                                break;
                            case 2:
                                boolean check = false;
                                while (check == false) {
                                    cl.updateClass();
                                    check = continuemenu();
                                }
                                cl.menuClassManager();
                                break;
                            case 3:
                                Class cls = new Class();
                                check = false;
                                while (check == false) {
                                    cls = cl.inputClass();
                                    check = continuemenu();
                                }
                                boolean addclass = cl.addClass(cls);
                                if (addclass == true) {
                                    System.out.println("Add thành công");
                                } else {
                                    System.out.println("Add thất bại");
                                }
                                cl.menuClassManager();
                                break;
                            case 0:
                                mainmenu();
                                break;
                        }
                    } while (choose != 0);
                    break;
                case 3:
                    mark.MarkMenu();
                    do {
                        choose = sc.nextInt();
                        switch (choose) {
                            case 1:
                                boolean check = false;
                                while (check == false) {
                                        mark.addMark(mark.inputMark(std.getStudent(), cl.getClassL()));
                                        check = continuemenu();
                                }
                                mark.MarkMenu();
                                break;
                            case 2:
                                mark.displayMarkbyClass(std.getStudent(), cl.getClassL());
                                System.out.println("Nhập bất kỳ để tiếp tục");
                                sc.nextLine();
                                sc.nextLine();
                                mark.MarkMenu();
                                break;
                            case 3:
                                mark.displayMarkbySubject(std.getStudent());
                                System.out.println("Nhập bất kỳ để tiếp tục");
                                sc.nextLine();
                                sc.nextLine();
                                mark.MarkMenu();
                                break;
                            case 0:
                                mainmenu();
                                break;
                        }
                    } while (choose != 0);
                    break;
                case 0:
                    break;
            }
        } while (n != 0);

        String jsonStudents= gson.toJson(std.getStudent());
        String jsonMark=gson.toJson(mark.getMarkL());
        String jsonClass=gson.toJson(cl.getClassL());

        FileWriter fileWriter=new FileWriter(file);
        fileWriter.write(jsonStudents);
        fileWriter.close();

        FileWriter fileWriter1=new FileWriter(file1);
        fileWriter1.write(jsonClass);
        fileWriter1.close();

        FileWriter fileWriter2=new FileWriter(file2);
        fileWriter2.write(jsonMark);
        fileWriter2.close();

    }

    public static void mainmenu() {
        System.out.println("=======================================");
        System.out.println();
        System.out.println("1.\tQuản lý danh sách sinh viên");
        System.out.println("2.\tQuản lý lớp học");
        System.out.println("3.\tQuản lý điểm thi");
        System.out.println("0.\tThoát");
        System.out.println();
        System.out.print("#Chọn: ");
    }

    public static boolean continuemenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.print("Bạn muốn tiếp tục?(Y/N): ");
        String n = null;
        n = sc.nextLine();
        switch (n) {
            case "y":
                return true;
            default:
                System.out.println("Nhập lại");
                return false;
        }
    }
}
