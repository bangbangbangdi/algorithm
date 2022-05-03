package tool;

import java.util.ArrayList;
import java.util.Arrays;

public class Tools {
    public static int sum(int[] arr){
        return Arrays.stream(arr).parallel().reduce(0,Integer::sum);
    }

    public static void printBinaryTree(BtNode head) {
        if (head == null) {
            return;
        }
        int length = 10;
        printBTProcess(head, length, "Head", 0);
    }

    public static void printBTProcess(BtNode head, int length, String to, int level) {
        if (head == null) {
            return;
        }
        printBTProcess(head.right, length, "v", level + 1);
        String str = String.valueOf(head.value);
        str = to + str + to;
        int leftSpace = (length - str.length()) >> 1;
        int rightSpace = length - str.length() - leftSpace;
        leftSpace = leftSpace + (length * level);
        str = getSpace(leftSpace) + str + getSpace(rightSpace);
        System.out.println(str);
        printBTProcess(head.left, length, "^", level + 1);
    }

    public static String getSpace(int num) {
        if (num < 1) {
            return "";
        }
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < num; i++) {
            buf.append(" ");
        }
        return buf.toString();
    }

    public static void in(BtNode head, ArrayList<BtNode> arr) {
        if (head == null) {
            return;
        }
        arr.add(head);
        in(head.left, arr);
        in(head.right, arr);
    }

    public static BtNode[] getRandomBTAndAB(int maxLevel, int maxValue) {
        BtNode head = generateRandomBST(maxLevel, maxValue);
        if (head == null) {
            return null;
        }
        BtNode a = null;
        BtNode b = null;
        ArrayList<BtNode> arr = new ArrayList<>();
        Tools.in(head, arr);
        a = arr.get((int) (Math.random() * arr.size()));
        b = arr.get((int) (Math.random() * arr.size()));
        return new BtNode[]{head, a, b};
    }

    public static BtNode generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    public static BtNode generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() > 0.7) {
            return null;
        }
        BtNode head = new BtNode((int) ((maxValue + 1) * Math.random() - (maxValue + 1) * Math.random()));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static boolean isSameValueStructure(BtNode head1, BtNode head2) {
        if (head1 == null && head2 == null) {
            return true;
        }
        if (head1 != null && head2 == null) {
            return false;
        }
        if (head1 == null && head2 != null) {
            return false;
        }
        if (head1.value != head2.value) {
            return false;
        }
        return isSameValueStructure(head1.left, head2.left) && isSameValueStructure(head1.right, head2.right);
    }


    public static BtNode createSimpleBtNode() {
        BtNode btNode1 = new BtNode(1);
        BtNode btNode2 = new BtNode(2);
        BtNode btNode3 = new BtNode(3);
        BtNode btNode4 = new BtNode(4);
        BtNode btNode5 = new BtNode(5);
        BtNode btNode6 = new BtNode(6);
        BtNode btNode7 = new BtNode(7);

        btNode1.left = btNode2;
        btNode1.right = btNode3;
        btNode2.left = btNode4;
        btNode2.right = btNode5;
        btNode3.left = btNode6;
        btNode3.right = btNode7;
        return btNode1;
    }

    public static BtNode createSimpleBtNode(boolean isBST) {
        BtNode btNode1 = new BtNode(4);
        BtNode btNode2 = new BtNode(2);
        BtNode btNode3 = new BtNode(6);
        BtNode btNode4 = new BtNode(1);
        BtNode btNode5 = new BtNode(3);
        BtNode btNode6 = new BtNode(5);
        BtNode btNode7 = new BtNode(7);

        btNode1.left = btNode2;
        btNode1.right = btNode3;
        btNode2.left = btNode4;
        btNode2.right = btNode5;
        btNode3.left = btNode6;
        btNode3.right = btNode7;
        return btNode1;
    }

    public static void printAllBtNode(BtNode head) {
        if (head == null) {
            System.out.print("null" + " ");
            return;
        }
        System.out.print(head.value + " ");
        printAllBtNode(head.left);
        printAllBtNode(head.right);
    }

    public static void printBtNode(BtNode head) {

    }

    public static void printAllNodes(ArrayList<Node> arr) {
        for (Node cur : arr) {
            System.out.print(cur.value + "-");
        }
        System.out.println();
    }

    public static void printAllNodes(Node[] arr) {
//        System.out.println("arrSize:" + arr.length);
        for (Node cur : arr) {
            System.out.print(cur.value + " ");
        }
        System.out.println();
    }

    public static void printAllNodes(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        while (cur.next != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.print(cur.value + " ");
        System.out.println();
    }

    public static void addNodes(Node head, Node[] arr) {
        if (head == null || arr == null) {
            return;
        }
        Node tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        for (int i = 0; i < arr.length; i++) {
            tail.next = arr[i];
            tail = tail.next;
        }
    }

    public static Node createNodes(Node[] arr) {
        if (arr == null || arr.length < 1) {
            return null;
        }
        Node head = arr[0];
        Node cur = head;
        for (int i = 1; i < arr.length; i++) {
            cur.next = arr[i];
            cur = cur.next;
        }
        return head;
    }

    public static Node createNodes(int[] arr) {
        if (arr == null) {
            return null;
        }
        Node[] nodeArr = new Node[arr.length];
        for (int i = 0; i < arr.length; i++) {
            nodeArr[i] = new Node(arr[i]);
        }
        for (int i = 1; i < nodeArr.length; i++) {
            nodeArr[i - 1].next = nodeArr[i];
        }
        return nodeArr[0];
    }

    public static Node[] generateRandomNodeArray(int maxSize, int maxValue) {
        Node[] arr = new Node[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Node((int) ((maxValue + 1) * Math.random()) - (int) ((maxValue + 1) * Math.random()));
        }
        return arr;
    }

    public static Node[] copyNodeArray(Node[] arr) {
        Node[] nodes = new Node[arr.length];
        for (int i = 0; i < arr.length; i++) {
            nodes[i] = new Node(arr[i].value);
        }
        return nodes;
    }


    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    public static int[] generateRandomArray(int maxSize, int maxValue, boolean isContainNegative) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        if (isContainNegative) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
            }
            return arr;
        } else {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = (int) ((maxValue + 1) * Math.random());
            }
            return arr;
        }
    }

    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void printStringArray(String[] str) {
        if (str == null) {
            return;
        }
        for (int i = 0; i < str.length; i++) {
            System.out.print(str[i] + " ");
        }
        System.out.println();
    }

    public static boolean isEqual(int[] arr1, int[] arr2) {
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static int[] copyArr(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[i];
        }
        return result;
    }

    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    public static void swap(int[] arr, int i, int j) {
        if ((arr == null) || i == j) {
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static int getRandomInt(int maxValue) {
        return (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
    }

    public static String generateRandomString(int strLen) {
        char[] chars = new char[(int) (strLen * Math.random() + 1)];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) ((int) (Math.random() * 6) + 97);
        }
        return String.valueOf(chars);
    }

    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] arr = new String[(int) (arrLen * Math.random() + 1)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = generateRandomString(strLen);
        }
        return arr;
    }
}
