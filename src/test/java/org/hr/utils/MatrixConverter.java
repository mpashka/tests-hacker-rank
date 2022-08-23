package org.hr.utils;

import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;

import java.util.regex.Pattern;

public class MatrixConverter extends SimpleArgumentConverter {

    private static final int[][] EMPTY_ARRAY = new int[0][];
    private static final Pattern ARRAY_PATTERN = Pattern.compile("\\[([^]]*)]");
    private static final Pattern ELEMENT_PATTERN = Pattern.compile("(\\d+)");

    @Override
    protected int[][] convert(Object source, Class<?> targetType) throws ArgumentConversionException {
        if (int[][].class.isAssignableFrom(targetType)) {
            if (source instanceof String) {
                String matrixSrc = String.valueOf(source).trim();
                if (matrixSrc.charAt(0) != '[' || matrixSrc.charAt(matrixSrc.length()-1) != ']') {
                    throw new IllegalArgumentException("Can't get matrix begin/end from " + matrixSrc);
                }
                matrixSrc = matrixSrc.substring(1, matrixSrc.length()-1);
                return ARRAY_PATTERN.matcher(matrixSrc)
                        .results()
                        .map(t -> t.group(1))
                        .map(t -> ELEMENT_PATTERN.matcher(t)
                                .results()
                                .map(m -> m.group(1))
                                .mapToInt(Integer::parseInt)
                                .toArray())
                        .toArray(int[][]::new);
            } else if (source == null) {
                return EMPTY_ARRAY;
            }
        }
        throw new IllegalArgumentException("Conversion from " + source.getClass() + " to "
                + targetType + " not supported.");
    }
}
