package org.hr.hackerrank.w26;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CountingValleys {

    /**
     * Complete the 'countingValleys' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER steps
     *  2. STRING path
     */
    public static int countingValleys(String path) {
        int height = 0;
        boolean valley = false;
        int valleys = 0;
        for (int i = 0; i < path.length(); i++) {
            boolean up = path.charAt(i) == 'U';
            if (height == 0 && !up) {
                valley = true;
            }
            height += up ? 1 : -1;
            if (valley && height == 0 && up) {
                valley = false;
                valleys++;
            }
        }
        return valleys;
    }

    public static int countingValleysWrong0(String path) {
        int height = 0;
        boolean prev = false;
        int step = 0;
        int valleys = 0;
        for (int i = 0; i < path.length(); i++) {
            boolean up = path.charAt(i) == 'U';
            boolean doub = prev == up;
            height += up ? 1 : -1;
            prev = up;
            if (i == 0) {
                continue;
            }
            if (step == 0 && doub && up == false) {
                step = 1;
            }
            if (step == 1 && height < 0) {
                step = 2;
            }
            if (step == 2 && up == true) {
                step = 0;
                valleys++;
            }
        }
        return valleys;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int steps = Integer.parseInt(bufferedReader.readLine().trim());

        String path = bufferedReader.readLine();

        int result = countingValleys(path);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

    @Test
    public void testKnown() {
        assertThat(countingValleys("UDDDUDUU"), is(1));
        assertThat(countingValleys("UDDUDUDU"), is(3));
        assertThat(countingValleys("DDUUUUDD"), is(1));
    }
}
