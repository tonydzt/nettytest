package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author douzhitong
 * @date 2021/3/8
 */
public class T290 {

    public static void main(String[] args) {
        System.out.println(new T290().wordPattern("abba", "dog cat cat fish"));
        System.out.println(new T290().wordPattern("aaa", "aa aa aa aa"));
    }

    public boolean wordPattern(String pattern, String s) {
        Map<Character, Integer> cMap = new HashMap<>();
        Map<String, Integer> sMap = new HashMap<>();
        String[] strArr = s.split(" ");
        if (strArr.length != pattern.length()) {
            return false;
        }
        for (int i = 0; i < pattern.length(); i++) {
            Character c = pattern.charAt(i);
            if (cMap.get(c) == null) {
                if (sMap.get(strArr[i]) != null) {
                    return false;
                }
                cMap.put(c, i);
                sMap.put(strArr[i], i);
            } else {
                if (sMap.get(strArr[i]) == null) {
                    return false;
                }
                int cIndex = cMap.get(c);
                int sIndex = sMap.get(strArr[i]);
                if (cIndex != sIndex) {
                    return false;
                }
            }
        }

        return true;
    }
}
