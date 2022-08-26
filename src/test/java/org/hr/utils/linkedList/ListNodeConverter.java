package org.hr.utils.linkedList;

import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;

public class ListNodeConverter extends SimpleArgumentConverter {

    @Override
    public ListNode convert(Object source, Class<?> targetType) throws ArgumentConversionException {
        if (!ListNode.class.isAssignableFrom(targetType)) {
            throw new IllegalArgumentException("Conversion from " + source.getClass() + " to "
                    + targetType + " not supported.");
        }
        String str;
        if (source == null || (str = source.toString()).isBlank()) {
            return null;
        }
        return ListNode.fromString(str);
    }
}
