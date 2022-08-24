package org.hr.utils;

import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;

import java.lang.reflect.Array;

public class ArrayConverter extends SimpleArgumentConverter {

    @Override
    protected Object convert(Object sourceObj, Class<?> targetType) throws ArgumentConversionException {
        if (!targetType.isArray()) {
            throw new IllegalArgumentException("Conversion from " + sourceObj.getClass() + " to "
                    + targetType + " not supported.");
        }
        if (sourceObj == null) {
            return Array.newInstance(targetType.getComponentType(), 0);
        }
        String src = String.valueOf(sourceObj).trim();
        if (src.charAt(0) != '[' || src.charAt(src.length()-1) != ']') {
            throw new IllegalArgumentException("Can't get matrix begin/end from " + src);
        }

        src = src.substring(1, src.length()-1);
/*
        return ARRAY_PATTERN.matcher(src)
                .results()
                .map(t -> t.group(1))
                .map(t -> ELEMENT_PATTERN.matcher(t)
                        .results()
                        .map(m -> m.group(1))
                        .mapToInt(Integer::parseInt)
                        .toArray())
                .toArray(int[][]::new);
*/
        return null;
    }

    private static class ParseContext {
        private Class<?> targetType;

        public ParseContext(Class<?> targetType) {
            this.targetType = targetType;
        }
    }

/*
        private static abstract class State {
            ParseContext context;
            ParseContext prevContext;

            public State(ParseContext context, ParseContext prevContext) {
                this.context = context;
                this.prevContext = prevContext;
            }

            abstract State next(char c);
        }

        private static class StateNone extends State {
            @Override
            public State next(char c) {
                if (c == ' ') {
                    return null;
                } else if (c == '[') {

                }
            }
        }

        private static class StateArray extends State {
            @Override
            public State next(char c) {
                if (c == ' ') {
                    return null;
                } else if (c == '[') {

                }
            }
        }

        private class StateValue implements State {
            private char delimiter;

            public StateValue(char delimiter) {
                this.delimiter = delimiter;
                value = new StringBuilder();
            }

            @Override
            public void next(char c) {

                return ;
            }
        }

        private class StateBegin implements State {
            @Override
            public void next(char c) {
                if (c == ' ') {
                } else if (c == '"' || c == '\'') {
                    setState(new StateValue(c));
                } else if (c == ',') {
                    skipValue();
                }
            }
        }
*/
}
