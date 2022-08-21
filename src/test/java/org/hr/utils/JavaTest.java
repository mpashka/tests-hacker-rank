package org.hr.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class JavaTest {
    private static final Logger log = LogManager.getLogger();

    @Test
    public void testDiv() {
        for (int i = 0; i < 4; i++) {
            log.info("{} -> {}:{}", i, (i - 2) % 2, ((i+1) % 4 - 2) % 2);
        }

        for (int i = 0; i < 5; i++) {
            int kxdx = ((i+2)%4 - 2) % 2, kxdy = ((i+3) % 4 - 2) % 2, kydx = ((5-i) % 4 - 2) % 2, kydy = ((4-i) % 4 - 2) % 2;
            log.info("{} -> {}:{} , {}:{}", i, kxdx,kxdy, kydx,kydy);
        }
    }

    @Test
    public void testMin() {
        int i = Integer.MIN_VALUE;
        log.info("{}, min+1:{}, min:{}, max:{}", -Integer.MIN_VALUE, -(Integer.MIN_VALUE+1), Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    @Test
    public void stringArrayConverterTest() {
        StringArrayConverter converter = new StringArrayConverter();
        assertThat(converter.convert("abc", String[].class), is(new String[]{"abc"}));
        assertThat(converter.convert("a,bc", String[].class), is(new String[]{"a","bc"}));
        assertThat(converter.convert(",bc", String[].class), is(new String[]{"","bc"}));
        assertThat(converter.convert("bc,", String[].class), is(new String[]{"bc",""}));
        assertThat(converter.convert(",", String[].class), is(new String[]{"",""}));
    }
}
