package my_practise.p2023.pro.datastructure.segmentTree;

import tool.Tools;

import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeSet;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.pro.datastructure.segmentTree
 * className:      FallingSquares
 * author:     BangDi
 * description:
 * 想象一下标准的俄罗斯方块游戏，X轴是积木最终下落到底的轴线
 * 下面是这个游戏的简化版：
 * 1）只会下落正方形积木
 * 2）[a,b] -> 代表一个边长为b的正方形积木，积木左边缘沿着X = a这条线从上方掉落
 * 3）认为整个X轴都可能接住积木，也就是说简化版游戏是没有整体的左右边界的
 * 4）没有整体的左右边界，所以简化版游戏不会消除积木，因为不会有哪一层被填满。
 * <p>
 * 给定一个N*2的二维数组matrix，可以代表N个积木依次掉落，
 * 返回每一次掉落之后的最大高度
 * date:    2023/8/11 11:01
 * version:    1.0
 */

// 思路:离散化+线段树
public class FallingSquares {

    // 该线段树求的是树上的最大值
    public static class SegmentTree {
        private int[] max;
        private boolean[] change;

        public SegmentTree(int n) {
            int N = n + 1;
            max = new int[N << 2];
            change = new boolean[N << 2];
        }

        public void pushUp(int rt) {
            max[rt] = Math.max(max[rt << 1], max[rt << 1 | 1]);
        }

        public void pushDown(int rt) {
            if (change[rt]) {
                change[rt << 1] = true;
                change[rt << 1 | 1] = true;
                max[rt << 1] = max[rt];
                max[rt << 1 | 1] = max[rt];
                change[rt] = false;
            }
        }

        public void update(int L, int R, int C, int l, int r, int rt) {
            if (L <= l && r <= R) {
                max[rt] = C;
                change[rt] = true;
                return;
            }
            int mid = (l + r) >> 1;
            pushDown(rt);
            if (L <= mid) {
                update(L, R, C, l, mid, rt << 1);
            }
            if (R > mid) {
                update(L, R, C, mid + 1, r, rt << 1 | 1);
            }
            pushUp(rt);
        }

        public int query(int L, int R, int l, int r, int rt) {
            if (L <= l && r <= R) {
                return max[rt];
            }
            int mid = (l + r) >> 1;
            int max = Integer.MIN_VALUE;
            pushDown(rt);
            if (L <= mid) {
                max = query(L, R, l, mid, rt << 1);
            }
            if (R > mid) {
                max = Math.max(max, query(L, R, mid + 1, r, rt << 1 | 1));
            }
            return max;
        }

    }

    public static int fallingSquare(int[][] squares) {
        if (squares == null || squares.length == 0) {
            return 0;
        }
        TreeSet<Integer> set = new TreeSet<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        int index = 1;
        for (int[] square : squares) {
            set.add(square[0]);
            set.add(square[0] + square[1] - 1);
        }
        while (!set.isEmpty()) {
            map.put(set.pollFirst(), index++);
        }
        int N = map.size();
        SegmentTree segmentTree = new SegmentTree(N);
        for (int[] square : squares) {
            Integer l = map.get(square[0]);
            Integer r = map.get(square[0] + square[1] - 1);
            int C = square[1];
            int h = segmentTree.query(l, r, 1, N, 1);
            segmentTree.update(l, r, C + h, 1, N, 1);
        }
        return segmentTree.query(1, N, 1, N, 1);
    }

    public static int compare(int[][] squares) {
        if (squares == null || squares.length == 0) {
            return 0;
        }
        int maxI = 0;
        for (int[] square : squares) {
            maxI = Math.max(maxI, square[0] + square[1] - 1);
        }
        int[] arr = new int[maxI + 1];
        for (int[] square : squares) {
            int max = 0;
            for (int j = square[0]; j < square[0] + square[1]; j++) {
                max = Math.max(max, arr[j]);
            }
            for (int i = 0; i < square[1]; i++) {
                arr[square[0] + i] = (max + square[1]);
            }
        }
        return Arrays.stream(arr).max().getAsInt();
    }

    public static int[][] generateDyadicArr(int maxSize, int maxIndex, int maxLength) {
        int size = (int) ((maxSize + 1) * Math.random());
        int[][] res = new int[size][];
        for (int i = 0; i < res.length; i++) {
            int index = (int) ((maxIndex + 1) * Math.random());
            int length = (int) ((maxLength + 1) * Math.random());
            res[i] = new int[]{index, length};
        }
        return res;
    }

    public static void test() {
        int testTime = 1000000;
        int maxSize = 30;
        int maxIndex = 100;
        int maxLength = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime && succeed; i++) {
            int[][] arr = generateDyadicArr(maxSize, maxIndex, maxLength);
            int compare = compare(arr);
            int res = fallingSquare(arr);
//            for (int[] ints : arr) {
//                System.out.print(ints[0] + "-" + ints[1] + "|");
//            }
//            System.out.println();
            if (compare != res) {
                for (int[] ints : arr) {
                    System.out.print(ints[0] + "-" + ints[1] + "|");
                }
                System.out.println();
                System.out.println("compare = " + compare);
                System.out.println("res = " + res);
                succeed = false;
            }
        }

        System.out.println(succeed ? "Nice" : "Fuck");
    }

    public static void main(String[] args) {
//        int[][] arr = {{3,2},{2,5},{9,1},{0,0}};
//        System.out.println(compare(arr));
//        System.out.println(fallingSquare(arr));
        test();
    }


}
