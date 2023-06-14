package my_practise.p2023.basic.datastructure.heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * projectName:    algorithm1
 * package:        my_practise.p2023.basic.datastructure.heap
 * className:      HeapPro
 * author:     BangDi
 * description:  增强Heap
 * 能够实现对于堆内的数据更新并保持堆有序
 * date:    2023/6/14 10:14
 * version:    1.0
 */
public class HeapPro {

    public static class MyHeap<T> {
        private ArrayList<T> heap;
        private HashMap<T, Integer> map;
        private Comparator<? super T> com;
        private int size;
        private int limit;

        public MyHeap(int limit, Comparator<? super T> com) {
            this.heap = new ArrayList<>(limit);
            this.map = new HashMap<>();
            this.com = com;
            this.size = 0;
            this.limit = limit;
        }

        public void push(T value) {
            if (isFull()) {
                throw new RuntimeException("Heap is Full");
            }
            heap.add(size, value);
            map.put(value, size);
            heapInsert(size++);
        }

        public T pop() {
            if (isEmpty()) {
                throw new RuntimeException("Heap is Empty");
            }
            T ans = heap.get(0);
            swap(0, --size);
            map.remove(ans);
            heapify(0);
            return ans;
        }

        public void resign(T value) {
            if (!map.containsKey(value)) {
                return;
            }
            Integer index = map.get(value);
            heap.set(index, value);
            heapInsert(index);
            heapify(index);
        }

        public void heapInsert(int index) {
            while (com.compare(heap.get(index), heap.get((index - 1) / 2)) > 0) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        public void heapify(int index) {
            int leftIndex = index * 2 + 1;
            while (leftIndex < size) {
                int largestIndex = leftIndex + 1 < size && com.compare(heap.get(leftIndex + 1), heap.get(leftIndex)) > 0 ? leftIndex + 1 : leftIndex;
                largestIndex = com.compare(heap.get(index), heap.get(largestIndex)) < 0 ? largestIndex : index;
                if (largestIndex == index) {
                    return;
                }
                swap(index, largestIndex);
                index = largestIndex;
                leftIndex = index * 2 + 1;
            }
        }

        public boolean isEmpty() {
            return size <= 0;
        }

        public boolean isFull() {
            return size == limit;
        }

        public void swap(int i, int j) {
            if (i == j) {
                return;
            }
            T tmp = heap.get(i);
            heap.set(i, heap.get(j));
            map.put(heap.get(i), i);
            heap.set(j, tmp);
            map.put(heap.get(j), j);
        }
    }
    public static class Student {
        private int age;

        public Student(int age) {
            this.age = age;
        }
    }

    public static class StudentComparator implements Comparator<Student> {
        @Override
        public int compare(Student o1, Student o2) {
            return o1.age - o2.age;
        }
    }

    public static void test() {
        StudentComparator com = new StudentComparator();
        MyHeap<Student> heap = new MyHeap<Student>(100,com);

        Student stu4 = new Student(40);
        Student stu1 = new Student(10);
        Student stu2 = new Student(20);
        Student stu3 = new Student(30);

        heap.push(stu1);
        heap.push(stu2);
        heap.push(stu3);
        heap.push(stu4);

        stu2.age = 100;
        heap.resign(stu2);

        System.out.println(heap.pop().age);
        System.out.println(heap.pop().age);
        System.out.println(heap.pop().age);
        System.out.println(heap.pop().age);
    }

    public static void main(String[] args) {
        test();
    }
}
