package my_practise.p2023.basic.datastructure.binaryTree;

import tool.BtNode;
import tool.Tools;

import java.util.Stack;

/**
 * projectName:    algorithm1
 * package:        my_practise.p2023.basic.datastructure.binaryTree
 * className:      SuccessorNode
 * author:     BangDi
 * description:  后继节点
 * // 给你二叉树中的某个节点，返回该节点的后继节点
 * // 后继节点:对一颗二叉树进行中序遍历，当前节点的后一个节点即该节点的后续节点
 * date:    2023/6/7 9:26
 * version:    1.0
 */
public class SuccessorNode {

    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int value) {
            this.value = value;
        }
    }

    // 该题目重点在于分析好后继节点的所有情况，即存在右子树的情况和不存在右子树的情况,不存在右子树的情况下，还需要考虑当前的node是否是更上一层的左子树
    public static Node findSuccessorNode(Node node) {
        if (node == null) {
            return null;
        }
        if (node.right != null) {
            Node cur = node.right;
            while (cur.left != null) {
                cur = cur.left;
            }
            return cur;
        }
        Node parent = node.parent;
        while (parent != null && node != parent.left) {
            node = parent;
            parent = parent.parent;
        }
        return parent;
    }

    public static void test() {
        Node n0 = new Node(1);
        Node n1 = new Node(2);
        Node n2 = new Node(3);
        Node n3 = new Node(4);
        Node n4 = new Node(5);
        Node n5 = new Node(6);
        Node n6 = new Node(7);
        Node n7 = new Node(8);
        n0.left = n1;
        n0.right = n2;
        n1.left = n3;
        n1.right = n4;
        n2.left = n5;
        n2.right = n6;
        n3.left = n7;
        System.out.println(findSuccessorNode(n3) == null ? "null" : findSuccessorNode(n7).value);
    }

    public static void main(String[] args) {
        test();
    }

}
