package org.hr.stepik;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class StepikTest1 {

    private static final Logger log = LogManager.getLogger();

    @Test
    public void test1() {
        List<IntPredicate> find1_3_5 = Arrays.asList(x -> x == 1, x -> x == 3, x -> x == 5);
        IntPredicate f135 = disjunctAll(find1_3_5);
        assertThat(f135.test(1), is(true));
        assertThat(f135.test(3), is(true));
        assertThat(f135.test(5), is(true));
        assertThat(f135.test(2), is(false));
        assertThat(f135.test(10), is(false));

        IntPredicate f0 = disjunctAll(Collections.emptyList());
        assertThat(f0.test(0), is(false));
        assertThat(f0.test(1000), is(false));
    }

    /**
     * The method represents a disjunct operator for a list of predicates.
     * For an empty list it returns the always false predicate.
     */
    public static IntPredicate disjunctAll(List<IntPredicate> predicates) {
        return x -> predicates.stream().anyMatch(p -> p.test(x));
    }

    //
    //
    //

    @Test
    public void testPalindrome() {
        assertThat(Stream.of("aaaa", "aaa", "a", "aa").collect(createPalindromeCollector()), is(new HashMap<>() {{
            put(false, Collections.emptyList());
            put(true, Arrays.asList("aaaa", "aaa", "a", "aa"));
        }}));
        assertThat(Stream.of("level", "bbaa", "ac").collect(createPalindromeCollector()), is(new HashMap<>() {{
            put(false, Arrays.asList("bbaa", "ac"));
            put(true, Arrays.asList("level"));
        }}));
        assertThat(Stream.of("aaaa", "aaa", "a", "aa").collect(createPalindromeCollector2()), is(new HashMap<>() {{
//            put(false, Collections.emptyList());
            put(true, Arrays.asList("aaaa", "aaa", "a", "aa"));
        }}));
        assertThat(Stream.of("level", "bbaa", "ac").collect(createPalindromeCollector2()), is(new HashMap<>() {{
            put(false, Arrays.asList("bbaa", "ac"));
            put(true, Arrays.asList("level"));
        }}));
        assertThat(Stream.of("aaaa", "aaa", "a", "aa").collect(createPalindromeCollector3()), is(new HashMap<>() {{
            put(false, Collections.emptyList());
            put(true, Arrays.asList("aaaa", "aaa", "a", "aa"));
        }}));
        assertThat(Stream.of("level", "bbaa", "ac").collect(createPalindromeCollector3()), is(new HashMap<>() {{
            put(false, Arrays.asList("bbaa", "ac"));
            put(true, Arrays.asList("level"));
        }}));
    }

    private Collector<String, Map<Boolean, List<String>>, Map<Boolean, List<String>>> createPalindromeCollector() {
        return new Collector<String, Map<Boolean, List<String>>, Map<Boolean, List<String>>>() {
            @Override
            public Supplier<Map<Boolean, List<String>>> supplier() {
                return () -> new HashMap<>() {{
                    put(true, new ArrayList<>());
                    put(false, new ArrayList<>());
                }};
            }

            @Override
            public BiConsumer<Map<Boolean, List<String>>, String> accumulator() {
                return (c, s) -> c.get(new StringBuilder(s).reverse().toString().equals(s)).add(s);
            }

            @Override
            public BinaryOperator<Map<Boolean, List<String>>> combiner() {
                return (c1, c2) -> {
                    Map<Boolean, List<String>> c = new HashMap<>();
                    addAll(c, c1);
                    addAll(c, c1);
                    return c;
                };
            }

            @Override
            public Function<Map<Boolean, List<String>>, Map<Boolean, List<String>>> finisher() {
                return i -> i;
            }

            @Override
            public Set<Characteristics> characteristics() {
                return Collections.unmodifiableSet(EnumSet.of(Characteristics.CONCURRENT,Characteristics.IDENTITY_FINISH));
            }

            private void addAll(Map<Boolean, List<String>> c, Map<Boolean, List<String>> c1) {
                c1.forEach((key, value) -> value.forEach(v -> c.get(key).add(v)));
            }
        };
    }

    private Collector<String, ?, Map<Boolean, List<String>>> createPalindromeCollector2() {
        return Collectors.groupingBy(s -> new StringBuilder(s).reverse().toString().equals(s));
    }

    private Collector<String, ?, Map<Boolean, List<String>>> createPalindromeCollector3() {
        return Collectors.partitioningBy(s -> new StringBuilder(s).reverse().toString().equals(s));
    }

    //
    //
    //

    @Test
    public void test5_3_intstream() {
        assertThat(createFilteringStream(1, 2, 3, 4).boxed().toArray(), emptyArray());
        assertThat(createFilteringStream(30, 75, 60, 90).boxed().collect(Collectors.toList()), contains(75, 90));
    }

    public static IntStream createFilteringStream(Integer... values) {
        return createFilteringStream(Stream.of(values).mapToInt(v -> v).filter(v -> v % 2 == 0), Stream.of(values).mapToInt(v -> v).filter(v -> v % 2 != 0));
    }

    public static IntStream createFilteringStream(IntStream evenStream, IntStream oddStream) {
        return Stream.concat(
                evenStream.filter(i -> i % 15 == 0).boxed(),
                oddStream.filter(i -> i % 15 == 0).boxed())
                .mapToInt(i -> i)
                .sorted()
                .sequential()
                .skip(2);
    }

    @Test
    public void testPP() {
        int a = 0;
        int b = a++ + a++ + a++;
        log.debug("A={}, b={}", a, b);
    }

}
