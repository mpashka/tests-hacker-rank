package org.hr.practice.ipk.dah;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

public class FrequencyQueries {

    // Complete the freqQuery function below.
    static List<Integer> freqQuery(List<List<Integer>> queries) {
        List<Integer> result = new ArrayList<>();
        // Key - number, value - count
        Map<Integer, Integer> count = new HashMap<>();
        // Key - frequency, value - count
        Map<Integer, Integer> freqsCount = new HashMap<>();
        for (List<Integer> query : queries) {
            int action = query.get(0);
            Integer number = query.get(1);
            if (action == 3) {
                Integer num = freqsCount.get(number);
                result.add(num != null && num > 0 ? 1 : 0);
            } else {
                count.compute(number, (n, oldCount) -> {
                    if (action == 2 && (oldCount == null || oldCount == 0)) {
                        return oldCount;
                    }
                    int newCount = (oldCount == null ? 0 : oldCount) + (action == 1 ? 1 : -1);
                    if (oldCount != null && oldCount != 0) {
                        freqsCount.compute(oldCount, (k, v) -> v != null ? v - 1 : null);
                    }
                    freqsCount.merge(newCount, 1, Integer::sum);
                    return newCount;
                });
            }
        }
        return result;
    }

/*
    static List<Integer> freqQueryUnoptimized(List<List<Integer>> queries) {
        List<Integer> result = new ArrayList<>();
        List<Integer> data = new ArrayList<>();
        for (List<Integer> query : queries) {
            int action = query.get(0);
            int number = query.get(1);
            if (action == 3) {
                Map<Integer, Integer> count = new HashMap<>();
                data.forEach(i -> count.merge(i, 1, Integer::sum));
                Map<Integer, Integer> freqsCount = new HashMap<>();
                count.values().forEach(i -> freqsCount.merge(i, 1, Integer::sum));
                result.add(num > 0 ? 1 : 0);
            } else {
                Integer newCount = count.merge(number, action == 1 ? 1 : -1, Integer::sum);
                int oldCount = newCount + (action == 1 ? -1 : 1);
                if (oldCount != 0) {
                    freqsCount.compute(oldCount, (k, v) -> v != null ? v - 1 : null);
                } else if (oldCount < 0) {
                    System.out.println("Neg");
                }
                freqsCount.merge(newCount, 1, Integer::sum);
            }
        }
        return result;
    }
*/

    public static void main(String[] args) throws IOException {
        List<Integer> ans = test(System.in);

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        bufferedWriter.write(
                ans.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );
        bufferedWriter.close();
    }

    private static List<Integer> test(InputStream in) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, q).forEach(i -> {
            try {
                queries.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> ans = freqQuery(queries);

        bufferedReader.close();
        return ans;
    }

    @Test
    public void testKnown() {
        assertThat(freqQuery(asList(
                asList(1,1),
                asList(2,2),
                asList(3,2),
                asList(1,1),
                asList(1,1),
                asList(2,1),
                asList(3,2)
        )), contains(0, 1));
        assertThat(freqQuery(asList(
                asList(1, 5),
                asList(1, 6),
                asList(3, 2),
                asList(1, 10),
                asList(1, 10),
                asList(1, 6),
                asList(2, 5),
                asList(3, 2)
        )), contains(0, 1));
        assertThat(freqQuery(asList(
                asList(3, 4),
                asList(2, 1003),
                asList(1, 16),
                asList(3, 1)
        )), contains(0, 1));
        assertThat(freqQuery(asList(
                asList(1, 3),
                asList(2, 3),
                asList(3, 2),
                asList(1, 4),
                asList(1, 5),
                asList(1, 5),
                asList(1, 4),
                asList(3, 2),
                asList(2, 4),
                asList(3, 2)
        )), contains(0, 1, 1));
    }

    @Test
    public void test08() throws IOException {

        try (InputStream in = new FileInputStream("src/test/resources/hr/practice/ipk/dah/FrequencyQueries-input08.txt");
             InputStream out = new FileInputStream("src/test/resources/hr/practice/ipk/dah/FrequencyQueries-output08.txt")) {
            List<Integer> result = test(in);
            List<Integer> expected = IOUtils.readLines(out, StandardCharsets.UTF_8).stream()
                    .filter(StringUtils::isNotBlank)
                    .map(Integer::parseInt)
                    .collect(toList());
            assertThat(result, is(expected));
        }
    }
}
