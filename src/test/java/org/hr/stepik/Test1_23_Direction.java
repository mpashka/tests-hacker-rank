package org.hr.stepik;

import java.util.Scanner;

public class Test1_23_Direction {
    private static final String[] text = {
            "do not move", "move up", "move down", "move left", "move right",
    };

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(n >= 0 && n < text.length ? text[n] : "error!");
    }
}
