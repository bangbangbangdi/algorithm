package basicDataStructure.trieTree;

public class Node {
    public int pass;
    public int end;
    public Node[] next;

    public Node() {
        this.pass = 0;
        this.end = 0;
        this.next = new Node[26];
    }

}
