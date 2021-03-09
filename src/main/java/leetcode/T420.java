package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 一个强密码应满足以下所有条件：
 *
 * 由至少6个，至多20个字符组成。
 * 至少包含一个小写字母，一个大写字母，和一个数字。
 * 同一字符不能连续出现三次 (比如 "...aaa..." 是不允许的, 但是"...aa...a..." 是可以的)。
 * 编写函数strongPasswordChecker(s)，s 代表输入字符串，如果 s 已经符合强密码条件，则返回0；否则返回要将 s 修改为满足强密码条件的字符串所需要进行修改的最小步数。
 *
 * 插入、删除、替换任一字符都算作一次修改。
 *
 * @author douzhitong
 * @date 2021/3/8
 */
public class T420 {

    public static void main(String[] args) {
        System.out.println(new T420().strongPasswordChecker("aabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaa"));
        System.out.println(new T420().strongPasswordChecker("aaaB1"));
        System.out.println(new T420().strongPasswordChecker("aaa123"));
        System.out.println(new T420().strongPasswordChecker("aaa111"));
        System.out.println(new T420().strongPasswordChecker("aaaaAAAAAA000000123456"));
        System.out.println(new T420().strongPasswordChecker("QQQQQ"));
        System.out.println(new T420().strongPasswordChecker("aaaaaaaAAAAAA6666bbbbaaaaaaABBC"));


//        System.out.println(new T420().strongPasswordChecker("aabaabaabaabaaabaaba"));
//        System.out.println(new T420().strongPasswordChecker("aabaabaaBaa1aabaabaa"));
    }

    public int strongPasswordChecker(String password) {
        int total = 0;
        boolean hasNumber = false;
        boolean hasLowerCase = false;
        boolean haseUpperCase = false;
        Character preChar = null;
        int duplicate = 1;
        int useAdd = 0;
        int useDelete = 0;
        int useUpdate = 0;
        List<Integer> sanN1 = new ArrayList<>();
        List<Integer> sanN2 = new ArrayList<>();
        List<Integer> sanN = new ArrayList<>();
        if (password.length() > 20) {
            total += (password.length() - 20);
            useDelete += (password.length() - 20);
        }
        if (password.length() < 6) {
            total += (6 - password.length());
            useAdd += (6 - password.length());
        }

        for (int i = 0; i < password.length(); i++) {
            Character current = password.charAt(i);
            if (preChar == null) {
                preChar = current;
            } else if (current.equals(preChar)) {
                duplicate++;
            }

            if (i == password.length() - 1 || !current.equals(preChar)) {
                if (duplicate > 2) {
                    int yu = duplicate % 3;
                    if (yu == 0) {
                        sanN.add(duplicate);
                    } else if (yu == 1) {
                        sanN1.add(duplicate);
                    } else {
                        sanN2.add(duplicate);
                    }
                }
                duplicate = 1;
                preChar = current;
            }

            if (current >= '0' && current <= '9') {
                hasNumber = true;
            }
            if (current >= 'a' && current <= 'z') {
                hasLowerCase = true;
            }
            if (current >= 'A' && current <= 'Z') {
                haseUpperCase = true;
            }
        }

        sanN.addAll(sanN1);
        sanN.addAll(sanN2);

        for (int i = 0; i < sanN.size(); i++) {
            //先把重复字符串优化到3N + 2的最优结构
            int duplicateInner = sanN.get(i);
            int bestFirst = duplicateInner % 3 == 2 ? 0 : duplicateInner % 3 == 1 ? 2 : 1;
            if (useDelete >= bestFirst) {
                useDelete -= bestFirst;
                duplicateInner -= bestFirst;
                sanN.set(i, duplicateInner);
            }
        }

        for (Integer duplicateInner : sanN) {
            if (duplicateInner >= 3 && useDelete >= 3) {
                int delete = Math.min(duplicateInner, useDelete) / 3 * 3;
                useDelete -= delete;
                duplicateInner -= delete;
            }

            if (duplicateInner > 2) {
                duplicateInner -= 2 * useAdd;
            }
            if (duplicateInner > 2) {
                total += duplicateInner / 3;
                useUpdate += duplicateInner / 3;
            }
        }

        if (!hasNumber) {
            if (useAdd <= 0 && useUpdate <= 0) {
                total++;
            }
            if (useAdd > 0) {
                useAdd--;
            }else if (useUpdate > 0) {
                useUpdate--;
            }
        }
        if (!hasLowerCase) {
            if (useAdd <= 0 && useUpdate <= 0) {
                total++;
            }
            if (useAdd > 0) {
                useAdd--;
            }else if (useUpdate > 0) {
                useUpdate--;
            }
        }
        if (!haseUpperCase) {
            if (useAdd <= 0 && useUpdate <= 0) {
                total++;
            }
        }
        return total;
    }
}
