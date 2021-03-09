package leetcode;

import java.util.*;

/**
 * Graham's Scan法
 *
 * 在一个二维的花园中，有一些用 (x, y) 坐标表示的树。
 * 由于安装费用十分昂贵，你的任务是先用最短的绳子围起所有的树。
 * 只有当所有的树都被绳子包围时，花园才能围好栅栏。你需要找到正好位于栅栏边界上的树的坐标。
 *
 * 示例 1:
 * 输入: [[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]]
 * 输出: [[1,1],[2,0],[4,2],[3,3],[2,4]]
 *
 * 示例 2:
 * 输入: [[1,2],[2,2],[4,2]]
 * 输出: [[1,2],[2,2],[4,2]]
 *
 * 注意:
 * 所有的树应当被围在一起。你不能剪断绳子来包围树或者把树分成一组以上。
 * 输入的整数在 0 到 100 之间。
 * 花园至少有一棵树。
 * 所有树的坐标都是不同的。
 * 输入的点没有顺序。输出顺序也没有要求。
 */
public class T587 {

    public static void main(String[] args) {
//        int[][] points = new int[][]{{1,1},{2,2},{2,0},{2,4},{3,3},{4,2}};
        int[][] points = new int[][]{{1,2},{2,2},{4,2}};
//        int[][] points = new int[][]{{3,0},{4,0},{5,0},{6,1},{7,2},{7,3},{7,4},{6,5},{5,5},{4,5},{3,5},{2,5},{1,4},{1,3},{1,2},{2,1},{4,2},{0,3}};
        System.out.println(Arrays.deepToString(outerTrees(points)));
//        System.out.println(Arrays.toString(vector(new int[]{1, 3}, new int[]{2, 4})));
//        System.out.println(vectorCrossProduct(new int[]{1, 3}, new int[]{2, 4}));
//        System.out.println(Arrays.toString(getMinYpoint(points)));
//        System.out.println(getVectorCosWithX(new int[]{1, 1}));
    }

    public static int[][] outerTrees(int[][] points) {
        List<int[]> outerTrees = new ArrayList<>();
        // 1、在所有点中选取y坐标最小的一点H，当作基点。如果存在多个点的y坐标都为最小值，则选取x坐标最小的一点。
        int[] minYpoint = getMinYpoint(points);
//        outerTrees.add(minYpoint);

        // 2、计算其他各点与H点构成的向量，并根据与X轴夹角进行排序
        Point[] otherPoints = new Point[points.length - 1];
        int pointer = 0;
        for (int[] point : points) {
            if ((point[0] != minYpoint[0]) || (point[1] != minYpoint[1])) {
                Point otherPoint = new Point();
                otherPoint.point = point;
                otherPoint.lengthToH = (int) (Math.pow(point[0] - minYpoint[0], 2) + Math.pow(point[1] - minYpoint[1], 2));
                otherPoint.vectorToH = vector(minYpoint, point);
                otherPoint.cosWithX = getVectorCosWithX(otherPoint.vectorToH);
                otherPoint.isFirstLine = false;
                otherPoints[pointer++] = otherPoint;
            }
        }
        Arrays.sort(otherPoints);
        // 标记起始边，解决共线问题
        for (int i = 0; i < points.length - 1; i++) {
            if (i != 0 && otherPoints[i].cosWithX != otherPoints[i-1].cosWithX) {
                break;
            } else {
                otherPoints[i].isFirstLine = true;
            }
        }
        Arrays.sort(otherPoints);

        // 3、根据夹角由小至大排序后进行逆时针扫描
        Line[] lines = new Line[points.length];
        for (int i = 0; i <= otherPoints.length; i++) {
            int[] thisPoint = (i == otherPoints.length ? minYpoint : otherPoints[i].point);
            int[] prePoint = (i == 0 ? minYpoint : otherPoints[i-1].point);
            lines[i] = new Line(prePoint, thisPoint);
        }

        List<Line> resultLines = new ArrayList<>();
        for (int i = 0; i < lines.length; i++) {
            resultLines.add(lines[i]);
            if (resultLines.size() > 1) {
                while (vectorCrossProduct(resultLines.get(resultLines.size() - 1).vector, resultLines.get(resultLines.size() - 2).vector) < 0) {
                    int size = resultLines.size();
                    Line newLine = new Line(resultLines.get(size - 2).startPoint, resultLines.get(size - 1).endPoint);
                    resultLines.remove(size - 1);
                    resultLines.remove(size - 2);
                    resultLines.add(newLine);
                }
            }
        }
        for (Line line: resultLines) {
            outerTrees.add(line.startPoint);
        }

        return outerTrees.toArray(new int[0][]);
    }

