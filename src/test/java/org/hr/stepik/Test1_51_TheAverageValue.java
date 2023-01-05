package org.hr.stepik;

import java.util.Scanner;

public class Test1_51_TheAverageValue {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        double sum = 0;
        int count = 0;
        do {
            int next = in.nextInt();
            if (next == 0) {
                System.out.println(sum / count);
                return;
            }
            sum += next;
            count++;
        } while (true);
    }
}
