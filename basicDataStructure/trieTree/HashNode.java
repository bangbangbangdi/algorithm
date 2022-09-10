package basicDataStructure.trieTree;

import java.util.HashMap;

public class HashNode {

    public int pass;
    public int end;
    public HashMap<Character,HashNode> next;

    public HashNode(int pass, int end) {
        this.pass = pass;
        this.end = end;
        this.next = new HashMap<>();
    }
}
