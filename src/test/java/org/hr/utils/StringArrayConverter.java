package org.hr.utils;

import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;

import java.util.ArrayList;
import java.util.List;

public class StringArrayConverter extends SimpleArgumentConverter {

    private static final String[] EMPTY_ARRAY = new String[0];

    @Override
    protected String[] convert(Object source, Class<?> targetType) throws ArgumentConversionException {
        if (String[].class.isAssignableFrom(targetType)) {
            if (source instanceof String) {
                String str = String.valueOf(source);
                List<String> strs = new ArrayList<>();
                int comma, prev = 0;
                while (true) {
                    comma = str.indexOf(',', prev);
                    if (comma == -1) {
                        strs.add(str.substring(prev));
                        break;
                    }
                    strs.add(str.substring(prev, comma));
                    prev = comma+1;
                }
                return strs.toArray(String[]::new);
            } else if (source == null) {
                return EMPTY_ARRAY;
            }
        }
        throw new IllegalArgumentException("Conversion from " + source.getClass() + " to "
                + targetType + " not supported.");
    }
}
