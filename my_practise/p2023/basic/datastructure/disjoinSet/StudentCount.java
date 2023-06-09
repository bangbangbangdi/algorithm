package my_practise.p2023.basic.datastructure.disjoinSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * projectName:    algorithm1
 * package:        my_practise.p2023.basic.datastructure.disjoinSet
 * className:      StudentCount
 * author:     BangDi
 * description:
 * // 每个学生有三个字段,任意两个学生间,只要有一个字段相同,那么就认为他们是同一个人
 * // 给一个学生List请返回学生人数
 * date:    2023/6/9 9:23
 * version:    1.0
 */
public class StudentCount {

    public static class Student {
        public String a;
        public String b;
        public String c;

        public Student(String a, String b, String c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    public static int studentCount(List<Student> list) {
        if (list == null || list.isEmpty()) {
            return 0;
        }
        HashMap<String, Student> aMap = new HashMap<>();
        HashMap<String, Student> bMap = new HashMap<>();
        HashMap<String, Student> cMap = new HashMap<>();
        UnionFind.UnionSet<Student> unionSet = new UnionFind.UnionSet<Student>(list);
        for (Student stu : list) {
            if (aMap.containsKey(stu.a) || bMap.containsKey(stu.b) || cMap.containsKey(stu.c)) {
                unionSet.union(aMap.get(stu.a), stu);
                unionSet.union(bMap.get(stu.b), stu);
                unionSet.union(cMap.get(stu.c), stu);
            }
            aMap.put(stu.a, stu);
            bMap.put(stu.b, stu);
            cMap.put(stu.c, stu);
        }
        return unionSet.size.size();
    }

    public static void test() {
        Student s1 = new Student("A", "23", "甲");
        Student s2 = new Student("B", "23", "乙");
        Student s3 = new Student("C", "24", "丙");
        Student s4 = new Student("D", "23", "丙");
        List<Student> arr = new ArrayList<>();
        arr.add(s1);
        arr.add(s2);
        arr.add(s3);
        arr.add(s4);
        System.out.println(studentCount(arr));
    }

    public static void main(String[] args) {
        test();
    }

}