package basicDataStructure.heap;

import tool.Tools;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Heap {

    public static class MyHeap {
        private int[] myHeap;
        private int limit;
        private int heapSize;

        public MyHeap(int limit) {
            myHeap = new int[limit];
            this.limit = limit;
        }

        public boolean isFull() {
            return heapSize == limit;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        private void heapInsert(int[] arr, int index) {
            while (arr[index] > arr[(index - 1) / 2]) {
                Tools.swap(arr, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void heapify(int[] arr, int index, int heapSize) {
            int leftIndex = index * 2 + 1;
            while (leftIndex < heapSize) {
                int largestIndex = leftIndex + 1 < heapSize && arr[leftIndex] < arr[leftIndex + 1] ? leftIndex + 1 : leftIndex;
                largestIndex = arr[index] < arr[largestIndex] ? largestIndex : index;
                if (largestIndex == index) {
                    break;
                }
                Tools.swap(arr,index,largestIndex);
                index = largestIndex;
                leftIndex = index * 2 + 1;
            }
        }

        public void push(int value) {
            if (isFull()) {
                throw new RuntimeException("Heap Full");
            }
            myHeap[heapSize] = value;
            heapInsert(myHeap, heapSize++);
        }

        public int pop() {
            if (isEmpty()){
                throw new RuntimeException("Heap Empty");
            }
            int ans = myHeap[0];
            Tools.swap(myHeap,--heapSize,0);
            heapify(myHeap,0,heapSize);
            return ans;
        }
    }

    public static class RightHeap {
        private int[] myHeap;
        private int heapSize;
        private final int limit;

        public RightHeap(int limit) {
            myHeap = new int[limit];
            this.limit = limit;
        }

        public boolean isFull() {
            return heapSize == limit;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public void add(int value) {
            if (isFull()) {
                throw new RuntimeException("Heap Full");
            }
            myHeap[heapSize++] = value;
        }

        public int pop() {
            if (isEmpty()) {
                throw new RuntimeException("Heap Empty");
            }
            int largestIndex = 0;
            for (int i = 0; i < heapSize; i++) {
                largestIndex = myHeap[largestIndex] < myHeap[i] ? i : largestIndex;
            }
            int ans = myHeap[largestIndex];
            Tools.swap(myHeap, largestIndex, --heapSize);
            return ans;
        }
    }

/*
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
            RightHeap rightHeap = new RightHeap(maxSize);
            for (int k : arr) {
                if (!myHeap.isEmpty() && Math.random() < 0.5) {
                    int myPop = myHeap.pop();
                    int rightPop = rightHeap.pop();
                    if (myPop != rightPop) {
                        succeed = false;
                    }
                } else {
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

*/
public static void test() {
    int testTime = 1000000;
    int maxSize = 100;
    int maxValue = 10;
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
//                System.out.println("myPop = " + myPop + ";rightPop = " + rightPop);
                if (myPop != rightPop) {
                    System.out.println("myPop = " + myPop + ";rightPop = " + rightPop);
                    succeed = false;
                }
            } else {
//                System.out.println("push " + k);
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
