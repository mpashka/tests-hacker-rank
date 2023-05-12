package org.hr.interview.company3;

import java.util.BitSet;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MyTest {

    public int findDuplicate(int[] in) {
        BitSet numbers = new BitSet();
        for (int i : in) {
            if (numbers.get(i)) {
                return i;
            }
            numbers.set(i);
        }
        throw new IllegalArgumentException("No duplicates in input data");
    }

    @Test
    public void test() {
        assertThat(findDuplicate(new int[]{15, 3, 4, 5, 6, 7, 8, 3}), is(3));
    }
}
