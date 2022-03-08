package myClass02;


import java.util.LinkedList;

public class Code04_RingArray {

    public static class MyQueue {
        private int[] arr;
        private int pushi;
        private int polli;
        private int size;
        private final int limit;

        public MyQueue(int l) {
            arr = new int[l];
            pushi = 0;
            polli = 0;
            size = 0;
            limit = l;
        }

        public void push(int i) {
            if (size >= limit) {
                throw new RuntimeException("栈满了");
            }
            arr[pushi] = i;
            pushi = nextIndex(pushi);
            size++;
        }

        public int pop() {
            if (size <= 0) {
                throw new RuntimeException("栈空了");
            }
            int result = arr[polli];
            polli = nextIndex(polli);
            size--;
            return result;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isAvailable(){
            return size < limit;
        }

        public void printAll(){
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i]+" ");
            }
            System.out.println();
        }

        public int nextIndex(int index) {
            return index < limit - 1 ? index + 1 : 0;
        }
    }

    public static class MyStack {
        public int limit;
        public int index;
        public int[] arr;
        public int size;

        public MyStack(int l) {
            arr = new int[l];
            limit = l;
            index = 0;
            size = 0;
        }

        public void push(int i) {
            if (size >= limit) {
                throw new RuntimeException("栈满了");
            }
        }
    }

    public static int getRandomInt(int max) {
        int value = (int) (Math.random() * max - Math.random() * max);
        return value;
    }

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue(20);
        LinkedList<Integer> queue = new LinkedList<>();

        for (int i = 0; i < 500000; i++) {
            int size = (int) (Math.random() * 20);
            System.out.println("size: " + size);
            for (int j = 0; j < size; j++) {
                if (((myQueue.isEmpty() & queue.isEmpty()) || Math.random() < 0.5) && myQueue.isAvailable()) {
//                    System.out.println("push");
                    int randomInt = getRandomInt(100);
//                    System.out.println("randomInt: " + randomInt);
                    myQueue.push(randomInt);
                    queue.offer(randomInt);
//                    myQueue.printAll();
//                    for (int k = 0; k < queue.size(); k++) {
//                        System.out.print(queue.get(k)+" ");
//                    }
//                    System.out.println();
//                    System.out.println("----------");
                } else {
//                    System.out.println("pop");
                    int myPop = myQueue.pop();
                    int pop = queue.poll();
//                    System.out.println("myPop:" + myPop + " pop:" + pop);
//                    System.out.println("--------");
                    if (myPop != pop) {
                        System.out.println("Fuck!");
                        break;
                    }
                }
            }
        }
        System.out.println("Succeed!");
    }
}