package my_practise.basic.myClass05;

import java.util.HashMap;

public class Code02_TrieTree {

    // 前缀树只包含26个小写字母的情况
    public static class Node1 {
        public int pass;
        public int end;
        public Node1[] nexts;

        public Node1() {
            nexts = new Node1[26];
            pass = 0;
            end = 0;
        }
    }

    public static class Trie1 {
        private Node1 root;

        public Trie1() {
            root = new Node1();
        }

        public void insert(String str) {
            if (str == null) {
                return;
            }
            char[] chars = str.toCharArray();
            Node1 node = root;
            node.pass++;
            int path = 0;
            for (int i = 0; i < chars.length; i++) {
                path = chars[i] - 'a';
                if (node.nexts[path] == null) {
                    node.nexts[path] = new Node1();
                }
                node = node.nexts[path];
                node.pass++;
            }
            node.end++;
        }

        public int search(String str) {
            if (str == null) {
                return 0;
            }
            char[] chars = str.toCharArray();
            Node1 node = root;
            int path = 0;
            for (int i = 0; i < chars.length; i++) {
                path = chars[i] - 'a';
                if (node.nexts[path] == null) {
                    return 0;
                }
                node = node.nexts[path];
            }
            return node.end;
        }

        public int prefixNumber(String pre) {
            if (pre == null) {
                return 0;
            }
            char[] chars = pre.toCharArray();
            Node1 node = root;
            int path = 0;
            for (int i = 0; i < chars.length; i++) {
                path = chars[i] - 'a';
                if (node.nexts[path] == null) {
                    return 0;
                }
                node = node.nexts[path];
            }
            return node.pass;
        }

        public void delete(String str) {
            if (search(str) == 0) {
                return;
            }
            Node1 node = root;
            node.pass--;
            char[] chars = str.toCharArray();
            int path = 0;
            for (int i = 0; i < chars.length; i++) {
                path = chars[i] - 'a';
                if (--node.nexts[path].pass == 0) {
                    node.nexts[path] = null;
                    return;
                }
                node = node.nexts[path];
            }
            node.end--;
        }
    }

    public static class Node2 {
        public int pass;
        public int end;
        public HashMap<Integer, Node2> nexts;

        public Node2() {
            pass = 0;
            end = 0;
            nexts = new HashMap<>();
        }
    }

    // 前缀树不只包含26个小写字母的情况
    public static class Trie2 {
        public Node2 root;

        public Trie2() {
            root = new Node2();
        }

        public void insert(String str) {
            if (str == null) {
                return;
            }
            char[] chars = str.toCharArray();
            Node2 node = root;
            node.pass++;
            int path = 0;
            for (int i = 0; i < chars.length; i++) {
                path = (int) chars[i];
                if (!node.nexts.containsKey(path)) {
                    node.nexts.put(path, new Node2());
                }
                node = node.nexts.get(path);
                node.pass++;
            }
            node.end++;
        }

        public int search(String str) {
            if (str == null) {
                return 0;
            }
            char[] chars = str.toCharArray();
            int path = 0;
            Node2 node = root;
            for (int i = 0; i < chars.length; i++) {
                path = (int) chars[i];
                if (!node.nexts.containsKey(path)) {
                    return 0;
                }
                node = node.nexts.get(path);
            }
            return node.end;
        }

        public void delete(String str) {
            if (search(str) == 0) {
                return;
            }
            char[] chars = str.toCharArray();
            Node2 node = root;
            node.pass--;
            int path = 0;
            for (int i = 0; i < chars.length; i++) {
                path = (int) chars[i];
                if (--node.nexts.get(path).pass == 0) {
                    node.nexts.remove(path);
                    return;
                }
                node = node.nexts.get(path);
            }
            node.end--;
        }

        public int prefixNumber(String str) {
            if (str == null) {
                return 0;
            }
            char[] chars = str.toCharArray();
            Node2 node = root;
            int path = 0;
            for (int i = 0; i < chars.length; i++) {
                path = (int) chars[i];
                if (!node.nexts.containsKey(path)) {
                    return 0;
                }
                node = node.nexts.get(path);
            }
            return node.pass;
        }
    }

    public static class Right {
        private HashMap<String, Integer> box;

        public Right() {
            box = new HashMap<>();
        }

