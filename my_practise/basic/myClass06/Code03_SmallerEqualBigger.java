package my_practise.basic.myClass06;

import tool.Node;
import tool.Tools;

public class Code03_SmallerEqualBigger {

    public static Node listPartition1(Node head, int pivot) {
        if (head == null || head.next == null) {
            return head;
        }
        Node cur = head;
        int size = 0;
        int i = 0;
        while (cur != null) {
            size++;
            cur = cur.next;
        }
        Node[] arr = new Node[size];
        cur = head;
        for (i = 0; i < arr.length; i++) {
            arr[i] = cur;
            cur = cur.next;
        }
        arrPartition(arr, pivot);
        for (i = 1; i < arr.length; i++) {
            arr[i - 1].next = arr[i];
        }
        arr[i - 1].next = null;
        return arr[0];
    }

    public static void arrPartition(Node[] arr, int pivot) {
        int bigIndex = arr.length;
        int smallIndex = -1;
        int index = 0;
        while (index < bigIndex) {
            if (arr[index].value < pivot) {
                swapNode(arr, ++smallIndex, index++);
            } else if (arr[index].value > pivot) {
                swapNode(arr, --bigIndex, index);
            } else {
                index++;
            }
        }
    }

    public static void swapNode(Node[] arr, int i, int j) {
        Node temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static Node listPartition2(Node head, int pivot) {
        Node sH = null;
        Node sT = null;
        Node eH = null;
        Node eT = null;
        Node mH = null;
        Node mT = null;

        Node cur = head;
        Node next = null;
        while (cur != null) {
            next = cur.next;
            // 每个节点在进行操作前请先断链，不然可能会出现环的情况
            cur.next = null;
            if (cur.value < pivot) {
                if (sH == null) {
                    sH = cur;
                } else {
                    sT.next = cur;
                }
                sT = cur;
            }
            if (cur.value == pivot) {
                if (eH == null) {
                    eH = cur;
                } else {
                    eT.next = cur;
                }
                eT = cur;
            }
            if (cur.value > pivot) {
                if (mH == null) {
                    mH = cur;
                } else {
                    mT.next = cur;
                }
                mT = cur;
            }
            cur = next;
        }

        // 头尾相连，如果等于区域为空，则直接连大于区域
        if (sT != null) {
            sT.next = (eH != null) ? eH : mH;
        }

        if (eT != null) {
            eT.next = mH;
        }
        return (sH != null) ? sH : (eH != null) ? eH : mH;
    }

    public static boolean checkPartition(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        int equalLIndex = -1;
        int equalRIndex = -1;
        int size = 0;
        int i = 0;
        Node cur = head;
        while (cur != null) {
            size++;
            cur = cur.next;
        }
        cur = head;
        int[] arr = new int[size];
        for (i = 0; i < arr.length; i++) {
            arr[i] = cur.value;
            cur = cur.next;
        }

        // 得到等于零的区域
        for (i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                if (equalLIndex == -1) {
                    equalLIndex = i;
                }
                equalRIndex = i;
            }
        }


        // 如果没有等于零的
        if (equalLIndex == -1) {
            i = 0;
            // 得到第一个大于零的索引
            while (arr[i] < 0 && i < arr.length - 1) {
                i++;
            }
            // 左右都等于大于零的一个索引
            equalLIndex = equalRIndex = i;
            // 如果全是负数的情况
            if (i == arr.length - 1) {
                return true;
            }

        }

        // 到这里意味着数组里必然存在大于零的区域和小于零的区域
        for (i = 0; i < arr.length; i++) {
            if (i < equalLIndex && arr[i] >= 0) {
                return false;
            }
            if (i > equalRIndex && arr[i] <= 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int testTimes = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; (i < testTimes) && succeed; i++) {
            Node[] arr = Tools.generateRandomNodeArray(maxSize, maxValue);
            Node[] copyArr = Tools.copyNodeArray(arr);
            Node head1 = Tools.createNodes(arr);
            Node head2 = Tools.createNodes(copyArr);
//            System.out.println("===============");
//            Tools.printAllNodes(head1);
//            Tools.printAllNodes(head2);
            Node res1 = listPartition1(head1, 0);
            Node res2 = listPartition2(head2, 0);

            if (!(checkPartition(res1) && checkPartition(res2))) {
                succeed = false;
                Tools.printAllNodes(res1);
                Tools.printAllNodes(res2);
            }
        }
        System.out.println(succeed ? "Nice" : "F");
    }
}