    /**
     *  在所有点中选取y坐标最小的一点H，当作基点。
     *  如果存在多个点的y坐标都为最小值，则选取x坐标最小的一点。
     *
     * @param points 点集
     * @return y坐标最小的一点
     */
    public static int[] getMinYpoint(int[][] points) {
        int[] minYpoint = new int[2];
        minYpoint[0] = points[0][0];
        minYpoint[1] = points[0][1];
        for (int i = 1; i < points.length; i++) {
            int[] thisPoint = points[i];
            if (thisPoint[1] < minYpoint[1] || (thisPoint[1] == minYpoint[1] && thisPoint[0] <  minYpoint[0])) {
                minYpoint[0] = thisPoint[0];
                minYpoint[1] = thisPoint[1];
            }
        }
        return minYpoint;
    }

    /**
     * 计算向量
     *
     * @param pointA 点A
     * @param pointB 点B
     * @return 向量
     */
    public static int[] vector(int[] pointA, int[] pointB) {
        int[] vector = new int[2];
        vector[0] = pointB[0] - pointA[0];
        vector[1] = pointB[1] - pointA[1];
        return vector;
    }

    /**
     * 计算向量叉积
     *
     * @param vectorA 向量A
     * @param vectorB 向量B
     * @return 向量叉积
     */
    public static int vectorCrossProduct(int[] vectorA, int[] vectorB) {
        return vectorA[0] * vectorB[1] - vectorA[1] * vectorB[0];
    }

    /**
     * 计算向量点积
     *
     * @param vectorA 向量A
     * @param vectorB 向量B
     * @return 向量点积
     */
    public static int vectorDotProduct(int[] vectorA, int[] vectorB) {
        return vectorA[0] * vectorB[0] + vectorA[1] * vectorB[1];
    }

    /**
     * 计算向量与X轴余弦值
     *
     * @param vectorA 向量A
     * @return 向量与X轴余弦值
     */
    public static double getVectorCosWithX(int[] vectorA) {
        return getVectorCos(vectorA, null, Mode.WITHX);
    }

    /**
     * 计算向量余弦值
     *
     * @param vectorA 向量A
     * @param vectorB 向量B
     * @return 向量余弦值
     */
    public static double getVectorCos(int[] vectorA, int[] vectorB, Mode mode) {
        if (mode == Mode.WITHX) {
            vectorB = new int[]{1,0};
        } else if (mode == Mode.WITHY) {
            vectorB = new int[]{0,1};
        }

        double vectorCos = vectorDotProduct(vectorA, vectorB) / (getVectorModulus(vectorA) * getVectorModulus(vectorB));
        return Math.round(vectorCos*100000000) / (double)100000000;
    }

    public static double getVectorModulus(int[] vector) {
        return Math.sqrt(Math.pow(vector[0], 2) + Math.pow(vector[1], 2));
    }

    static class Point implements Comparable<Point> {
        public int[] point;
        public int[] vectorToH;
        public int lengthToH;
        public double cosWithX;
        public boolean isFirstLine;

        @Override
        public int compareTo(Point otherPoint) {
            int result = Double.compare(this.cosWithX, otherPoint.cosWithX);
            if (result == 0) {
                // 共线问题处理：非起始边按照从远道近排列，起始边按从近到远排列。
                result = isFirstLine ? Integer.compare(this.lengthToH, otherPoint.lengthToH) : Integer.compare(otherPoint.lengthToH, this.lengthToH);
            }
            return result;
        }
    }

    static class Line {
        public Line(int[] startPoint, int[] endPoint) {
            this.startPoint = startPoint;
            this.endPoint = endPoint;
            this.vector = vector(startPoint, endPoint);
        }

        public int[] startPoint;
        public int[] endPoint;
        public int[] vector;
    }

    static enum Mode {
        /**
         * 求向量余弦值模式
         */
        WITHX, WITHY, BETWEEN
    }
}
