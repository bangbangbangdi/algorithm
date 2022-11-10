package my_practise.basic.myClass08;

import tool.BtNode;
import tool.Tools;

import java.util.LinkedList;
import java.util.Queue;

public class Code06_IsCBT {
    public static boolean isCBT(BtNode head) {
        if (head == null) {
            return true;
        }
        return process(head).isCBT;
    }

    public static class Info {
        public int num;
        public int level;
        public boolean isCBT;
        public boolean isFull;

        public Info(int num, int level, boolean isCBT, boolean isFull) {
            this.num = num;
            this.level = level;
            this.isCBT = isCBT;
            this.isFull = isFull;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "num=" + num +
                    ", level=" + level +
                    ", isCBT=" + isCBT +
                    ", isFull=" + isFull +
                    '}';
        }
    }

    public static Info process(BtNode x) {
        if (x == null) {
            return new Info(0, 0, true, true);
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        int num = leftInfo.num + rightInfo.num + 1;
        int level = Math.max(leftInfo.level, rightInfo.level) + 1;
        boolean isCBT = true;
        boolean isFull = num == Math.pow(2, level) - 1;

        if (!(leftInfo.isCBT && rightInfo.isCBT)) {
            isCBT = false;
        }
        if (leftInfo.level < rightInfo.level) {
            isCBT = false;
        }
        if (leftInfo.level - rightInfo.level > 1) {
            isCBT = false;
        }
        // 这里可以被优化掉
//        if (leftInfo.num < rightInfo.num) {
//            isCBT = false;
//        }
        if (leftInfo.level == rightInfo.level && !leftInfo.isFull) {
            isCBT = false;
        }
        if (leftInfo.level > rightInfo.level && !rightInfo.isFull) {
            isCBT = false;
        }

        Info info = new Info(num, level, isCBT, isFull);
//        System.out.println("x:" + x.value + " " + info);

        return info;
    }

    public static boolean isCBT2(BtNode head) {
        if (head == null) {
            return true;
        }
        Queue<BtNode> queue = new LinkedList<>();
        queue.add(head);
        BtNode left = null;
        BtNode right = null;
        boolean leaf = false;
        while (!queue.isEmpty()) {
            BtNode node = queue.poll();
            left = node.left;
            right = node.right;
            if ((leaf && !(left == null && right == null)) || (left == null && right != null)) {
                return false;
            }
            if (left != null) {
                queue.add(left);
            }
            if (right != null) {
                queue.add(right);
            }
            if (!(left != null && right != null)) {
                leaf = true;
            }
        }
        return true;
    }

    public static boolean isCBT3(BtNode head) {
        if (head == null) {
            return true;
        }
//        Tools.printBinaryTree(head);
//        System.out.println("=======");
        Queue<Integer> in = in(head);
        BtNode fullBT = build(in);
//        Tools.printBinaryTree(fullBT);
        boolean ans = compareBtNode(head, fullBT);
//        System.out.println(ans);
        return ans;
    }

    public static Queue<Integer> in(BtNode head) {
        if (head == null) {
            return null;
        }
        LinkedList<Integer> queue = new LinkedList<>();
        LinkedList<BtNode> help = new LinkedList<>();
        help.offer(head);
        while (!help.isEmpty()) {
            BtNode node = help.poll();
            queue.offer(node.value);
            if (node.left != null) {
                help.offer(node.left);
            }
            if (node.right != null) {
                help.offer(node.right);
            }
        }
        return queue;
    }

    public static BtNode build(Queue<Integer> queue) {
        if (queue == null || queue.isEmpty()) {
            return null;
        }
        BtNode head = new BtNode(queue.poll());
        Queue<BtNode> help = new LinkedList<>();
        help.offer(head);
        while (!queue.isEmpty()) {
            BtNode node = help.poll();
            node.left = new BtNode(queue.poll());
            Integer rightValue = queue.poll();
            if (rightValue != null) {
                node.right = new BtNode(rightValue);
            }
            if (node.left != null) {
                help.offer(node.left);
            }
            if (node.right != null) {
                help.offer(node.right);
            }
        }
        return head;
    }

    public static boolean compareBtNode(BtNode head1, BtNode head2) {
        if (head1 == null && head2 == null) {
            return true;
        }
        if (head1 == null || head2 == null) {
            return false;
        }
        if (head1.value != head2.value){
            return false;
        }
        return compareBtNode(head1.left,head2.left) && compareBtNode(head1.right,head2.right);

    }

    public static void main(String[] args) {
        int testTimes = 500000;
        int maxLevel = 10;
        int maxValue = 10;
        boolean succeed = true;
        for (int i = 0; (i < testTimes) && succeed; i++) {
            BtNode node = Tools.generateRandomBST(maxLevel, maxValue);
            if (isCBT(node) != isCBT2(node) || isCBT(node) != isCBT3(node)) {
                succeed = false;
                System.out.println("-------");
                System.out.println("isCBT " + isCBT(node));
                System.out.println("isCBT2 " + isCBT2(node));
                System.out.println("isCBT3 " + isCBT3(node));
                Tools.printBinaryTree(node);
            }
        }
        System.out.println(succeed ? "Nice" : "F");
    }
}
