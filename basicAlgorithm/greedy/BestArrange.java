package basicAlgorithm.greedy;

//一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲。
//给你每一个项目开始的时间和结束的时间
//你来安排宣讲的日程，要求会议室进行的宣讲的场次最多。
//返回最多的宣讲场次。

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

public class BestArrange {
    public static class Program {
        public int start;
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static int compare(Program[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return process(arr, 0, 0);
//        return process(arr, 0);
    }

    public static int process(Program[] arr, int index) {
        if (index == arr.length) {
            return sumProgram(arr);
        }
        int max = Integer.MIN_VALUE;
        for (int i = index; i < arr.length; i++) {
            swap(arr, index, i);
            max = Math.max(max, process(arr, index + 1));
            swap(arr, index, i);
        }
        return max;
    }

    public static int process(Program[] arr, int finishCount, int endLine) {
        if (arr == null) {
            return finishCount;
        }
        int res = finishCount;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].start >= endLine) {
                res = Math.max(res, process(copyButExcept(arr, i), finishCount + 1, arr[i].end));
            }
        }
        return res;
    }

    public static Program[] copyButExcept(Program[] arr, int index) {
        if (arr.length == 1) {
            return null;
        }
        Program[] programs = new Program[arr.length - 1];
        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i != index) {
                programs[j++] = arr[i];
            }
        }
        return programs;
    }

    public static int sumProgram(Program[] arr) {
        int res = 0;
        int end = 0;
        for (Program program : arr) {
            if (program.start >= end) {
                end = program.end;
                res++;
            }
        }
        return res;
    }

    public static void swap(Object[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        Object tem = arr[i];
        arr[i] = arr[j];
        arr[j] = tem;
    }

    public static int bestArrange(Program[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Arrays.sort(arr, new MyComparator());
        return sumProgram(arr);
    }

    public static class MyComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o1.end - o2.end;
        }
    }

    public static Program[] generatePrograms(int maxEnd, int maxSize) {
        int len = (int) ((maxSize + 1) * Math.random());
        Program[] programs = new Program[len];
        for (int i = 0; i < len; i++) {
            int r1 = (int) ((maxEnd + 1) * Math.random());
            int r2 = (int) ((maxEnd + 1) * Math.random());
            if (r1 == r2) {
                programs[i] = new Program(r1, r2 + 1);
            } else {
                programs[i] = new Program(Math.min(r1, r2), Math.max(r1, r2));
            }
        }
        return programs;
    }

    public static void printPrograms(Program[] programs) {
        if (programs == null || programs.length == 0) {
            return;
        }
        for (Program program : programs) {
            System.out.print(program.start + ":" + program.end + "|");
        }
    }

    public static void test() {
        int testTime = 1000000;
        int maxSize = 12;
        int maxEnd = 24;
        boolean succeed = true;
        for (int i = 0; i < testTime && succeed; i++) {
            Program[] programs = generatePrograms(maxEnd, maxSize);
            int bs = bestArrange(programs);
            int compare = compare(programs);
            if (bs != compare) {
                succeed = false;
                printPrograms(programs);
                System.out.println("bs : " + bs);
                System.out.println("compare : " + compare);
            }
        }

        System.out.println(succeed ? "Nice" : "Fuck");
    }

    public static void main(String[] args) {
        test();
//        Program p1 = new Program(17, 18);
//        Program p2 = new Program(1, 20);
//        Program p3 = new Program(0, 17);
//        Program p4 = new Program(2, 3);
//        Program[] arr = {p1,p2,p3,p4};
//        System.out.println(compare(arr));
    }
}
