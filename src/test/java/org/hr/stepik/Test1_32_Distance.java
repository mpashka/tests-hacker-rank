package org.hr.stepik;

import java.util.Scanner;

public class Test1_32_Distance {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int x1 = in.nextInt();
        int y1 = in.nextInt();
        int x2 = in.nextInt();
        int y2 = in.nextInt();
        System.out.println(x1 == x2 || y1 == y2 || Math.abs(x1 - x2) == Math.abs(y1 - y2) ? "YES" : "NO");
    }
}
