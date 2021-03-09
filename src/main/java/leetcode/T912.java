package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个整数数组 nums，将该数组升序排列
 * 归并排序
 *
 */
public class T912 {

    public static void main(String[] args) {
        int[] arr = new int[]{5,1,1,2,0,0};
        System.out.println(sortArray(arr));
    }

    public static List<Integer> sortArray(int[] nums) {
//        return Arrays.asList(mergeSort(nums, 0, nums.length - 1));
//        quickSort(nums, 0, nums.length - 1);
        heapSort(nums);
        List<Integer> list = new ArrayList<>();
        Arrays.stream(nums).map(Integer::new).forEach(list::add);
        return list;
    }

    //归并排序
    private static Integer[] mergeSort(int[] nums, int l, int h) {
        if (l == h) {
            return new Integer[] { nums[l] };
        }

        int mid = l + (h - l) / 2;
        Integer[] leftArr = mergeSort(nums, l, mid); //左有序数组
        Integer[] rightArr = mergeSort(nums, mid + 1, h); //右有序数组
        Integer[] newNum = new Integer[leftArr.length + rightArr.length]; //新有序数组

        int m = 0, i = 0, j = 0;
        while (i < leftArr.length && j < rightArr.length) {
            newNum[m++] = leftArr[i] < rightArr[j] ? leftArr[i++] : rightArr[j++];
        }
        while (i < leftArr.length) {
            newNum[m++] = leftArr[i++];
        }
        while (j < rightArr.length) {
            newNum[m++] = rightArr[j++];
        }
        return newNum;
    }

    //快速排序
    private static void quickSort(int[] nums, int l, int h) {
        if (l == h) {
            return;
        }

        int middle = getMiddle(nums, l, h);

        if (l < middle) {
            quickSort(nums, l, middle - 1);
        }
        if (middle < h) {
            quickSort(nums, middle + 1, h);
        }
    }

    private static int getMiddle(int[] nums, int l, int h) {

        int start = l;
        while(true) {
            while (l < h && nums[h] >= nums[start]) {
                h--;
            }
            while (l < h && nums[l] < nums[start]) {
                l++;
            }

            if (l == h) {
                break;
            } else {
                int tmp = nums[l];
                nums[l] = nums[h];
                nums[h] = tmp;
            }
        }

        if (start < l) {
            int tmp = nums[start];
            nums[start] = nums[l];
            nums[l] = tmp;
        }

        return l;
    }

    //堆排序
    private static int[] heapSort(int[] array) {
        //这里元素的索引是从0开始的,所以最后一个非叶子结点array.length/2 - 1
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            adjustHeap(array, i, array.length);  //调整堆
        }

        // 上述逻辑，建堆结束
        // 下面，开始排序逻辑
        for (int j = array.length - 1; j > 0; j--) {
            // 元素交换,作用是去掉大顶堆
            // 把大顶堆的根元素，放到数组的最后；换句话说，就是每一次的堆调整之后，都会有一个元素到达自己的最终位置
            swap(array, 0, j);
            // 元素交换之后，毫无疑问，最后一个元素无需再考虑排序问题了。
            // 接下来我们需要排序的，就是已经去掉了部分元素的堆了，这也是为什么此方法放在循环里的原因
            // 而这里，实质上是自上而下，自左向右进行调整的
            adjustHeap(array, 0, j);
        }
        return array;
    }

    private static void adjustHeap(int[] array, int i, int length) {
        // 先把当前元素取出来，因为当前元素可能要一直移动
        int temp = array[i];
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {  //2*i+1为左子树i的左子树(因为i是从0开始的),2*k+1为k的左子树
            // 让k先指向子节点中最大的节点
            if (k + 1 < length && array[k] < array[k + 1]) {  //如果有右子树,并且右子树大于左子树
                k++;
            }
            //如果发现结点(左右子结点)大于根结点，则进行值的交换
            if (array[k] > temp) {
                swap(array, i, k);
                // 如果子节点更换了，那么，以子节点为根的子树会受到影响,所以，循环对子节点所在的树继续进行判断
                i  =  k;
            } else {  //不用交换，直接终止循环
                break;
            }
        }
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
