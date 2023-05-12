package org.hr.interview.company1;

public class Day3_1StrMod {
    /*
    Одна редакторская правка.
    Реализовать функцию, проверяющую, можно ли одну строку получить из другой не более, чем за одно исправление (удаление, добавление, изменение символа)
    Сигнатура: boolean oneEditApart(String s1, String s2)
    Сигнатура (kotlin): fun oneEditApart(s1: String, s2: String) -> Bool
    Примеры:
    oneEditApart("cat", "at") == true
    oneEditApart("cat", "cats") == true
    oneEditApart("cat", "cast") == true
    oneEditApart("cast", "cats") == false
    oneEditApart("cat", "cut") == true
    oneEditApart("cat", "dog") == false
    */
    boolean oneEditApart(String s1, String s2) {
        if (s1.length() == s2.length()) {
            return singleSymbol(s1, s2);
        } else if (s1.length() - 1 == s2.length()) {
            return extraSymbol(s1, s2);
        } else if (s2.length() - 1 == s1.length()) {
            return extraSymbol(s2, s1);
        } else {
            return false;
        }
    }

    boolean extraSymbol(String large, String str) {
        boolean found = false;
        for (int i = 0; i < large.length(); i++) {
            if (i == str.length() && !found) {
                return true;
            }
            char c2 = str.charAt(found ? i-1 : i);
            if (large.charAt(i) != c2) {
                if (found) {
                    return false;
                }
                found = true;
            }
        }
        return true;
    }

    boolean singleSymbol(String s1, String s2) {
        int diff = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (++diff > 1) {
                    return false;
                }
            }
        }
        return true;
    }

}
