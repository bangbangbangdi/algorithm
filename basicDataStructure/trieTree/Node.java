package basicDataStructure.trieTree;

public class Node {
    public int pass;
    public int end;
    public Node[] next;

    public Node(int pass,int end) {
        this.pass = pass;
        this.end = end;
        this.next = new Node[26];
    }

}
