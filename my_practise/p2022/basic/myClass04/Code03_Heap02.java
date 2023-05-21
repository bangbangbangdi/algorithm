package my_practise.p2022.basic.myClass04;


import tool.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class Code03_Heap02 {
    public static class MyHeap<T> {
        private ArrayList<T> heap;
        private int heapSize;
        private HashMap<T, Integer> indexMap;
        private Comparator<? super T> comparator;

        public MyHeap(Comparator<? super T> comp) {
            heap = new ArrayList<>();
            indexMap = new HashMap();
            comparator = comp;
            heapSize = 0;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        private void swap(int i, int j) {
            T o1 = heap.get(i);
            T o2 = heap.get(j);
            indexMap.put(o1, j);
            indexMap.put(o2, i);
            heap.set(i, o2);
            heap.set(j, o1);
        }

        private void heapInsert(int index) {
            while (comparator.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void heapify(int index, int heapSize) {
            int left = index * 2 + 1;
            while (left < heapSize) {
                int largest = (left + 1 < heapSize) && (comparator.compare(heap.get(left + 1), heap.get(left)) < 0) ? left + 1 : left;
                largest = comparator.compare(heap.get(largest), heap.get(index)) < 0 ? largest : index;
                if (largest == index) {
                    break;
                }
                swap(index, largest);
                index = largest;
                left = index * 2 + 1;
            }
        }

        public void push(T o1) {
            heap.add(heapSize, o1);
            indexMap.put(o1, heapSize);
            heapInsert(heapSize++);
        }

        public T pop() {
            T t = heap.get(0);
            swap(0, --heapSize);
            heapify(0, heapSize);
            indexMap.remove(t);
            return t;
        }

        public void resign(T value) {
            Integer index = indexMap.get(value);
            heapInsert(index);
            heapify(index, heapSize);
        }
    }

    public static void main(String[] args) {
        Student stu1 = new Student(10, 111, "BangDi1");
        Student stu2 = new Student(20, 222, "BangDi2");
        Student stu3 = new Student(30, 333, "BangDi3");
        Student stu4 = new Student(40, 444, "BangDi4");

        MyHeap<Student> heap = new MyHeap<>(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o2.getAge() - o1.getAge();
            }
        });
        heap.push(stu1);
        heap.push(stu2);
        heap.push(stu3);
        heap.push(stu4);

        System.out.println("====修改前====");
        while (!heap.isEmpty()) {
            Student.printStudent(heap.pop());
        }


        heap.push(stu1);
        heap.push(stu2);
        heap.push(stu3);
        heap.push(stu4);

        System.out.println("====修改后====");

        stu1.setAge(100);
        stu2.setAge(200);
        heap.resign(stu1);
        heap.resign(stu2);
        while (!heap.isEmpty()) {
            Student.printStudent(heap.pop());
        }
    }
}