        public void insert(String str) {
            if (!box.containsKey(str)) {
                box.put(str, 1);
            } else {
                box.put(str, box.get(str) + 1);
            }
        }

        public int search(String str) {
            if (!box.containsKey(str)) {
                return 0;
            } else {
                return box.get(str);
            }
        }

        public void delete(String str) {

            if (!box.containsKey(str)) {
                return;
            }
            if (box.get(str) == 1) {
                box.remove(str);
            } else {
                box.put(str, box.get(str) - 1);
            }
        }

        public int prefixNumber(String pre) {
            int count = 0;
            for (String str : box.keySet()) {
                if (str.startsWith(pre)) {
                    count += box.get(str);
                }
            }
            return count;
        }
    }

    // for test
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 6);
            ans[i] = (char) (97 + value);
        }
        return String.valueOf(ans);
    }

    // for test
    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }


    public static void main(String[] args) {
        int arrLen = 100;
        int strLen = 20;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            String[] arr = generateRandomStringArray(arrLen, strLen);
            Trie1 trie1 = new Trie1();
            Trie2 trie2 = new Trie2();
            Right right = new Right();
            for (int j = 0; j < arr.length; j++) {
                double decide = Math.random();
                if (decide < 0.25) {
                    trie1.insert(arr[j]);
                    trie2.insert(arr[j]);
                    right.insert(arr[j]);
                } else if (decide < 0.5) {
                    trie1.delete(arr[j]);
                    trie2.delete(arr[j]);
                    right.delete(arr[j]);
                } else if (decide < 0.75) {
                    int ans1 = trie1.search(arr[j]);
                    int ans2 = trie2.search(arr[j]);
                    int ans3 = right.search(arr[j]);
                    if (ans1 != ans2 || ans2 != ans3) {
                        System.out.println("Oops!");
                    }
                } else {
                    int ans1 = trie1.prefixNumber(arr[j]);
                    int ans2 = trie2.prefixNumber(arr[j]);
                    int ans3 = right.prefixNumber(arr[j]);
                    if (ans1 != ans2 || ans2 != ans3) {
                        System.out.println("Oops!");
                    }
                }
            }
        }
        System.out.println("finish!");


//
//        int testTime = 500000;
//        int strLen = 20;
//        int arrLen = 10;
//        boolean succeed = true;
//        for (int i = 0; (i < testTime) && succeed; i++) {
//            Trie1 trie1 = new Trie1();
//            Trie2 trie2 = new Trie2();
//            Right right = new Right();
//            String[] arr = Tools.generateRandomStringArray(arrLen, strLen);
////            Tools.printStringArray(arr);
////            System.out.println();
//            for (int j = 0; j < arr.length; j++) {
//                double decide = Math.random();
//                if (decide < 0.25) {
////                    System.out.println("insert "+arr[j]);
//                    trie1.insert(arr[j]);
//                    trie2.insert(arr[j]);
//                    right.insert(arr[j]);
//                } else if (decide < 0.5) {
////                    System.out.println("delete "+arr[j]);
//                    trie1.delete(arr[j]);
//                    trie2.delete(arr[j]);
//                    right.delete(arr[j]);
//                } else if (decide < 0.75) {
////                    System.out.println("search "+arr[j]);
//                    int ans1 = trie1.search(arr[j]);
//                    int ans2 = trie2.search(arr[j]);
//                    int ans3 = right.search(arr[j]);
//                    if ((ans1 != ans2) || (ans2 != ans3)) {
//                        succeed = false;
////                        System.out.println("search " + arr[j]);
////                        System.out.println("ans1 search "+ans1);
////                        System.out.println("ans2 search "+ans2);
////                        System.out.println("ans3 search "+ans3);
//                    }
//                } else {
//                    int ans1 = trie1.prefixNumber(arr[j]);
//                    int ans2 = trie2.prefixNumber(arr[j]);
//                    int ans3 = right.prefixNumber(arr[j]);
//                    if ((ans1 != ans2) || (ans2 != ans3)) {
//                        succeed = false;
////                        System.out.println("prefixNumber " + arr[j]);
////                        System.out.println("ans1 prefixNumber "+ans1);
////                        System.out.println("ans2 prefixNumber "+ans2);
////                        System.out.println("ans3 prefixNumber "+ans3);
//                    }
//                }
//            }
//        }
//        System.out.println(succeed?"Succeed":"Fuck");
    }
}