package my_practise.p2023.basic.datastructure.trieTree;

import java.util.HashMap;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.basic.datastructure.trieTree
 * className:      HashNode
 * author:     BangDi
 * description:  TODO
 * date:    2023/6/19 22:31
 * version:    1.0
 */
public class HashNode {

    public int pass;
    public int end;
    public HashMap<Character,HashNode> next;

    public HashNode(){
        pass = 0;
        end = 0;
        next = new HashMap<>();
    }

}
