package basicDataStructure.heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class HeapPro {

    public static class MyHeap<T> {
        private ArrayList<T> heap;
        private HashMap<T, Integer> map;
        private int heapSize;
        private Comparator<? super T> com;

        public MyHeap(Comparator<? super T> com) {
            this.com = com;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public boolean contains(T t) {
            return heap.contains(t);
        }

        public int size() {
            return heapSize;
        }

        private void heapInsert(ArrayList<T> arr, int index) {
            while (com.compare(arr.get(index), arr.get((index - 1) / 2)) < 0) {

            }
        }


        private void swap(int a, int b) {
            T o1 = heap.get(a);
            T o2 = heap.get(b);
            Integer index1 = map.get(o1);
            Integer index2 = map.get(o2);
            heap.set(index1, o2);
            heap.set(index2, o1);
            map.put(o1, index2);
            map.put(o2, index1);
        }
    }
    public static class Student {
        private int age;

        public Student(int age){
            this.age = age;
        }
    }

    public static class StudentComparator implements Comparator<Student>{
        @Override
        public int compare(Student o1, Student o2) {
            return o1.age - o2.age;
        }
    }

    public static void test(){
        Student stu1 = new Student(10);
        Student stu2 = new Student(20);
        StudentComparator com = new StudentComparator();
        System.out.println(com.compare(stu1, stu2));
    }

    public static void main(String[] args) {
        test();
    }
}
