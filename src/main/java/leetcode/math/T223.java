package leetcode.math;

import java.util.Arrays;

/**
 * @author douzhitong
 * @date 2021/3/5
 */
public class T223 {

    public static void main(String[] args) {
        System.out.println(new T223().computeArea(-3, 0, 3, 4, 0, -1, 9, 2));
    }

    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int areaA = computeArea(A, B, C, D);
        int areaB = computeArea(E, F, G, H);
        int crossWidth = getCrossBound(A, C, E, G);
        int crossHeight = getCrossBound(B, D, F, H);
        int areaCross = crossWidth * crossHeight;
        return areaA + areaB - areaCross;
    }

    private int computeArea(int A, int B, int C, int D) {
        return Math.abs((C - A) * (D - B));
    }

    private int getCrossBound(int A, int B, int C, int D) {
        if (Math.min(A,B) > Math.max(C,D) || Math.min(C,D) > Math.max(A,B)) {
            return 0;
        }
        int[] arr = new int[4];
        arr[0] = A;
        arr[1] = B;
        arr[2] = C;
        arr[3] = D;
        Arrays.sort(arr);
        return Math.abs(arr[1] - arr[2]);
    }
}
