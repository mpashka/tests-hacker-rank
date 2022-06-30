package org.hr.utils;

import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;

import java.util.Arrays;

public class IntArrayConverter extends SimpleArgumentConverter {

    private static final int[] EMPTY_ARRAY = new int[0];

    @Override
    protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
        if (int[].class.isAssignableFrom(targetType)) {
            if (source instanceof String) {
                return Arrays.stream(source.toString().split("\\s*,\\s*"))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            } else if (source == null) {
                return EMPTY_ARRAY;
            }
        }
        throw new IllegalArgumentException("Conversion from " + source.getClass() + " to "
                + targetType + " not supported.");
    }
}
