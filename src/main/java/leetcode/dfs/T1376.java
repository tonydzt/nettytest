package leetcode.dfs;

/**
 * 公司里有 n 名员工，每个员工的 ID 都是独一无二的，编号从 0 到 n - 1。公司的总负责人通过 headID 进行标识。
 *
 * 在 manager 数组中，每个员工都有一个直属负责人，其中 manager[i] 是第 i 名员工的直属负责人。对于总负责人，manager[headID] = -1。题目保证从属关系可以用树结构显示。
 *
 * 公司总负责人想要向公司所有员工通告一条紧急消息。他将会首先通知他的直属下属们，然后由这些下属通知他们的下属，直到所有的员工都得知这条紧急消息。
 *
 * 第 i 名员工需要 informTime[i] 分钟来通知它的所有直属下属（也就是说在 informTime[i] 分钟后，他的所有直属下属都可以开始传播这一消息）。
 *
 * 返回通知所有员工这一紧急消息所需要的 分钟数 。
 *
 * @author douzhitong
 * @date 2021/3/9
 */
public class T1376 {
    public static void main(String[] args) {
        System.out.println(new T1376().numOfMinutes(15,0,new int[]{-1,0,0,1,1,2,2,3,3,4,4,5,5,6,6}, new int[]{1,1,1,1,1,1,1,0,0,0,0,0,0,0,0}));
    }

    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {

        int[] count = new int[n];
        int max = 0;
        for (int i = 0; i < manager.length; i++) {
            // 只看叶子节点
            if (informTime[i] == 0) {
                int initHeadId = manager[i];
                int headId = manager[i];
                if (headId == -1 || count[headId] != 0) {
                    continue;
                }
                int total = 0;
                while (headId != -1) {
                    if (count[headId] != 0) {
                        total += count[headId];
                        break;
                    }
                    total += informTime[headId];
                    headId = manager[headId];
                }
                if (total > max) {
                    max = total;
                }
                count[initHeadId] = total;
            }
        }
        return max;
    }
}
