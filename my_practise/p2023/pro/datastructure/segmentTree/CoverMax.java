package my_practise.p2023.pro.datastructure.segmentTree;

import tool.Tools;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.pro.datastructure.segmentTree
 * className:      CoverMax
 * author:     BangDi
 * description:
 * 线段重合问题: 给定一个二维数组arr 数组的第一个元素代表线段起点,第二个元素表示线段终点,求最大的重合的次数
 * date:    2023/8/13 16:52
 * version:    1.0
 */
public class CoverMax {

    public static class Segment {
        public int start;
        public int end;

        public Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static int coverMax(int[][] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        PriorityQueue<Segment> startH = new PriorityQueue<>(new Comparator<Segment>() {
            @Override
            public int compare(Segment o1, Segment o2) {
                return o1.start - o2.start;
            }
        });
        PriorityQueue<Segment> endH = new PriorityQueue<>(new Comparator<Segment>() {
            @Override
            public int compare(Segment o1, Segment o2) {
                return o1.end - o2.end;
            }
        });
        int max = 0;
        for (int[] ints : arr) {
            startH.add(new Segment(ints[0], ints[1]));
        }
        while(!startH.isEmpty()){
            Segment cur = startH.poll();
            while(!endH.isEmpty() && endH.peek().end <= cur.start){
                endH.poll();
            }
            endH.add(cur);
            max = Math.max(max,endH.size());
        }
        return max;
    }

    public static int compare(int[][] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int[] ints : arr) {
            for (int i = ints[0]; i < ints[1]; i++) {
                int value = map.getOrDefault(i, 0) + 1;
                map.put(i, value);
            }
        }
        Integer max = map.values().stream().max((o1, o2) -> o1 - o2).get();
        return max;
    }

    public static void test() {
        int testTime = 1000000;
        int maxSize = 100;
        int maxIndex = 100;
        int maxLength = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime && succeed; i++) {
            int[][] arr = Tools.generateDyadicArr(maxSize, maxIndex, maxLength);
            int compare = compare(arr);
            int res = coverMax(arr);
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
//        int[][] arr = {{1,3},{4,7},{2,5},{4,9}};
//        System.out.println(compare(arr));
//        System.out.println(coverMax(arr));
        test();
    }

}
