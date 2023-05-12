package org.hr.interview.company2;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * An input string is valid if:
 * Open brackets must be closed by the same type of brackets.  （]
 * Open brackets must be closed in the correct order.  )(
 * Every close bracket has a corresponding open bracket of the same type.
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~`
 * Example 1:
 * Input: s = "()"
 * Output: true
 * ~~~~~~~~~~~~~~~
 * Example 2:
 * Input: s = "()[]{}"
 * Output: true
 * ~~~~~~~~~~~~~~~
 * Example 3:
 * Input: s = "(]"
 * Output: false
 * ~~~~~~~~~~~~~~~
 * Example 4:
 * Input: s = "(())"
 * Output:true
 * ~~~~~~~~~~~~~~~
 * Example 5:
 * Input: s = "([)]"
 * Output:false
 *
 * Stack
 */
public class CheckString {

    private static final Map<Character, Character> OPEN_BRACKETS = Map.of(
            '(', ')',
            '[', ']',
            '{', '}'
    );

    public boolean checkValid(String in) {
        Deque<Character> brackets = new LinkedList<>();
        for (int i = 0; i < in.length(); i++) {
            char c = in.charAt(i);
            Character oppositeClose = OPEN_BRACKETS.get(c);
            if (oppositeClose != null) {
                brackets.addFirst(oppositeClose);
                continue;
            }
            Character toClose = brackets.pollFirst();
            if (toClose == null || toClose != c) {
                return false;
            }
        }
        return brackets.isEmpty();
    }

    @Test
    public void test() {
        assertThat(checkValid("()"), is(true));
        assertThat(checkValid("()[]{}"), is(true));
        assertThat(checkValid("(]"), is(false));
        assertThat(checkValid("(())"), is(true));
        assertThat(checkValid("([)]"), is(false));
    }
}
