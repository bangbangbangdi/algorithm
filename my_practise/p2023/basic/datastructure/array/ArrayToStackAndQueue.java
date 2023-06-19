package my_practise.p2023.basic.datastructure.array;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * projectName:    algorithm1
 * package:        my_practise.p2023.basic.datastructure.array
 * className:      ArrayToStackAndQueue
 * author:     BangDi
 * description:  数组实现栈和队列
 * date:    2023/6/19 11:05
 * version:    1.0
 */
public class ArrayToStackAndQueue {

    public static class MyStack {
        private int[] stack;
        private int index;

        public MyStack(int size) {
            stack = new int[size];
            index = 0;
        }

        public boolean isFull() {
            return index == stack.length;
        }

        public boolean isEmpty() {
            return index == 0;
        }

        public void push(int value) {
            if (isFull()) {
                throw new RuntimeException("stack is full");
            }
            stack[index++] = value;
        }

        public int pop() {
            if (isEmpty()) {
                throw new RuntimeException("stack is empty");
            }
            return stack[--index];
        }
    }

    public static class MyQueue {
        private int[] queue;
        private int offerIndex;
        private int pollIndex;
        private int limit;
        private int size;

        public MyQueue(int capacity) {
            queue = new int[capacity];
            offerIndex = 0;
            pollIndex = 0;
            limit = capacity;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == limit;
        }

        public int getNextIndex(int index) {
            return index == limit - 1 ? 0 : index + 1;
        }

        public void offer(int value) {
            if (isFull()){
                throw new RuntimeException("queue is full");
            }
            int nextIndex = getNextIndex(offerIndex++);
            size++;
            queue[nextIndex] = value;
        }

        public int poll(){
            if (isEmpty()){
                throw new RuntimeException("queue is empty");
            }
            int nextIndex = getNextIndex(pollIndex++);
            size--;
            return queue[nextIndex];
        }

    }

    public static boolean isEqual(Integer o1, Integer o2) {
        if (o1 == null && o2 != null) {
            return false;
        }
        if (o1 != null && o2 == null) {
            return false;
        }
        if (o1 == null && o2 == null) {
            return true;
        }
        return o1.equals(o2);
    }

    public static void test() {
        int oneTestDataNum = 100;
        int value = 10000;
        int testTimes = 100000;
        boolean succeed = true;
        for (int i = 0; i < testTimes && succeed; i++) {
            MyStack myStack = new MyStack(100);
            MyQueue myQueue = new MyQueue(100);
            Stack<Integer> stack = new Stack<>();
            Queue<Integer> queue = new LinkedList<>();
            for (int j = 0; j < oneTestDataNum && succeed; j++) {
                int nums = (int) (Math.random() * value);
                if (stack.isEmpty()) {
//                    System.out.println("stack add : " + nums);
                    myStack.push(nums);
                    stack.push(nums);
                } else {
                    if (Math.random() < 0.5) {
//                        System.out.println("stack add : " + nums);
                        myStack.push(nums);
                        stack.push(nums);
                    } else {
                        Integer my = myStack.pop();
                        Integer s = stack.pop();
                        if (!isEqual(my, s)) {
                            succeed = false;
//                            System.out.println("oops!");
//                            System.out.println("my : " + my);
//                            System.out.println("s : " + s);
                        }
                    }
                }
                int numq = (int) (Math.random() * value);
                if (queue.isEmpty()) {
//                    System.out.println("queueAdd : " + numq);
                    myQueue.offer(numq);
                    queue.offer(numq);
                } else {
                    if (Math.random() < 0.5) {
//                        System.out.println("queueAdd : " + numq);
                        myQueue.offer(numq);
                        queue.offer(numq);
                    } else {
                        Integer my = myQueue.poll();
                        Integer q = queue.poll();
//                        System.out.println("my : " + my);
//                        System.out.println("q : " + q);
                        if (!isEqual(my, q)) {
                            succeed = false;
                            System.out.println("oops!");
                            System.out.println("my : " + my);
                            System.out.println("q : " + q);
                        }
                    }
                }
            }
        }
        System.out.println(succeed ? "Nice" : "F");
    }

    public static void main(String[] args) {
        test();
    }


}
