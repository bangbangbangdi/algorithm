package my_practise.p2024.pro.algorithm;

import java.util.*;

/**
 * projectName:    algorithm
 * package:        my_practise.p2024.pro.algorithm
 * className:      WordSearch
 * author:     BangDi
 * description:  TODO
 * date:    2024/7/23 22:37
 * version:    1.0
 */
public class WordSearch {

    public static class Node {
        Node[] next;
        int pass;
        int end;

        public Node() {
            this.next = new Node[26];
            this.pass = 0;
            this.end = 0;
        }
    }

    public static class Trie {
        public Node head;
    }


    public static List<String> wordSearch(char[][] boards, String[] words) {
        if (boards == null || boards.length == 0 || boards[0] == null || boards[0].length == 0 || words == null || words.length == 0) {
            return null;
        }
        StringBuilder path = new StringBuilder();
        List<String> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            int val = map.containsKey(word) ? map.get(word) + 1 : 1;
            map.put(word, val);
        }
        for (int i = 0; i < boards.length; i++) {
            for (int j = 0; j < boards[0].length; j++) {
                process(boards, words, map, i, j, path, res);
            }
        }

        return res;
    }

    public static void process(char[][] boards, String[] words, Map<String, Integer> map, int i, int j, StringBuilder path, List<String> res) {
        int M = boards.length;
        int N = boards[0].length;
        char cha = boards[i][j];
        path.append(cha);
        int len = path.length();
        boards[i][j] = 0;
        Set<String> set = new HashSet<>();
        for (String word : words) {
            if (word.length() < len || map.get(word) < 1) {
                continue;
            }
            if (!set.contains(word) && word.substring(0, len).contentEquals(path)) {
                set.add(word);
                if (word.contentEquals(path)) {
                    res.add(word);
                    map.put(word, map.get(word) - 1);
                    continue;
                }
                if (i > 0 && boards[i - 1][j] != 0) {
                    process(boards, words, map, i - 1, j, path, res);
                }
                if (i + 1 < M && boards[i + 1][j] != 0) {
                    process(boards, words, map, i + 1, j, path, res);
                }
                if (j > 0 && boards[i][j - 1] != 0) {
                    process(boards, words, map, i, j - 1, path, res);
                }
                if (j + 1 < N && boards[i][j + 1] != 0) {
                    process(boards, words, map, i, j + 1, path, res);
                }
            }
        }
        path.deleteCharAt(len - 1);
        boards[i][j] = cha;
    }


    public static void main(String[] args) {
        char[][] boards = {{'o', 't', 't', 'o'}, {'b', 'e', 'l', 'z'}, {'a', 'n', 'g', 'o'}, {'t', 'i', 'k', 'o'}};
        String[] words = {"bang", "tin", "otto", "otto", "otto", "elk", "zoom", "4396", "TheShy"};
        List<String> res = wordSearch(boards, words);
        for (String s : res) {
            System.out.println(s);
        }
    }

}
