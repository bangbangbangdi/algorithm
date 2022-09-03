package basicAlgorithm.greedy;
//输入: 正数数组costs、正数数组profits、正数K、正数M
//costs[i]表示i号项目的花费
//profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
//K表示你只能串行的最多做k个项目
//M表示你初始的资金
//说明: 每做完一个项目，马上获得的收益，可以支持你去做下一个项目。不能并行的做项目。
//输出：你最后获得的最大钱数。


import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class IPO {

    public static int IPO(int[] costs, int[] profits, int K, int M) {
        if (costs == null || profits == null || costs.length == 0 || costs.length != profits.length || K == 0) {
            return M;
        }
        PriorityQueue<Project> heap = new PriorityQueue<>(new MaxProfitComparator());
        ArrayList<Project> list = new ArrayList<>();
        boolean done = false;
        for (int i = 0; i < costs.length; i++) {
            heap.add(new Project(costs[i], profits[i]));
        }
        while (K != 0 && !done) {
            K--;
            done = true;
            while (!heap.isEmpty()) {
                Project p = heap.poll();
                if (p.cost <= M) {
                    M += p.profit;
                    done = false;
                    break;
                } else {
                    list.add(p);
                }
            }
            heap.addAll(list);
        }
        return M;
    }

    public static int IPO2(int[] costs, int[] profits, int K, int M) {
        if (costs == null || profits == null || costs.length != profits.length || costs.length == 0 || K == 0) {
            return M;
        }
        PriorityQueue<Project> minCost = new PriorityQueue<>(new MinCostComparator());
        PriorityQueue<Project> maxProfit = new PriorityQueue<>(new MaxProfitComparator());
        for (int i = 0; i < costs.length; i++) {
            minCost.add(new Project(costs[i], profits[i]));
        }
        for (int i = K; i >= 0; i--) {
            while (!minCost.isEmpty() && minCost.peek().cost <= M) {
                maxProfit.add(minCost.poll());
            }
            if (maxProfit.isEmpty()) {
                return M;
            }
            M += maxProfit.poll().profit;
        }
        return M;
    }

    public static class Project {
        public int cost;
        public int profit;

        public Project(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }
    }

    public static class MaxProfitComparator implements Comparator<Project> {

        @Override
        public int compare(Project o1, Project o2) {
            return o2.profit - o1.profit;
        }
    }

    public static class MinCostComparator implements Comparator<Project> {
        @Override
        public int compare(Project o1, Project o2) {
            return o1.cost - o2.cost;
        }
    }

    public static void main(String[] args) {
        int[] costs = {3, 3, 5};
        int[] profits = {5, 10, 2};
        int K = 3;
        int M = 3;
        System.out.println(IPO(costs, profits, K, M));
        System.out.println(IPO2(costs, profits, K, M));
    }

}
