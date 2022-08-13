package basicDataStructure.heap;

import tool.Tools;

import java.util.Arrays;

public class Heap {

    public static class MyHeap {
        private int[] myHeap;
        private int heapSize;
        private final int limit;

        public MyHeap(int limit) {
            myHeap = new int[limit];
            this.limit = limit;
        }

        public boolean isFull() {
            return limit == heapSize;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public void add(int value) {
            if (isFull()) {
                throw new RuntimeException("Heap Full");
            }
            heapInsert(value);
//            Tools.printArray(myHeap);
        }

        public int pop() {
            if (isEmpty()) {
                throw new RuntimeException("Heap Empty");
            }
            int ans = myHeap[0];
            Tools.swap(myHeap, 0, --heapSize);
            heapify();
//            Tools.printArray(myHeap);
            return ans;
        }

        private void heapInsert(int value) {
            int index = heapSize;
            myHeap[heapSize++] = value;
            while (myHeap[index] > myHeap[(index - 1) / 2]) {
                Tools.swap(myHeap, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void heapify() {
            int index = 0;
            int leftIndex = 1 < heapSize ? 1 : 0;
            while (leftIndex < heapSize) {
                int largestIndex = leftIndex + 1 < heapSize && myHeap[leftIndex] < myHeap[leftIndex + 1] ? leftIndex + 1 : leftIndex;
                largestIndex = myHeap[index] < myHeap[largestIndex] ? largestIndex : index;
                if (index == largestIndex) {
                    break;
                }
                Tools.swap(myHeap, index, largestIndex);
                index = largestIndex;
                leftIndex = 2 * index + 1;
            }
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
//            Tools.printArray(arr);
            for (int k : arr) {
                if (!myHeap.isEmpty() && Math.random() < 0.5) {
                    int myPop = myHeap.pop();
                    int rightPop = rightHeap.pop();
//                    System.out.println("my:" + myPop + " r:" + rightPop);
                    if (myPop != rightPop) {
                        succeed = false;
                    }
                } else {
//                    System.out.println("add " + k);
                    myHeap.add(k);
                    rightHeap.add(k);
                }
            }
        }
        System.out.println(succeed ? "Nice" : "Fuck");
    }

    public static void main(String[] args) {
        test();
//        int[] arr = {38, 27, 27, -59, -52, -21, -70, -78};
//        MyHeap heap = new MyHeap(15);
//        for (int i = 0; i < arr.length; i++) {
//            heap.add(arr[i]);
//        }

//        System.out.println("pop " + heap.pop());
//        while (!heap.isEmpty()) {
//            System.out.println("pop " + heap.pop());
//        }
    }

}
