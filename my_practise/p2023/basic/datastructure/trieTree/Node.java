package my_practise.p2023.basic.datastructure.trieTree;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.basic.datastructure.trieTree
 * className:      Node
 * author:     BangDi
 * description:  TODO
 * date:    2023/6/19 22:33
 * version:    1.0
 */
public class Node {

    public int pass;
    public int end;
    public Node[] next;

    public Node(){
        pass = 0;
        end = 0;
        next = new Node[26];
    }

}
