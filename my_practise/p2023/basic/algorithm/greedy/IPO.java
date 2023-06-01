package my_practise.p2023.basic.algorithm.greedy;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.basic.algorithm.greedy
 * className:      IPO
 * author:     BangDi
 * description:
 * 输入: 正数数组costs、正数数组profits、正数K、正数M
 * costs[i]表示i号项目的花费
 * profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
 * K表示你只能串行的最多做k个项目
 * M表示你初始的资金
 * 说明: 每做完一个项目，马上获得的收益，可以支持你去做下一个项目。不能并行的做项目。
 * 输出：你最后获得的最大钱数。
 * date:    2023/6/1 23:25
 * version:    1.0
 */
public class IPO {

    public static int ipo(int[] costs, int[] profits, int K, int M) {
        if (costs == null || costs.length < 1 || profits == null || profits.length < 1 || K == 0 || M < 0) {
            return 0;
        }
        PriorityQueue<Project> heap = new PriorityQueue<>(new Comparator<Project>() {
            @Override
            public int compare(Project o1, Project o2) {
                return o2.profit - o1.profit;
            }
        });
        for (int i = 0; i < costs.length; i++) {
            heap.add(new Project(costs[i], profits[i]));
        }
        Stack<Project> stack = new Stack<>();
        while (!heap.isEmpty() && K != 0) {
            Project project = heap.poll();
            if (project.cost <= M) {
                M += project.profit;
                K--;
                while (!stack.empty()) {
                    heap.add(stack.pop());
                }
            } else {
                stack.push(project);
            }
        }
        return M;
    }

    static class Project {
        int cost;
        int profit;

        public Project(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }

        @Override
        public String toString() {
            return "Project{" +
                    "cost=" + cost +
                    ", profit=" + profit +
                    '}';
        }
    }

    public static void main(String[] args) {
        int[] costs = {3, 30, 5};
        int[] profits = {5, 10, 2};
        int K = 3;
        int M = 3;
        System.out.println(ipo(costs, profits, K, M));
//        System.out.println(IPO2(costs, profits, K, M));
    }
}