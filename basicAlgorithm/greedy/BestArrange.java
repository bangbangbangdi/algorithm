package basicAlgorithm.greedy;

//一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲。
//给你每一个项目开始的时间和结束的时间
//你来安排宣讲的日程，要求会议室进行的宣讲的场次最多。
//返回最多的宣讲场次。

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
        return 1;
    }

    public static int process(Program[] arr , int index){
        if (index == arr.length){
            return sumProgram(arr);
        }
        for (int i = 0; i < arr.length; i++) {

        }
        return 1;
    }


    public static int sumProgram(Program[] arr){
        int res = 0;
        int end = arr[0].end;
        for (Program program : arr) {
            if (program.start > end) {
                end = program.end;
                res++;
            }
        }
        return res;
    }

}
