package myClass04;


import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Code01_Comparator {
    public static class Student {
        private int age;
        private int id;
        private String name;

        public Student(int age, int id, String name) {
            this.age = age;
            this.id = id;
            this.name = name;
        }
    }

    public static void printAllStudent(Student[] students) {
        for (int i = 0; i < students.length; i++) {
            System.out.println(students[i].name + "--" + students[i].age + "--" + students[i].id);
        }
    }

    public static class IdAscendComparator implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            return o1.id - o2.id;
        }
    }

    public static class IdDescendComparator implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            return o2.id - o1.id;
        }
    }

    public static class AgeAscendComparator implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            return o1.age - o2.age;
        }
    }

    public static class AgeDescendComparator implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            return o2.age - o1.age;
        }
    }

    public static class IdInAgeIn implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            return o1.id == o2.id ? (o1.age - o2.age) : (o1.id - o2.id);
        }
    }

    public static class MyCom implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }

    public static void main(String[] args) {
        Student stu1 = new Student(10, 111, "咩咩");
        Student stu4 = new Student(15, 111, "咩咩");
        Student stu2 = new Student(20, 222, "吱吱");
        Student stu5 = new Student(25, 222, "吱吱");
        Student stu3 = new Student(30, 333, "嘻嘻");
        Student[] students = {stu1, stu2, stu3, stu4, stu5};

        System.out.println("====第一次打印===");
        Arrays.sort(students, new IdAscendComparator());
        printAllStudent(students);
        System.out.println("====第二次打印===");
        Arrays.sort(students, new IdDescendComparator());
        printAllStudent(students);
        System.out.println("====第三次打印===");
        Arrays.sort(students, new IdInAgeIn());
        printAllStudent(students);

        System.out.println("============================");
        System.out.println("============================");
        System.out.println("============================");

        PriorityQueue<Integer> heap = new PriorityQueue<>(new MyCom());
        heap.add(1);
        heap.add(2);
        heap.add(3);
        heap.add(4);
        while(!heap.isEmpty()){
            System.out.println(heap.poll());
        }
    }
}