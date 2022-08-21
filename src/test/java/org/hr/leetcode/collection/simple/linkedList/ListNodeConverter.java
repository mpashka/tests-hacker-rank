package org.hr.leetcode.collection.simple.linkedList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;

import java.util.Arrays;

public class ListNodeConverter extends SimpleArgumentConverter {
    private static final Logger log = LogManager.getLogger();

    @Override
    protected ListNode convert(Object source, Class<?> targetType) throws ArgumentConversionException {
        if (ListNode.class.isAssignableFrom(targetType)) {
            if (source instanceof String) {
                return Arrays.stream(source.toString().split("\\s*,\\s*"))
                        .mapToInt(Integer::parseInt)
                        .collect(ListNode::new, ListNode::append, ListNode::append);
            } else if (source == null) {
                return null;
            }
        }
        throw new IllegalArgumentException("Conversion from " + source.getClass() + " to "
                + targetType + " not supported.");
    }
}
