package org.hr.stepik;
import java.util.Scanner;

/**
 * Given the sequence of numbers, ending with number 0. Find the sum of all these numbers without using a loop.
 *
 * Sample Input:
 *
 * 1
 * 7
 * 9
 * 0
 * Sample Output:
 *
 * 17
 */
public class StepikTest2Noloop {
    private static final Scanner scanner = new Scanner(System.in);

    private static int sum(int prev) {
        int n = scanner.nextInt();
        return n == 0 ? prev : sum(prev + n);
    }

    public static void main(String[] args) {
        System.out.println(sum(0));
    }
}
