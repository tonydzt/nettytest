package leetcode.str;

/**
 * 段式回文 其实与 一般回文 类似，只不过是最小的单位是 一段字符而不是 单个字母。
 *
 * 举个例子，对于一般回文 "abcba" 是回文，而 "volvo" 不是，但如果我们把"volvo" 分为 "vo"、"l"、"vo" 三段，则可以认为 “(vo)(l)(vo)” 是段式回文（分为 3 段）。
 *
 * 给你一个字符串text，在确保它满足段式回文的前提下，请你返回 段 的最大数量k。
 *
 * 如果段的最大数量为k，那么存在满足以下条件的a_1, a_2, ..., a_k：
 *
 * 每个a_i都是一个非空字符串；
 * 将这些字符串首位相连的结果a_1 + a_2 + ... + a_k和原始字符串text相同；
 * 对于所有1 <= i <= k，都有a_i = a_{k+1 - i}。
 * 
 * 示例 1：
 *
 * 输入：text = "ghiabcdefhelloadamhelloabcdefghi"
 * 输出：7
 * 解释：我们可以把字符串拆分成 "(ghi)(abcdef)(hello)(adam)(hello)(abcdef)(ghi)"。
 * 示例 2：
 *
 * 输入：text = "merchant"
 * 输出：1
 * 解释：我们可以把字符串拆分成 "(merchant)"。
 * 示例 3：
 *
 * 输入：text = "antaprezatepzapreanta"
 * 输出：11
 * 解释：我们可以把字符串拆分成 "(a)(nt)(a)(pre)(za)(tpe)(za)(pre)(a)(nt)(a)"。
 * 示例 4：
 *
 * 输入：text = "aaa"
 * 输出：3
 * 解释：我们可以把字符串拆分成 "(a)(a)(a)"。
 *
 * 提示：
 *
 * text仅由小写英文字符组成。
 * 1 <= text.length <= 1000
 * 
 * @author douzhitong
 * @date 2021/3/10
 */
public class T1147 {

    public static void main(String[] args) {
        System.out.println(new T1147().longestDecomposition("ghiabcdefhelloadamhelloabcdefghi"));
        System.out.println(new T1147().longestDecomposition("merchant"));
        System.out.println(new T1147().longestDecomposition("antaprezatepzapreanta"));
        System.out.println(new T1147().longestDecomposition("aaa"));

    }

    public int longestDecomposition(String text) {
        return longestDecomposition(text, 0, text.length() - 1);
    }

    public int longestDecomposition(String text, int fromIndex, int endIndex) {
        if (fromIndex > endIndex) {
            return 0;
        } else if (fromIndex == endIndex) {
            return 1;
        }

        for (int i = fromIndex; i < fromIndex + (endIndex - fromIndex + 1) / 2; i++) {
            if (text.charAt(i) == text.charAt(endIndex)) {
                boolean isEqual = true;
                for (int j = fromIndex; j <= i; j++) {
                    char left = text.charAt(j);
                    char right = text.charAt(endIndex - (i - fromIndex) + (j - fromIndex));
                    if (left != right) {
                        isEqual = false;
                        break;
                    }
                }
                if (isEqual) {
//                    System.out.println(text.substring(fromIndex, i + 1));
                    return 2 + longestDecomposition(text, i + 1, endIndex - (i - fromIndex) - 1);
                }
            }
        }
        return 1;
    }
}
