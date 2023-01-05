package org.hr.stepik;

import java.util.Scanner;

public class Test1_10_NumberOfTens {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println((n / 10) % 10);
    }
}
