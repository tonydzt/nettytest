package leetcode;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 * <p>
 * 说明：
 * <p>
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * 示例 1：
 * <p>
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 * 示例 2：
 * <p>
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *      注意你可以重复使用字典中的单词。
 * 示例 3：
 * <p>
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 */
public class T139 {

    private static List<String> failedList = new ArrayList<>();

    public static boolean wordBreak(String s, List<String> wordDict) {

        if (failedList.contains(s)) {
            return false;
        }

        for (String word : wordDict) {
            if (s.length() >= word.length()) {
                if (s.substring(0, word.length()).equals(word)
                        && (s.substring(word.length()).length() == 0 || wordBreak(s.substring(word.length()), wordDict))) {
                    return true;
                }
            }
        }

        failedList.add(s);
        return false;
    }

    public static void main(String[] args) {
        System.out.println(wordBreak("leetcode", Lists.newArrayList("leet","code")));
        System.out.println(wordBreak("applepenapple", Lists.newArrayList("apple","pen")));
        System.out.println(wordBreak("catsandog", Lists.newArrayList("cats", "dog", "sand", "and", "cat")));

    }
}
