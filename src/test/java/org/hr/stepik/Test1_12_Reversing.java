package org.hr.stepik;

import java.util.Scanner;

public class Test1_12_Reversing {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        char[] chars = String.valueOf(num).toCharArray();
        int result = 0, mult = 1;
        for (int i = 0; i < chars.length; i++) {
            result += (chars[i] - '0') * mult;
            mult *= 10;
        }
        System.out.println(result);
    }
}
