package basicDataStructure.disjoinSet;

// 每个学生有三个字段,任意两个学生间,只要有一个字段相同,那么就认为他们是同一个人
// 给一个学生List请返回学生人数

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StudentCount {

    public static class Student {
        public String aID;
        public String bID;
        public String cID;

        public Student(String aID, String bID, String cID) {
            this.aID = aID;
            this.bID = bID;
            this.cID = cID;
        }
    }

    public static int studentCount(List<Student> list) {
        if (list == null) {
            return 0;
        }
        HashMap<String, Student> aMap = new HashMap<>();
        HashMap<String, Student> bMap = new HashMap<>();
        HashMap<String, Student> cMap = new HashMap<>();
        UnionFind.UnionSet<Student> unionSet = new UnionFind.UnionSet<>(list);
        for (Student stu : list) {
            if (aMap.containsKey(stu.aID) || bMap.containsKey(stu.bID) || cMap.containsKey(stu.cID)) {
                unionSet.union(aMap.get(stu.aID), stu);
                unionSet.union(bMap.get(stu.bID), stu);
                unionSet.union(cMap.get(stu.cID), stu);
            }
            aMap.put(stu.aID, stu);
            bMap.put(stu.bID, stu);
            cMap.put(stu.cID, stu);
        }
        return unionSet.sizes.size();
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
