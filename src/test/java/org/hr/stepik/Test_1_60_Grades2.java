package org.hr.stepik;

import java.util.Scanner;

public class Test_1_60_Grades2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String students = in.nextLine();
        int count = 0, a = 0;
        for (int i = 0; i < students.length(); i++) {
            char c = students.charAt(i);
            if (c == 'A') {
                a++;
            } else if (c > 'A' && c <= 'F') {
                count++;
            }
        }
        System.out.println(((double) a) / (a + count));
    }
}
