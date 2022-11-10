package my_practise.basic.myClass07;

import tool.BtNode;
import tool.Tools;

import java.util.LinkedList;
import java.util.Queue;


public class Code03_LevelTraversalBT {
    public static void level(BtNode head) {
        Queue<BtNode> queue = new LinkedList<>();
        queue.offer(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            System.out.print(head.value + " ");
            if (head.left != null) {
                queue.offer(head.left);
            }
            if (head.right != null) {
                queue.offer(head.right);
            }
        }
    }

    public static void main(String[] args) {
        BtNode node = Tools.createSimpleBtNode();
        level(node);
    }
}
