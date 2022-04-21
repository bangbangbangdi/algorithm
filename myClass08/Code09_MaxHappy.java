package myClass08;

import java.util.ArrayList;
import java.util.List;

public class Code09_MaxHappy {
    public static class Employee {
        public int happy;
        List<Employee> subordinates;

        public Employee(int happy) {
            this.happy = happy;
            this.subordinates = new ArrayList<Employee>();
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "happy=" + happy +
                    ", subordinates=" + subordinates +
                    '}';
        }
    }

    public static Employee generateRandomEmployee(int maxLevel, int maxHappy, int curLevel, int maxSubordinateSize) {
        if (curLevel > maxLevel || Math.random() > 0.7) {
            return null;
        }
        Employee employee = getEmployee(maxHappy);
        int curSize = (int) ((maxSubordinateSize + 1) * Math.random());
        for (int i = 0; i < curSize; i++) {
            Employee emp = generateRandomEmployee(maxLevel, maxHappy, curLevel + 1, maxSubordinateSize);
            if (emp != null) {
                employee.subordinates.add(emp);
            }
        }
        return employee;
    }

    public static Employee getEmployee(int maxHappy) {
        int happy = (int) ((maxHappy + 1) * Math.random());
        return new Employee(happy);
    }

    public static int maxHappy(Employee boss) {
        if (boss == null) {
            return 0;
        }

        return Math.max(process(boss).yes, process(boss).no);
    }

    public static class Info {
        public int yes;
        public int no;

        public Info(int yes, int no) {
            this.yes = yes;
            this.no = no;
        }
    }

    public static Info process(Employee emp) {
        if (emp == null) {
            return new Info(emp.happy, 0);
        }
        int yes = emp.happy;
        int no = 0;

        for (Employee next : emp.subordinates) {
            yes += process(next).no;
            no += process(next).yes;
        }

        return new Info(yes, no);
    }


    public static void main(String[] args) {
        Employee boss = generateRandomEmployee(3, 10, 1, 10);
//        if (boss != null) {
//            System.out.println(boss.happy);
//            for (int i = 0; i < boss.subordinates.size(); i++) {
//                System.out.println(boss.subordinates.get(i).happy);
//            }
//        }
        System.out.println(maxHappy(boss));
    }

}
