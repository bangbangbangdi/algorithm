package my_practise.p2023.basic.datastructure;

import tool.BtNode;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.basic.datastructure
 * className:      MaxDistance
 * author:     BangDi
 * description:  TODO
 * 给定一颗二叉树的头节点head,任何两个节点之间都存在距离
 * 返回整棵树都最大距离
 * date:    2023/6/4 00:15
 * version:    1.0
 */
public class MaxDistance {

    public static int maxDistance(BtNode node) {
        if (node == null) {
            return 0;
        }
        return process(node).maxDistance;
    }

    public static Info process(BtNode node) {
        if (node == null) {
            return new Info(0, 0);
        }
        Info left = process(node.left);
        Info right = process(node.right);
        int height = Math.max(left.height, right.height) + 1;
        int maxDistance = Math.max(Math.max(left.maxDistance, right.maxDistance), left.height + right.height);
        return new Info(maxDistance, height);
    }

    public static class Info {
        int maxDistance;
        int height;

        public Info(int maxDistance, int height) {
            this.maxDistance = maxDistance;
            this.height = height;
        }
    }

    public static void test() {
        BtNode n0 = new BtNode(5);
        BtNode n1 = new BtNode(3);
        BtNode n2 = new BtNode(7);
        BtNode n3 = new BtNode(2);
        BtNode n4 = new BtNode(4);
        BtNode n5 = new BtNode(6);
        BtNode n6 = new BtNode(8);
        BtNode n7 = new BtNode(8);
        BtNode n8 = new BtNode(9);
        n0.left = n1;
        n1.left = n2;
        n2.left = n3;
        n2.right = n4;
        n3.left = n5;
        n4.right = n6;
        n6.right = n7;
        System.out.println(maxDistance(n0));
    }

    public static void main(String[] args) {
        test();
    }
}
