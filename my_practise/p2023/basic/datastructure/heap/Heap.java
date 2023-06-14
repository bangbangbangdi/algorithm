package my_practise.p2023.basic.datastructure.heap;

import tool.Tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.basic.datastructure.heap
 * className:      Heap
 * author:     BangDi
 * description:  手撸一个堆
 * date:    2023/6/13 21:21
 * version:    1.0
 */
public class Heap {

    public static class MyHeap {
        private int[] heap;
        private int limit;
        private int size;

        public MyHeap(int size) {
            this.heap = new int[size];
            this.limit = size;
            this.size = 0;
        }

        public boolean isFull() {
            return limit == size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void push(int value) {
            if (isFull()) {
                throw new RuntimeException("Heap is Full");
            }
            heap[size] = value;
            heapInsert(size);
            size++;
        }

        public int pop() {
            if (isEmpty()) {
                throw new RuntimeException("Heap is Empty");
            }
            int ans = heap[0];
            Tools.swap(heap, 0, --size);
            heapify(0);
            return ans;
        }

        public void heapInsert(int index) {
            while (heap[index] > heap[(index - 1) / 2]) {
                Tools.swap(heap, index, (index-1) / 2);
                index = (index - 1) / 2;
            }
        }

        public void heapify(int index) {
            int leftIndex = index * 2 + 1;
            while (leftIndex < size) {
                int largestIndex = leftIndex + 1 < size && heap[leftIndex + 1] > heap[leftIndex] ? leftIndex + 1 : leftIndex;
                largestIndex = heap[largestIndex] > heap[index] ? largestIndex : index;
                if (largestIndex == index) {
                    return;
                }
                Tools.swap(heap, index, largestIndex);
                index = largestIndex;
                leftIndex = index * 2 + 1;
            }
        }
    }

    public static void test() {
        int testTime = 1000000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime && succeed; i++) {
            int[] arr = Tools.generateRandomArray(maxSize, maxValue);
            int[] copyArr = Tools.copyArr(arr);
            Arrays.sort(copyArr);
            MyHeap myHeap = new MyHeap(maxSize);
            PriorityQueue<Integer> rightHeap = new PriorityQueue<>((o1, o2) -> (o2 - o1));
            for (int k : arr) {
                if (!myHeap.isEmpty() && Math.random() < 0.5) {
                    int myPop = myHeap.pop();
                    int rightPop = rightHeap.poll();
//                    System.out.println("myPop = " + myPop + ";rightPop = " + rightPop);
                    if (myPop != rightPop) {
                        System.out.println("myPop = " + myPop + ";rightPop = " + rightPop);
                        succeed = false;
                    }
                } else {
//                    System.out.println("push " + k);
                    myHeap.push(k);
                    rightHeap.add(k);
                }
            }
        }
        System.out.println(succeed ? "Nice" : "Fuck");
    }

    public static void main(String[] args) {
        test();
    }

}
