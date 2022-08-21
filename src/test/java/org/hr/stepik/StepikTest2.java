package org.hr.stepik;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class StepikTest2 {

    private static final Logger log = LogManager.getLogger();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int h = scanner.nextInt();
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        log.info("H: {}, a: {}, b: {}", h, a, b);
    }

    /**
     * Snail creeps up the vertical pole of height H feets. Per day it goes A feets up, and per night it goes B
     * feets down. In which day the snail will reach the top of the pole?
     *
     * Input data format
     *
     * On the input the program receives non-negative integers H, A, B, where H > B and A > B. Every integer does not exceed 100.
     */
    @Test
    public void test1() {
        assertThat(findSnailDays(10, 3, 2), is(8));
        assertThat(findSnailDays(20, 7, 3), is(5));
    }

    public int findSnailDays(int h, int a, int b) {
        return 1 + (int) Math.ceil((double) (h - a) / (a - b));
    }

    /**
     * GC-content is an important feature of the genome sequences and is defined as the percentage ratio of the sum of all guanines and cytosines to the overall number of nucleic bases in the genome sequence.
     * Write a program, which calculates the percentage of G characters (guanine) and C characters (cytosine) in the entered string. Your program should be case independent.
     *
     * For example, in the string "acggtgttat" the percentage of characters G and C equals to \dfrac4{10} \cdot 100 = 40.0
     * 10
     * 4
     * ​
     *  ⋅100=40.0, where 4 is the number of symbols G and C, and 10 is the length of the string.
     *
     * Output your result as a double value.
     * Sample Input:
     *
     * acggtgttat
     * Sample Output:
     *
     * 40.0
     */
    @Test
    public void test2() {
        assertThat(findGCStructure("acggtgttat"), is(40.));
    }

    public double findGCStructure(String in) {
        int length = in.length();
        int gc = 0;
        for (int i = 0; i < length; i++) {
            switch (in.charAt(i)) {
                case 'C':
                case 'c':
                case 'G':
                case 'g':
                    gc++;
            }
        }
        return 100. * gc / length;
    }

    /**
     * The sum of digits of a three-digit number
     *
     * Given a three-digit integer (i.e. an integer from 100 to 999). Find the sum of its digits.
     *
     * Sample Input:
     *
     * 476
     * Sample Output:
     *
     * 17
     */
    @Test
    public void test3() {
        assertThat(digitsSum(476), is(17));
    }

    private int digitsSum(int number) {
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }

    /**
     * Digital watches
     *
     * Digital watches display time in the h:mm:ss format (from 0:00:00 to 23:59:59), i.e. first goes the number of hours, then goes the two-digit number of minutes, followed by the two-digit number of seconds. If necessary, number of minutes and seconds are filled by zeroes to a two-digit number.
     *
     * N seconds passed from the beginning of the day. Output what the watches will display.
     *
     * Input data format
     *
     * Given the natural number N on input, not exceeding 10^710
     * 7
     *   (10000000).
     *
     * Sample Input 1:
     *
     * 3602
     * Sample Output 1:
     *
     * 1:00:02
     * Sample Input 2:
     *
     * 129700
     * Sample Output 2:
     *
     * 12:01:40
     */
    @Test
    public void test4() {
        assertThat(time(3602), is("1:00:02"));
        assertThat(time(129700), is("12:01:40"));
    }

    private String time(int seconds) {
        int h = seconds / 3600 % 24;
        int m = seconds / 60 % 60;
        int s = seconds % 60;
        StringBuilder out = new StringBuilder(8);
        out.append(h).append(':');
        if (m < 10) out.append('0');
        out.append(m).append(':');
        if (s < 10) out.append('0');
        out.append(s);
        return out.toString();
    }

    /**
     * MKAD
     *
     * The length of the Moscow Ring Road (MKAD) is 109 kilometers. Biker Vasya starts from the zero kilometer of
     * MKAD and drives with a speed of V kilometers per hour. On which mark will he stop after T hours?
     *
     * Input data format
     *
     * The program gets integers V and T as input. If V > 0, then Vasya moves in a positive direction along MKAD,
     * if the value of V < 0 – in the negative direction. 0 ≤ T ≤ 1000, -1000 ≤ V ≤ 1000.
     *
     * Output data format
     *
     * The program should output an integer from 0 to 108 - the mark on which Vasya stops.
     *
     * Sample Input 1:
     *
     * 60
     * 2
     * Sample Output 1:
     *
     * 11
     * Sample Input 2:
     *
     * -1
     * 1
     * Sample Output 2:
     *
     * 108
     */
    @Test
    public void test5() {
        assertThat(findMKADPos(60, 2), is(11));
        assertThat(findMKADPos(-1, 1), is(108));
    }

    private int findMKADPos(int speed, int time) {
        return speed * time % 109 + (speed >= 0 ? 0 : 109);
    }



/*
import java.util.Scanner;
private static final Scanner scanner = new Scanner(System.in);
int h = scanner.nextInt();
String in = scanner.next();
 */
}
