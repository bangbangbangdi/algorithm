package basicDataStructure.binaryTree;

import java.util.List;

public class MaxHappy {

    public static class Employee {
        public int happy;
        List<Employee> subordinates;

        public Employee(int happy, List<Employee> subordinates) {
            this.happy = happy;
            this.subordinates = subordinates;
        }
    }

    public static int maxHappy(Employee head) {
        if (head == null) {
            return 0;
        }
        Info info = process(head);
        return Math.max(info.joinMax, info.notJoinMax);
    }

    public static Info process(Employee head) {
        if (head == null) {
            return new Info(0, 0);
        }
        int joinMax = head.happy;
        int notJoinMax = 0;


        for (int i = 0; i < head.subordinates.size(); i++) {
            Info sub = process(head.subordinates.get(i));
            joinMax += sub.notJoinMax;
            notJoinMax += Math.max(sub.joinMax, sub.notJoinMax);
        }

        return new Info(joinMax, notJoinMax);
    }

    public static class Info {
        public int joinMax;
        public int notJoinMax;

        public Info(int joinMax, int notJoinMax) {
            this.joinMax = joinMax;
            this.notJoinMax = notJoinMax;
        }
    }

}
