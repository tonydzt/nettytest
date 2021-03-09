package tianchi;

/**
 * @author douzhitong
 * @date 2021/2/2
 */
public class 亮起时间最长的灯 {

    public static void main(String[] args) {
        int[][] operation = new int[][]{{0,2},{1,5},{0,9},{2,15}};
        System.out.println(longestLightingTime(operation));
    }

    public static char longestLightingTime(int[][] operation) {

        long[] resultArr = new long[26];
        for (int i = 0; i < operation.length; i++) {
            int[] operationThis = operation[i];
            int takeTime = i == 0 ? operationThis[1] : operationThis[1] - operation[i-1][1];
            if (takeTime > resultArr[operationThis[0]]) {
                resultArr[operationThis[0]] = takeTime;
            }
        }

        int max = 0;

        for (int i = 0; i< resultArr.length; i++) {
            if (resultArr[i] > resultArr[max]) {
                max = i;
            }
        }
        return (char)('a' + max);
    }
}
