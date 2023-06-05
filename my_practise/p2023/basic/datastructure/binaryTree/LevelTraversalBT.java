package my_practise.p2023.basic.datastructure.binaryTree;

import tool.BtNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.basic.datastructure
 * className:      LevelTraversalBT
 * author:     BangDi
 * description:  TODO
 * date:    2023/6/3 22:15
 * version:    1.0
 */
public class LevelTraversalBT {

    public static void levelTraversal(BtNode node) {
        if (node == null) {
            return;
        }
        Queue<BtNode> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            BtNode cur = queue.poll();
            System.out.println("cur.value = " + cur.value);
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
    }


    public static void test() {
        BtNode n0 = new BtNode(1);
        BtNode n1 = new BtNode(2);
        BtNode n2 = new BtNode(3);
        BtNode n3 = new BtNode(4);
        BtNode n4 = new BtNode(5);
        BtNode n5 = new BtNode(6);
        BtNode n6 = new BtNode(7);
        BtNode n7 = new BtNode(8);
        n0.left = n1;
        n0.right = n2;
        n1.left = n3;
        n1.right = n4;
        n2.left = n5;
        n2.right = n6;
        n3.left = n7;
        levelTraversal(n0);
    }

    public static void main(String[] args) {
        test();
    }
}
