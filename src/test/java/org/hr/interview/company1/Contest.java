package org.hr.interview.company1;

import java.util.BitSet;
import java.util.Scanner;

public class Contest {
    public static void main0(String[] args) {
        Scanner myInput = new Scanner(System.in);
        int i1 = myInput.nextInt();
        int i2 = myInput.nextInt();
        System.out.println(i1 + i2);
    }

    public static void main(String[] args) {
        Scanner myInput = new Scanner(System.in);
        String j = myInput.nextLine();
        String s = myInput.nextLine();
        BitSet bits = new BitSet(16);
        for (int i = 0; i < j.length(); i++) {
            bits.set(j.charAt(i));
        }
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (bits.get(s.charAt(i))) {
                count++;
            }
        }
        System.out.println(count);
    }
}
