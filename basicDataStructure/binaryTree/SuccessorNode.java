package basicDataStructure.binaryTree;

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

    public static Node findSuccessorNode(Node cur) {
        if (cur == null) {
            return null;
        }
        if (cur.right != null) {
            cur = cur.right;
            while (cur.left != null) {
                cur = cur.left;
            }
            return cur;
        }
        Node parent = cur.parent;
        while (cur != parent.left) {
            cur = parent;
            parent = cur.parent != null ? cur.parent : parent;
        }
        return parent;
    }

}
