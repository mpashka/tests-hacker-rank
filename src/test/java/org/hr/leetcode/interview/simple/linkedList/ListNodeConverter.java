package org.hr.leetcode.interview.simple.linkedList;

import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;

public class ListNodeConverter extends SimpleArgumentConverter {

    @Override
    protected ListNode convert(Object source, Class<?> targetType) throws ArgumentConversionException {
        if (ListNode.class.isAssignableFrom(targetType)) {
            if (source instanceof String) {
                String str = source.toString();
                if (str.isBlank()) {
                    return null;
                }
                ListNode first = null, last = null;
                for (String s : str.split("\\s*,\\s*")) {
                    int i = Integer.parseInt(s);
                    if (first == null) {
                        first = last = new ListNode(i);
                    } else {
                        last.next = new ListNode(i);
                        last = last.next;
                    }
                }
                return first;
            } else if (source == null) {
                return null;
            }
        }
        throw new IllegalArgumentException("Conversion from " + source.getClass() + " to "
                + targetType + " not supported.");
    }
}
