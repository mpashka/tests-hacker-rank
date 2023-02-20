package org.hr.yandex;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class Shame1 {

    public String removeSmile(String in) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < in.length(); i++) {
            if (i <= in.length() - 3 && in.charAt(i) == ':' && in.charAt(i+1) == '-') {
                char smile = in.charAt(i+2);
                if (smile == '(' || smile == ')') {
                    i = i + 3;
                    while (i < in.length() && in.charAt(i) == smile) {
                        i++;
                    }
                    i--;
                    continue;
                }
            }
            out.append(in.charAt(i));
        }
        return out.toString();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "s:-))))x|sx",
            ":-:-(((())|:-))",
            "aaaaaaa|aaaaaaa",
            ":-(|",
    }, delimiter = '|')
    public void test(String in, String out) {
        assertThat(removeSmile(in), is(out == null ? "" : out));
    }
}
