package org.hr.stepik;

import java.util.Scanner;

public class Test1_15_LeapYear {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int year = in.nextInt();
        System.out.println((year % 4 == 0 && year % 100 > 0) || year % 400 == 0 ? "Leap" : "Regular");
    }
}
