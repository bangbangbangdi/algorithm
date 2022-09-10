package basicDataStructure.trieTree;

//设计一种结构。用户可以：
//        1）void insert(String str)            添加某个字符串，可以重复添加，每次算1个
//        2）int search(String str)             查询某个字符串在结构中还有几个
//        3)  void delete(String str)           删掉某个字符串，可以重复删除，每次算1个
//        4）int prefixNumber(String str)  查询有多少个字符串，是以str做前缀的

import java.util.HashMap;

public class TrieTree {

    private final Node head;

    public TrieTree() {
        this.head = new Node(0, 0);
    }

    public void insert(String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        char[] chars = str.toCharArray();
        Node cur = head;
        // 路过节点
        for (int i = 0; i < chars.length; i++) {
            head.pass += 1;
            if (cur.next[chars[i] - 'a'] == null) {
                cur.next[chars[i] - 'a'] = new Node(1, 0);
            } else {
                cur.next[chars[i] - 'a'].pass += 1;
            }
            cur = cur.next[chars[i] - 'a'];
        }
        cur.end += 1;
    }

    public int search(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chars = str.toCharArray();
        Node cur = head;
        for (char aChar : chars) {
            cur = cur.next[aChar - 'a'];
            if (cur == null) {
                return 0;
            }
        }
        return cur.end;
    }

    public int prefixNumber(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chars = str.toCharArray();
        Node cur = head;
        for (char aChar : chars) {
            cur = cur.next[aChar - 'a'];
            if (cur == null) {
                return 0;
            }
        }
        return cur.pass;
    }

    public void delete(String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        Node cur = head;
        char[] chars = str.toCharArray();
        for (char aChar : chars) {
            cur = cur.next[aChar - 'a'];
            if (cur == null) {
                return;
            }
        }
        cur = head;
        for (int i = 0; i < chars.length; i++) {
            cur.pass--;
            if (cur.next[chars[i] - 'a'].pass - 1 == 0) {
                cur.next[chars[i] - 'a'] = null;
                return;
            }
            cur = cur.next[chars[i] - 'a'];
        }
        cur.end--;
    }

    public static class TrieTree2 {

        private final HashNode head;

        public TrieTree2() {
            head = new HashNode(0, 0);
        }

        public void insert(String str) {
            if (str == null || str.length() == 0) {
                return;
            }
            HashNode cur = head;
            char[] chars = str.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                cur.pass++;
                if (!cur.next.containsKey(chars[i])) {
                    cur.next.put(chars[i], new HashNode(0, 0));
                }
                cur.end = i == chars.length - 1 ? cur.end + 1 : cur.end;
                cur = cur.next.get(chars[i]);
            }
        }

    }

    public static void test() {
        HashMap<Character, HashNode> map = new HashMap<>();
        map.put('a', new HashNode(10, 20));
        System.out.println(map.containsKey('b'));
    }

    public static void main(String[] args) {
        test();
    }
}
