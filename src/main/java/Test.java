import java.util.ArrayList;
import java.util.List;

/**
 * @author douzhitong
 * @date 2019/1/2
 */
public class Test {

    public static void main(String[] args) {
//        char[][] arr = {
//                {'a', 'c', 'd', 'z'},
//                {'x', 't', 'r', 'o'},
//                {'f', 'r', 'o', 'o'}
//        };
//        String words = "actf";
//        System.out.println(hasWord(arr, words));
            Byte a = (byte)200;
            System.out.println(a);
    }

    public static boolean hasWord(char[][] arr, String words) {
        char first = words.charAt(0);
        for (int row = 0; row < arr.length; row++) {
            for (int col = 0; col < arr[0].length; col++) {
                if (first == arr[row][col]) {
                    List<String> everNode = new ArrayList<>();
                    everNode.add(row+","+col);
                    if (deepCheck(row, col, arr, everNode, words.substring(1))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean deepCheck(int lastRow, int lastCol, char[][] arr, List<String> everNode, String words) {
        if (words == null || words.length() == 0) {
            return true;
        }
        int[][] directs = {
                {-1,0}, // ↑
                {1,0},  // ↓
                {0,-1}, // ←
                {0,1}   // →
        };

        for (int[] direct: directs) {
            int row = lastRow + direct[0];
            int col = lastCol + direct[1];
            if (row >= 0 && row < arr.length && col >=0 && col < arr[0].length // 越界判断
                    && !everNode.contains(row + "," + col)
                    && words.charAt(0) == arr[row][col]) {
                everNode.add(row + "," + col);
                return deepCheck(row, col, arr, everNode, words.substring(1));
            }
        }
        return false;
    }

}
