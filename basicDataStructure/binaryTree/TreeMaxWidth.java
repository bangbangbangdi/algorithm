package basicDataStructure.binaryTree;

import tool.BtNode;

import java.util.LinkedList;

// 求最宽的节点数
public class TreeMaxWidth {

    public static int getMaxWidth(BtNode head) {
        if (head == null) {
            return 0;
        }
        LinkedList<BtNode> queue = new LinkedList<>();
        queue.add(head);
        BtNode curEnd = head;
        BtNode nextEnd = head;
        int curWidth = 0;
        int maxWidth = 0;
        while (!queue.isEmpty()) {
            BtNode node = queue.pop();
            if (node.left != null) {
                queue.add(node.left);
                nextEnd = node.left;
            }
            if (node.right != null) {
                queue.add(node.right);
                nextEnd = node.right;
            }
            curWidth++;
            if (node == curEnd) {
                maxWidth = Math.max(maxWidth, curWidth);
                curEnd = nextEnd;
                curWidth = 0;
            }
        }
        return maxWidth;
    }

    public static void test(){
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
        System.out.println(getMaxWidth(n0));
    }

    public static void main(String[] args) {
        test();
    }
}
