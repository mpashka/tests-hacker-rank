package org.hr.interview.company1;

import java.util.HashMap;
import java.util.Map;

public class Day3_2AnagramSearch {
/*
Найти подстроку в строке без учёта порядка символов
Сигнатура: String searchAnagram(String text, String pattern)
Примеры:
searchAnagram("XABCD", "BAC") == "ABC"
searchAnagram("XABCAD", "BACA") == "ABCA"
searchAnagram("XABCD", "BAD") == null

searchAnagram("A", "A") == "A"
*/

    String searchAnagram(String text, String pattern) {
        Map<Character, int[]> patternKey = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            patternKey.computeIfAbsent(pattern.charAt(i), c -> new int[1])[0]++;
        }

        Map<Character, int[]> key = new HashMap<>();
        int sameChars = 0;
        for (int i = 0; i < text.length(); i++) {
            if (i >= pattern.length()) {
                sameChars = add(key, patternKey, sameChars, text.charAt(i), true, text.charAt(i - pattern.length()));
            } else {
                sameChars = add(key, patternKey, sameChars, text.charAt(i), false, (char) 0);
            }
            if (sameChars == pattern.length()) {
                return text.substring(i - pattern.length() + 1, i + 1);
            }
        }
        return null;
    }

    int add(Map<Character, int[]> key, Map<Character, int[]> pattern, int sameChars, char cIn, boolean isCOut, char cOut) {
        {
            int[] keyCountIn = key.computeIfAbsent(cIn, c -> new int[1]);
            int[] patternCountIn = pattern.get(cIn);
            if (patternCountIn != null && patternCountIn[0] == keyCountIn[0]) {
                sameChars--;
            }
            keyCountIn[0]++;
            if (patternCountIn != null && patternCountIn[0] == keyCountIn[0]) {
                sameChars++;
            }
        }

        if (isCOut) {
            int[] keyCountOut = key.get(cOut);
            int[] patternCountOut = pattern.get(cOut);
            if (patternCountOut != null && patternCountOut[0] == keyCountOut[0]) {
                sameChars--;
            }
            keyCountOut[0]--;
            if (patternCountOut != null && patternCountOut[0] == keyCountOut[0]) {
                sameChars++;
            }
        }
        return sameChars;
    }

/*
    int changeCount(int[] keyCount, int delta, int sameCount) {
        int[] keyCountIn = key.computeIfAbsent(cIn, c -> new int[1]);
        int[] patternCountIn = pattern.get(c);
        if (patternCountIn != null && patternCountIn[0] == keyCountIn[0]) {
            sameChars--;
        }
        keyCountIn[0] += delta;
        if (patternCountIn != null && patternCountIn[0] == keyCountIn[0]) {
            sameChars++;
        }
    }
*/

}
