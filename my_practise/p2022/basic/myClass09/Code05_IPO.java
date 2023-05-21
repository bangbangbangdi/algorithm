package my_practise.p2022.basic.myClass09;


import tool.Tools;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Code05_IPO {

    public static class Program {
        public int cost;
        public int profit;

        public Program(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }

        @Override
        public String toString() {
            return "Program{" +
                    "cost=" + cost +
                    ", profit=" + profit +
                    '}';
        }
    }

    public static Program[] generateProgram(int[] costs, int[] profits) {
        if (costs == null || profits == null || costs.length != profits.length) {
            return null;
        }
        Program[] programs = new Program[costs.length];
        for (int i = 0; i < costs.length; i++) {
            programs[i] = new Program(costs[i], profits[i]);
        }
        return programs;
    }

    public static int findMaximizedCapital(int[] costs, int[] profits, int K, int M) {
        if (costs == null || profits == null || costs.length != profits.length) {
            return 0;
        }
        Program[] programs = generateProgram(costs, profits);
        return process(K, M, programs);
    }

    public static int process(int K, int M, Program[] programs) {
        if (K == 0 || programs == null || programs.length == 0) {
            return M;
        }
        int ans = M;

        for (int i = 0; i < programs.length; i++) {
            if (programs[i].cost > M) {
                continue;
            }
            ans = Math.max(ans, process(K - 1, M + programs[i].profit, copyAndExcept(programs, i)));
        }

//        for (Program p : programs) {
//            System.out.println(p);
//        }
//        System.out.println("===============");
//        System.out.println("ans " + ans);
        return ans;
    }

    public static Program[] copyAndExcept(Program[] programs, int index) {
        Program[] copy = new Program[programs.length - 1];
        int copyIndex = 0;
        for (int i = 0; i < programs.length; i++) {
            if (i == index) {
                continue;
            }
            copy[copyIndex++] = programs[i];
        }
        return copy;
    }

    public static class MinCostComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o1.cost - o2.cost;
        }
    }

    public static class MaxProfitComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o2.profit - o1.profit;
        }
    }

    public static int findMaximizedCapital2(int[] costs, int[] profits, int K, int M) {
        if (costs == null || profits == null || costs.length != profits.length) {
            return 0;
        }
        Program[] programs = generateProgram(costs, profits);
        PriorityQueue<Program> minCostHeap = new PriorityQueue<>(new MinCostComparator());
        PriorityQueue<Program> maxProfitHeap = new PriorityQueue<>(new MaxProfitComparator());
        for (int i = 0; i < programs.length; i++) {
            minCostHeap.offer(programs[i]);
        }

//        for(Program p:minCostHeap){
//            System.out.println(p);
//        }

        while (K > 0) {
            while (minCostHeap.peek() != null && minCostHeap.peek().cost <= M) {
                maxProfitHeap.offer(minCostHeap.poll());
            }
            if (maxProfitHeap.peek() == null) {
                return M;
            }
            M += maxProfitHeap.poll().profit;
            K--;
        }

        return M;
    }


    public static void main(String[] args) {
        int testTimes = 50000;
        int maxSize = 9;
        int maxCost = 10;
        int maxProfit = 10;
        int maxK = 10;
        int maxM = 5;
        boolean succeed = true;
        for (int i = 0; i < testTimes && succeed; i++) {
            int k = (int) (Math.random() * (maxK + 1));
            int m = (int) (Math.random() * (maxM + 1));
            int[] costs = Tools.generateRandomArray(maxSize, maxCost, false);
            int[] profits = new int[costs.length];
            for (int j = 0; j < costs.length; j++) {
                profits[j] = (int) (Math.random() * (maxProfit + 1));
            }

            if (findMaximizedCapital(costs, profits, k, m) != findMaximizedCapital2(costs, profits, k, m)) {
                succeed = false;
                Program[] programs = generateProgram(costs, profits);
                for (Program p : programs) {
                    System.out.println(p);
                }
            }
        }
        System.out.println(succeed ? "Nice" : "F");
    }

}
