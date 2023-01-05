package org.hr.stepik;

import java.util.Scanner;

public class Test_1_18_Triangle {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a1 = in.nextInt();
        int a2 = in.nextInt();
        int a3 = in.nextInt();
        int max = Math.max(a1, Math.max(a2, a3));
        System.out.println(a1 + a2 + a3 - max * 2 > 0 ? "YES" : "NO");
    }

}
