package basicDataStructure.linkedList;

//将单向链表按某值划分成左边小、中间相等、右边大的形式
//1）把链表放入数组里，在数组上做partition（笔试用）
//2）分成小、中、大三部分，再把各个部分之间串起来（面试用）


import tool.Node;
import tool.Tools;

import java.util.ArrayList;

public class SmallerEqualBigger {

    public static Node nodePartition(Node head, int value) {
        if (head == null || head.next == null) {
            return head;
        }

        ArrayList<Node> arr = new ArrayList<>();
        while (head != null) {
            arr.add(head);
            head = head.next;
        }
        int smallerIndex = -1;
        int maxIndex = arr.size();
        int index = 0;
        int cur = 0;
        while (index < maxIndex) {
            cur = arr.get(index).value;
            if (cur < value) {
                swap(arr, index++, ++smallerIndex);
            } else if (cur > value) {
                swap(arr, index, --maxIndex);
            } else {
                index++;
            }
        }
        Node res = arr.get(0);
        for (int i = 0; i < arr.size() - 1; i++) {
            arr.get(i).next = arr.get(i + 1);
        }
        arr.get(arr.size() - 1).next = null;
        return res;
    }

    public static void swap(ArrayList<Node> arr, int i, int j) {
        if (i == j) {
            return;
        }
        Node tem = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, tem);
    }

    public static void test() {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(2);
        Node n5 = new Node(1);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        nodePartition(n1, 2);
        Tools.printAllNodes(n1);
    }

    public static void main(String[] args) {
        test();
    }

}
