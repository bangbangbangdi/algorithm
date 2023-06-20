package my_practise.p2023.basic.datastructure.trieTree;

import tool.Tools;

import java.util.HashMap;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.basic.datastructure.trieTree
 * className:      TrieTree
 * author:     BangDi
 * description:  TODO
 * //设计一种结构。用户可以：
 * //        1）void insert(String str)            添加某个字符串，可以重复添加，每次算1个
 * //        2）int search(String str)             查询某个字符串在结构中还有几个
 * //        3)  void delete(String str)           删掉某个字符串，可以重复删除，每次算1个
 * //        4）int prefixNumber(String str)  查询有多少个字符串，是以str做前缀的
 * date:    2023/6/19 22:33
 * version:    1.0
 */
public class TrieTree {

    public static class Trie {
        private Node head;

        public Trie() {
            head = new Node();
        }

        public void insert(String str) {
            if (str == null || str.length() == 0) {
                return;
            }
            Node cur = head;
            for (int i = 0; i < str.length(); i++) {
                int index = str.charAt(i) - 'a';
                if (cur.next[index] == null) {
                    cur.next[index] = new Node();
                }
                cur.next[index].pass++;
                cur = cur.next[index];
            }
            cur.end++;
        }

        public int search(String str) {
            if (str == null || str.length() == 0) {
                return 0;
            }
            Node cur = head;
            for (int i = 0; i < str.length(); i++) {
                int index = str.charAt(i) - 'a';
                if (cur.next[index] == null) {
                    return 0;
                }
            }
            return cur.end;
        }

        public void delete(String str) {
            if (str == null || str.length() == 0) {
                return;
            }
            Node cur = head;
            for (int i = 0; i < str.length(); i++) {
                cur.pass--;
                int index = str.charAt(i) - 'a';
                if (cur.pass == 0) {
                    cur.next[index] = null;
                    return;
                }
            }
            cur.end--;
        }

        public int prefixNumber(String str){
            if (str == null){
                return 0;
            }
            if (str.length() == 0){
                return head.pass;
            }
            Node cur = head;
            for (int i = 0; i < str.length(); i++) {
                int index = str.charAt(i) - 'a';
                if (cur.next[index] == null) {
                    return 0;
                }
            }
            return cur.pass;
        }
    }

    public static class Right {
        private HashMap<String, Integer> map;

        public Right() {
            map = new HashMap<>();
        }

        public void insert(String str) {
            if (str == null || str.length() == 0) {
                return;
            }
            if (map.containsKey(str)) {
                map.put(str, map.get(str) + 1);
                return;
            }
            map.put(str, 1);
        }

        public int search(String str) {
            if (str == null || str.length() == 0) {
                return 0;
            }
            return map.get(str) == null ? 0 : map.get(str);
        }

        public void delete(String str) {
            if (search(str) == 0) {
                return;
            }
            if (map.get(str) == 1) {
                map.remove(str);
                return;
            }
            map.put(str, map.get(str) - 1);
        }

        public int prefixNumber(String str) {
            if (str == null || str.length() == 0) {
                return 0;
            }
            int res = 0;
            for (String s : map.keySet()) {
                res += s.startsWith(str) ? map.get(s) : 0;
            }
            return res;
        }
    }

    public static void test() {
        int testTime = 1000000;
        int arrMaxSize = 10;
        int strMaxSize = 10;
        boolean succeed = true;
        for (int i = 0; i < testTime && succeed; i++) {
            String[] arr = Tools.generateRandomStringArray(arrMaxSize, strMaxSize);
            double decide = Math.random();
            Trie trie1 = new Trie();
            Right right = new Right();
            for (String s : arr) {
                if (decide < 0.25) {
                    trie1.insert(s);
                    right.insert(s);
                } else if (decide < 0.5) {
                    trie1.delete(s);
                    right.delete(s);
                } else if (decide < 0.75) {
                    int t1 = trie1.search(s);
                    int r1 = right.search(s);
                    if (t1 != r1) {
                        succeed = false;
                    }
                } else {
                    int pre1 = trie1.prefixNumber(s);
                    int rPre1 = right.prefixNumber(s);
                    if (pre1 != rPre1) {
                        succeed = false;
                    }
                }
            }
        }
        System.out.println(succeed ? "Nice" : "Fuck");
    }

    public static void main(String[] args) {
        test();
    }

}
