package myClass09;

import java.util.Arrays;
import java.util.Comparator;

public class Code04_BestArrange {

    public static class Program {
        public int start;
        public int end;

        @Override
        public String toString() {
            return "Program{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static int bestArrange(Program[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        return process(0, 0, arr);
    }

    public static int process(int start, int done, Program[] arr) {
        if (arr == null || arr.length == 0) {
            return done;
        }
        int max = done;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i].start < start) {
                continue;
            }
            max = Math.max(max, process(arr[i].end, done + 1, copyAndExcept(arr, i)));
        }

        return max;
    }

    public static Program[] copyAndExcept(Program[] arr, int index) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        Program[] programs = new Program[arr.length - 1];
        int programsIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i == index) {
                continue;
            }
            programs[programsIndex++] = arr[i];
        }
        return programs;
    }

    public static class MyComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o1.end - o2.end;
        }
    }

    public static int bestArrange2(Program[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Arrays.sort(arr, new MyComparator());
        int ans = 0;
        int end = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].start < end) {
                continue;
            }
            end = arr[i].end;
            ans++;
        }
        return ans;
    }

    public static Program[] generateProgram(int maxEnd, int maxSize) {
        int size = (int) (Math.random() * (maxSize + 1));
        Program[] programs = new Program[size];
        for (int i = 0; i < programs.length; i++) {
            int start = (int) (Math.random() * (maxEnd));
            int end = (int) ((maxEnd - start) * Math.random()) + start + 1;
            programs[i] = new Program(start, end);
        }
        return programs;
    }

    public static void main(String[] args) {
        int testTimes = 500000;
        int maxSize = 15;
        int maxEnd = 24;
        boolean succeed = true;
        for (int i = 0; i < testTimes && succeed; i++) {
            Program[] programs = generateProgram(maxEnd, maxSize);
            if (bestArrange(programs) != bestArrange2(programs)) {
                succeed = false;
            }
        }
        System.out.println(succeed ? "Nice" : "F");
    }
}
