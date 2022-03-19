package myClass04;

import tool.Tools;

public class Code02_Heap01 {
    public static class MyMaxHeap {
        private int[] heap;
        private final int limit;
        private int heapSize;

        public MyMaxHeap(int limit) {
            this.limit = limit;
            heap = new int[limit];
            heapSize = 0;
        }

        private void heapInsert(int[] arr, int index) {
            while (arr[index] > arr[(index - 1) / 2]) {
                Tools.swap(arr, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void heapify(int[] arr, int index, int heapSize) {
            int left = index * 2 + 1;
            while (left < heapSize) {
                int largest = (left + 1 < heapSize) && (arr[left] < arr[left + 1]) ? left + 1 : left;
                largest = arr[index] < arr[largest] ? largest : index;
                if (index == largest) {
                    break;
                }
                Tools.swap(arr, index, largest);
                index = largest;
                left = largest * 2 + 1;
            }
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public boolean isFull() {
            return heapSize == limit;
        }

        public void push(int value) {
            if (heapSize >= limit) {
                throw new RuntimeException("heap is full");
            }
            heap[heapSize] = value;
            heapInsert(heap, heapSize++);
        }

        public int pop() {
            if (heapSize <= 0) {
                throw new RuntimeException("heap is empty");
            }
            int result = heap[0];
//            System.out.println("before heapify");
//            printHeap();
            Tools.swap(heap, 0, --heapSize);
            heapify(heap, 0, heapSize);
//            System.out.println("after heapify");
//            printHeap();
//            System.out.println("--------------------");
            return result;
        }

        public void printHeap() {
            for (int i = 0; i < heapSize; i++) {
                System.out.print(heap[i] + " ");
            }
            System.out.println();
        }
    }

    public static class RightMaxHeap {
        private final int limit;
        private int[] heap;
        private int heapSize;

        public RightMaxHeap(int limit) {
            this.limit = limit;
            heap = new int[limit];
            heapSize = 0;
        }

        public boolean isEmpty() {
            return heapSize <= 0;
        }

        public boolean isFull() {
            return heapSize >= limit;
        }

        public void push(int value) {
            if (heapSize >= limit) {
                throw new RuntimeException("heap is full");
            }
            heap[heapSize++] = value;
        }

        public int pop() {
            if (heapSize <= 0) {
                throw new RuntimeException("heap is empty");
            }
            int maxIndex = 0;
            for (int i = 0; i < heapSize; i++) {
                maxIndex = heap[maxIndex] < heap[i] ? i : maxIndex;
            }
            int result = heap[maxIndex];
            Tools.swap(heap, maxIndex, --heapSize);
            return result;
        }
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxValue = 100;
        int maxSize = 100;
        boolean succeed = true;
        MyMaxHeap myMaxHeap = new MyMaxHeap(maxSize);
        RightMaxHeap rightMaxHeap = new RightMaxHeap(maxSize);
        for (int i = 0; i < testTime; i++) {
            for (int j = 0; j < 100; j++) {
                int randomInt = Tools.getRandomInt(maxValue);
                if (!myMaxHeap.isEmpty()) {
                    if (Math.random() < 0.5) {
                        myMaxHeap.push(randomInt);
                        rightMaxHeap.push(randomInt);
                    } else {
                        int myPop = myMaxHeap.pop();
                        int rightPop = rightMaxHeap.pop();
                        if (myPop != rightPop) {
                            System.out.println("myPop: " + myPop + " rightPop: " + rightPop);
                            succeed = false;
                            break;
                        }
                    }
                } else {
                    myMaxHeap.push(randomInt);
                    rightMaxHeap.push(randomInt);
                }
            }
            while (!myMaxHeap.isEmpty()) {
                int myPop = myMaxHeap.pop();
                int rightPop = rightMaxHeap.pop();
                if (myPop != rightPop) {
                    System.out.println("myPop: " + myPop + " rightPop: " + rightPop);
                    succeed = false;
                    break;
                }
            }
        }
        System.out.println(succeed ? "Nice!!" : "Fuck !!");
    }
}