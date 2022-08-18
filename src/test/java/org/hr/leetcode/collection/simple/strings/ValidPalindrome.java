package org.hr.leetcode.collection.simple.strings;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ValidPalindrome {
    private static final Logger log = LogManager.getLogger();

    public boolean isPalindrome(String s) {
        if (s.isEmpty()) {
            return true;
        }
        int bPos = 0, ePos = s.length()-1;
        do {
            char b = 0, e = 0;
            while (bPos < s.length()) {
                char c = s.charAt(bPos++);
                if (!Character.isLetterOrDigit(c)) {
                    continue;
                }
                b = Character.toLowerCase(c);
                break;
            }

            while (ePos >= 0) {
                char c = s.charAt(ePos--);
                if (!Character.isLetterOrDigit(c)) {
                    continue;
                }
                e = Character.toLowerCase(c);
                break;
            }

            if (b != e) {
//                log.info("{}({}): {}[{}] != {}[{}]", s, s.length(), b, bPos, e, ePos);
                return false;
            }
        } while (bPos < ePos);
        return true;
    }

    @ParameterizedTest
    @CsvSource(value = {
            "0P;false",
            "a.;true",
            "A man, a plan, a canal: Panama;true",
            "race a car;false",
            "_;true",
    }, delimiter = ';')
    public void test1(String s, boolean valid) {
        assertThat(isPalindrome(s), is(valid));
    }
}
