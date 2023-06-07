package basicDataStructure.binaryTree;

// 给你二叉树中的某个节点，返回该节点的后继节点
// 后继节点:对一颗二叉树进行中序遍历，当前节点的后一个节点即该节点的后续节点
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
        while (parent != null && cur != parent.left) {
            cur = parent;
            parent = cur.parent;
        }
        return parent;
    }

}
