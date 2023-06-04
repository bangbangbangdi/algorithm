package my_practise.p2023.basic.datastructure;

import java.util.ArrayList;
import java.util.List;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.basic.datastructure
 * className:      MaxHappy
 * author:     BangDi
 * description:
 * 派对都最大快乐值
 * 员工信息定义如下:
 * class Employee{
 *     public int happy; // 这名员工可以带来的快乐值
 *     List<Employee> subordinates; // 这名员工有哪些直接下级
 * }
 * 员工的每个员工都符合Employee类都描述,整个公司都人员结构可以看作是一颗标准都,没有环的多叉树,树的头节点是公司唯一的老板
 * 除老板之外的每个员工都有唯一的直接上级,叶节点是没有任何下属的基层员工(subordinates列表为空)
 * 初基层员工外,每个员工都有一个或多个直接下级
 * --
 * 这个公司现在要办party,你可以决定哪些员工来,哪些员工不来,规则:
 * 1.如果某个员工来了,那么这个员工的所有直接下级都不能来
 * 2.派对的整体快乐值是所有到场员工快乐值的累加
 * 3.你的目标是让派对的整体快乐值尽量大
 * --
 * 给定一颗多叉树的头节点boss,请返回派对的最大快乐值
 * date:    2023/6/4 00:44
 * version:    1.0
 */
public class MaxHappy {

    public static int maxHappy(Employee boss){
        if (boss == null){
            return 0;
        }
        return Math.max(process(boss).participate, process(boss).notParticipate);
    }

    public static Info process(Employee cur){
        if (cur.subordinates.isEmpty() ){
            return new Info(0,cur.happy);
        }
        int participate = cur.happy;
        int notParticipate = 0;
        for (Employee employee : cur.subordinates) {
            Info subInfo = process(employee);
            participate += subInfo.notParticipate;
            notParticipate += Math.max(subInfo.notParticipate, subInfo.participate);
        }
        return new Info(notParticipate,participate);
    }

    public static class Info{
        int notParticipate;
        int participate;

        public Info(int notParticipate, int participate) {
            this.notParticipate = notParticipate;
            this.participate = participate;
        }
    }

    public static class Employee{
        public int happy;
        public List<Employee> subordinates;

        public Employee(int happy) {
            this.happy = happy;
            subordinates = new ArrayList<>();
        }
    }

    public static int maxHappy1(Employee boss) {
        if (boss == null) {
            return 0;
        }
        return process1(boss, false);
    }

    public static int process1(Employee cur, boolean up) {
        if (up) {
            int ans = 0;
            for (Employee next : cur.subordinates) {
                ans += process1(next, false);
            }
            return ans;
        } else {
            int p1 = cur.happy;
            int p2 = 0;
            for (Employee next : cur.subordinates) {
                p1 += process1(next, true);
                p2 += process1(next, false);
            }
            return Math.max(p1, p2);
        }
    }


    // for test
    public static Employee genarateBoss(int maxLevel, int maxNexts, int maxHappy) {
        if (Math.random() < 0.02) {
            return null;
        }
        Employee boss = new Employee((int) (Math.random() * (maxHappy + 1)));
        genarateNexts(boss, 1, maxLevel, maxNexts, maxHappy);
        return boss;
    }

    // for test
    public static void genarateNexts(Employee e, int level, int maxLevel, int maxNexts, int maxHappy) {
        if (level > maxLevel) {
            return;
        }
        int nextsSize = (int) (Math.random() * (maxNexts + 1));
        for (int i = 0; i < nextsSize; i++) {
            Employee next = new Employee((int) (Math.random() * (maxHappy + 1)));
            e.subordinates.add(next);
            genarateNexts(next, level + 1, maxLevel, maxNexts, maxHappy);
        }
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxNexts = 7;
        int maxHappy = 100;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            Employee boss = genarateBoss(maxLevel, maxNexts, maxHappy);
            if (maxHappy1(boss) != maxHappy(boss)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
