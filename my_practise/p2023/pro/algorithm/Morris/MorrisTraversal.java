package my_practise.p2023.pro.algorithm.Morris;

import tool.BtNode;
import tool.Node;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.pro.algorithm.Morris
 * className:      MorrisTraversal
 * author:     BangDi
 * description:
 * 对于二叉树的遍历能做到:时间复杂度O(N) 空间复杂度O(1)
 * 递归遍历时间复杂度相同,空间复杂度O(logN)
 *  摩尔斯遍历流程
 *  1.cur无左树,cur=cur.right
 *  2.cur有左树,找到左树上的的最右节点,mostRight
 *   1)mostRight的右指针指向null的,mostRight.right = cur , cur = cur.left
 *   2)mostRight的右指针指向cur,mostRight.right = null, cur = cur.right
 * date:    2023/8/6 21:52
 * version:    1.0
 */
public class MorrisTraversal {

    public static void morris(BtNode head){
        BtNode cur = head;
        BtNode mostLeftRight = null;
        while(cur != null){
            if (cur.left != null){
                mostLeftRight = cur.left;
                while(mostLeftRight.right != null && mostLeftRight.right != cur){
                    mostLeftRight = mostLeftRight.right;
                }
                if (mostLeftRight.right == null){
                    System.out.print(cur.value);
                    mostLeftRight.right = cur;
                    cur = cur.left;
                    continue;
                }else{
                    mostLeftRight.right = null;
                }
            }
            System.out.print(cur.value);
            cur = cur.right;
        }
    }

    public static void morrisPre(BtNode head){
        BtNode cur = head;
        BtNode mostLeftRight = null;
        while(cur != null){
            if (cur.left != null){
                mostLeftRight = cur.left;
                while(mostLeftRight.right != null && mostLeftRight.right != cur){
                    mostLeftRight = mostLeftRight.right;
                }
                if (mostLeftRight.right == null){
                    System.out.print(cur.value);
                    mostLeftRight.right = cur;
                    cur = cur.left;
                }else{
                    mostLeftRight.right = null;
                    cur = cur.right;
                }
                continue;
            }
            System.out.print(cur.value);
            cur = cur.right;
        }
    }

    public static void morrisIn(BtNode head){
        BtNode cur = head;
        BtNode mostLeftRight = null;
        while(cur != null){
            if (cur.left != null){
                mostLeftRight = cur.left;
                while(mostLeftRight.right != null && mostLeftRight.right != cur){
                    mostLeftRight = mostLeftRight.right;
                }
                if (mostLeftRight.right == null){
                    mostLeftRight.right = cur;
                    cur = cur.left;
                }else{
                    System.out.print(cur.value);
                    mostLeftRight.right = null;
                    cur = cur.right;
                }
                continue;
            }
            System.out.print(cur.value);
            cur = cur.right;
        }
    }

    public static void morrisPost(BtNode head){
        BtNode cur = head;
        BtNode mostLeftRight = null;
        while(cur != null){
            if (cur.left != null){
                mostLeftRight = cur.left;
                while(mostLeftRight.right != null && mostLeftRight.right != cur){
                    mostLeftRight = mostLeftRight.right;
                }
                if (mostLeftRight.right == null){
                    mostLeftRight.right = cur;
                    cur = cur.left;
                    continue;
                }else{
                    mostLeftRight.right = null;
                    reverseOrderPrint(cur.left);
                }
            }
            cur = cur.right;
        }
        reverseOrderPrint(head);
    }

    public static void reverseOrderPrint(BtNode head){
        BtNode newH = reverseOrder(head);
        printNodeList(newH);
        reverseOrder(newH);
    }

    public static void printNodeList(BtNode head){
        while(head != null){
            System.out.print(head.value);
            head = head.right;
        }
    }

    public static BtNode reverseOrder(BtNode head){
        BtNode cur = head;
        BtNode pre = null;
        BtNode next = null;
        while(cur != null){
            next = cur.right;
            cur.right = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        BtNode n1 = new BtNode(1);
        BtNode n2 = new BtNode(2);
        BtNode n3 = new BtNode(3);
        BtNode n4 = new BtNode(4);
        BtNode n5 = new BtNode(5);
        BtNode n6 = new BtNode(6);
        BtNode n7 = new BtNode(7);

        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;

        morris(n1);
        System.out.println();
        morrisPre(n1);
        System.out.println();
        morrisIn(n1);
        System.out.println();
        morrisPost(n1);
    }

}
