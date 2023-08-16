package my_practise.p2023.pro.datastructure.segmentTree;

import java.util.*;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.pro.datastructure.segmentTree
 * className:      CoverMax2
 * author:     BangDi
 * description:  TODO
 * 给定一些矩形求最大重合面积
 * date:    2023/8/13 20:58
 * version:    1.0
 */
public class CoverMax2 {

    public static class Rectangle {
        public int up;
        public int down;
        public int left;
        public int right;

        public Rectangle(int up, int down, int left, int right) {
            this.up = up;
            this.down = down;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "Rectangle{" +
                    "up=" + up +
                    ", down=" + down +
                    ", left=" + left +
                    ", right=" + right +
                    ", hashCode=" + hashCode() +
                    '}';
        }
    }

    public static int coverMax(Rectangle[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        PriorityQueue<Rectangle> upH = new PriorityQueue<>(new UpComparator());
        PriorityQueue<Rectangle> downH = new PriorityQueue<>(new DownComparator());
        PriorityQueue<Rectangle> leftH = new PriorityQueue<>(new LeftComparator());
        int max = 0;
        downH.addAll(Arrays.asList(arr));
        while (!downH.isEmpty()) {
            Rectangle cur = downH.poll();
            upH.add(cur);
            while (!downH.isEmpty() && cur.down == downH.peek().down) {
                upH.add(downH.poll());
            }
            while (!upH.isEmpty() && upH.peek().up <= cur.down) {
                upH.poll();
            }
            leftH.addAll(upH);
            PriorityQueue<Rectangle> rightH = new PriorityQueue<>(new RightComparator());
            while (!leftH.isEmpty()) {
                Rectangle curL = leftH.poll();
                while (!rightH.isEmpty() && rightH.peek().right <= curL.left) {
                    rightH.poll();
                }
                rightH.add(curL);
                max = Math.max(max, rightH.size());
            }
        }
        return max;
    }

    public static class UpComparator implements Comparator<Rectangle> {
        @Override
        public int compare(Rectangle o1, Rectangle o2) {
            return o1.up != o2.up ? (o1.up - o2.up) : o1.toString().compareTo(o2.toString());
        }
    }

    public static class DownComparator implements Comparator<Rectangle> {
        @Override
        public int compare(Rectangle o1, Rectangle o2) {
            return o1.down != o2.down ? (o1.down - o2.down) : o1.toString().compareTo(o2.toString());
        }
    }

    public static class LeftComparator implements Comparator<Rectangle> {
        @Override
        public int compare(Rectangle o1, Rectangle o2) {
            return o1.left != o2.left ? (o1.left - o2.left) : o1.toString().compareTo(o2.toString());
        }
    }

    public static class RightComparator implements Comparator<Rectangle> {
        @Override
        public int compare(Rectangle o1, Rectangle o2) {
            return o1.right != o2.right ? (o1.right - o2.right) : o1.toString().compareTo(o2.toString());
        }
    }

    // 矩形数量是N
    // O(N*LogN)
    // +
    // O(N) * [ O(N) + O(N *LogN) ]
    public static int compare(Rectangle[] recs) {
        if (recs == null || recs.length == 0) {
            return 0;
        }
        // 根据down（底）排序
        Arrays.sort(recs, new DownComparator());
        // 可能会对当前底边的公共局域，产生影响的矩形
        // list -> treeSet(有序表表达)
        TreeSet<Rectangle> leftOrdered = new TreeSet<>(new LeftComparator());
        int ans = 0;
        // O(N)
        for (int i = 0; i < recs.length; ) { // 依次考察每一个矩形的底边
            // 同样底边的矩形一批处理
            do {
                leftOrdered.add(recs[i++]);
            } while (i < recs.length && recs[i].down == recs[i - 1].down);
            // 清除顶<=当前底的矩形
            removeLowerOnCurDown(leftOrdered, recs[i - 1].down);
            // 维持了右边界排序的容器
            TreeSet<Rectangle> rightOrdered = new TreeSet<>(new RightComparator());
            for (Rectangle rec : leftOrdered) { // O(N)
                removeLeftOnCurLeft(rightOrdered, rec.left);
                rightOrdered.add(rec);// O(logN)
                ans = Math.max(ans, rightOrdered.size());
            }
        }
        return ans;
    }

    public static void removeLowerOnCurDown(TreeSet<Rectangle> set, int curDown) {
        List<Rectangle> removes = new ArrayList<>();
        for (Rectangle rec : set) {
            if (rec.up <= curDown) {
                removes.add(rec);
            }
        }
        for (Rectangle rec : removes) {
            set.remove(rec);
        }
    }

    public static void removeLeftOnCurLeft(TreeSet<Rectangle> rightOrdered, int curLeft) {
        List<Rectangle> removes = new ArrayList<>();
        for (Rectangle rec : rightOrdered) {
            if (rec.right > curLeft) {
                break;
            }
            removes.add(rec);
        }
        for (Rectangle rec : removes) {
            rightOrdered.remove(rec);
        }
    }

    public static Rectangle[] generateR(int maxSize, int maxD, int maxL, int maxLen) {
        int size = (int) (1 + maxSize * Math.random());
        Rectangle[] res = new Rectangle[size];
        for (int i = 0; i < res.length; i++) {
            int down = (int) (maxD * Math.random());
            int up = down + (int) (1 + maxLen * Math.random());
            int left = (int) (maxL * Math.random());
            int right = left + (int) (1 + maxLen * Math.random());
            res[i] = new Rectangle(up, down, left, right);
        }
        return res;
    }

    public static void printR(Rectangle[] arr){
        for (Rectangle rectangle : arr) {
            System.out.println(rectangle);
            System.out.println();
        }
    }

    public static void test() {
        int testTime = 100000;
        int maxSize = 10;
        int maxD = 10;
        int maxL = 10;
        int maxLen = 5;
        boolean succeed = true;
        for (int i = 0; i < testTime && succeed; i++) {
            Rectangle[] arr = generateR(maxSize, maxD, maxL, maxLen);
            int res1 = coverMax(arr);
            int res2 = compare(arr);
            if (res1 != res2){
                succeed = false;
                printR(arr);
                System.out.println("res1 = " + res1);
                System.out.println("res2 = " + res2);
            }
        }
        System.out.println(succeed ? "Nice" : "Fuck");
    }

    public static void main(String[] args) {
        test();
    }

}
