package leetcode;

import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 */
public class T20 {

    public static void main(String[] args) {
        System.out.println(isValid("{[]}"));
    }

    public static boolean isValid(String s) {
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            int a = s.charAt(i);
            if (a == '(' || a == '{' || a == '[') {
                stack.push(a);
                continue;
            }

            if (stack.empty()) {
                return false;
            }

            int b = stack.pop();
            if (a == ')' && b != '(') {
                return false;
            }

            if (a == '}' && b != '{') {
                return false;
            }

            if (a == ']' && b != '[') {
                return false;
            }
        }

        if (!stack.empty()) {
            return false;
        }

        return true;
    }
}

