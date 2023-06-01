package my_practise.p2023.basic.algorithm.greedy;

import tool.Tools;

import java.util.Arrays;
import java.util.Comparator;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.basic.algorithm.greedy
 * className:      BestArrange
 * author:     BangDi
 * description:
 * 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲。
 * 给你每一个项目开始的时间和结束的时间
 * 你来安排宣讲的日程，要求会议室进行的宣讲的场次最多。
 * 返回最多的宣讲场次。
 * date:    2023/6/1 22:17
 * version:    1.0
 */
public class BestArrange {

    static class Program {
        int start;
        int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Program{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }

    }

    public static int bestArrange(Program[] programs) {
        if (programs == null || programs.length < 1) {
            return 0;
        }
        Comparator<Program> com = new Comparator<Program>() {
            @Override
            public int compare(Program o1, Program o2) {
                return o1.end - o2.end;
            }
        };
        Arrays.sort(programs, com);
        int res = 0;
        int end = 0;
        for (Program program : programs) {
            res += program.start >= end ? 1 : 0;
            end = program.start >= end ? program.end : end;
        }
        return res;
    }

    public static int compare(Program[] programs) {
        if (programs == null || programs.length < 1) {
            return 0;
        }
        return process(programs, 0);
    }

    public static int process(Program[] programs, int index) {
        int res = 0;
        int end = 0;
        if (index == programs.length) {
            for (Program program : programs) {
                res += program.start >= end ? 1 : 0;
                end = program.start >= end ? program.end : end;
            }
            return res;
        }
        res = Integer.MIN_VALUE;
        for (int i = index; i < programs.length; i++) {
            Tools.swap(programs, index, i);
            res = Math.max(res, process(programs, index + 1));
            Tools.swap(programs, index, i);
        }
        return res;
    }

    public static void main(String[] args) {
        Program p1 = new Program(9, 10);
        Program p2 = new Program(7, 18);
        Program p3 = new Program(12, 15);
        Program p4 = new Program(15, 17);
        Program p5 = new Program(18, 20);
        Program[] arr = {p1, p2, p3, p4, p5};
//        Program p1 = new Program(17, 18);
//        Program p2 = new Program(1, 20);
//        Program p3 = new Program(0, 17);
//        Program p4 = new Program(2, 3);
//        Program[] arr = {p1, p2, p3, p4};
        System.out.println(bestArrange(arr));
        System.out.println(compare(arr));
    }

}