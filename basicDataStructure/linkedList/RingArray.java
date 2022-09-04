package basicDataStructure.linkedList;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class RingArray {

    public static class MyStack {
        private int[] stack;
        private int curIndex;

        public MyStack(int size) {
            stack = new int[size];
            curIndex = 0;
        }

        public void add(int value) {
            if (curIndex == stack.length) {
                throw new RuntimeException("满了,放不下了");
            }
            stack[curIndex++] = value;
        }

        public int pop() {
            if (curIndex == 0) {
                throw new RuntimeException("空了空了");
            }
            return stack[--curIndex];
        }
    }

    public static class MyQueue {
        private int[] queue;
        private int addIndex;
        private int pollIndex;
        private int curSize;

        public MyQueue(int size) {
            queue = new int[size];
            addIndex = 0;
            pollIndex = 0;
            curSize = 0;
        }

        public void add(int value) {
            if (curSize == queue.length) {
                throw new RuntimeException("Full");
            }
            curSize++;
            queue[getNextIndex(addIndex++)] = value;
        }

        public int poll() {
            if (curSize == 0) {
                throw new RuntimeException("Empty");
            }
            curSize--;
            return queue[getNextIndex(pollIndex++)];
        }

        private int getNextIndex(int curIndex) {
            return curIndex == queue.length - 1 ? 0 : curIndex + 1;
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
                    myStack.add(nums);
                    stack.push(nums);
                } else {
                    if (Math.random() < 0.5) {
//                        System.out.println("stack add : " + nums);
                        myStack.add(nums);
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
                    myQueue.add(numq);
                    queue.offer(numq);
                } else {
                    if (Math.random() < 0.5) {
//                        System.out.println("queueAdd : " + numq);
                        myQueue.add(numq);
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
