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
            heap = new ArrayList<>();
            map = new HashMap<T,Integer>();
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

        public void push(T t) {
            heap.add(heapSize, t);
            map.put(t, heapSize);
            heapInsert(heap, heapSize++);
        }

        public T pop() {
            T ans = heap.get(0);
            swap(0, --heapSize);
            heapify(heap, 0, heapSize);
            return ans;
        }

        public void resign(T t) {
            Integer index = map.get(t);
            heapInsert(heap, index);
            heapify(heap, index, heapSize);
        }

        private void heapInsert(ArrayList<T> arr, int index) {
            while (com.compare(arr.get(index), arr.get((index - 1) / 2)) < 0) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void heapify(ArrayList<T> arr, int index, int heapSize) {
            int leftIndex = index * 2 + 1;
            while (leftIndex < heapSize) {
                int largestIndex = leftIndex + 1 < heapSize && com.compare(arr.get(leftIndex), arr.get(leftIndex + 1)) > 0 ? leftIndex + 1 : leftIndex;
                largestIndex = com.compare(arr.get(index), arr.get(largestIndex)) < 0 ? index : largestIndex;
                if (largestIndex == index) {
                    break;
                }
                swap(index, largestIndex);
                index = largestIndex;
                leftIndex = index * 2 + 1;
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

        public Student(int age) {
            this.age = age;
        }
    }

    public static class StudentComparator implements Comparator<Student> {
        @Override
        public int compare(Student o1, Student o2) {
            return o2.age - o1.age;
        }
    }

    public static void test() {
        StudentComparator com = new StudentComparator();
        MyHeap<Student> heap = new MyHeap<Student>(com);

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
