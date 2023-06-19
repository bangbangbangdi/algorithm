package my_practise.p2023.basic.datastructure.linkedList;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * projectName:    algorithm1
 * package:        my_practise.p2023.basic.datastructure.linkedList
 * className:      TwoStackImplementQueue
 * author:     BangDi
 * description:
 * date:    2023/6/19 14:41
 * version:    1.0
 */
public class TwoStackImplementQueue {

    public static class MyQueue<T> {

        private Stack<T> pushStack;
        private Stack<T> popStack;

        public MyQueue() {
            pushStack = new Stack<>();
            popStack = new Stack<>();
        }

        public boolean isEmpty() {
            return pushStack.isEmpty() && popStack.isEmpty();
        }

        public void offer(T value) {
            pushStack.push(value);
        }

        public T poll() {
            if (isEmpty()) {
                throw new RuntimeException("queue is empty");
            }
            if (popStack.isEmpty()) {
                while (!pushStack.isEmpty()) {
                    popStack.push(pushStack.pop());
                }
            }
            return popStack.pop();
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
            MyQueue<Integer> myQueue = new MyQueue<>();
            Queue<Integer> queue = new LinkedList<>();
            for (int j = 0; j < oneTestDataNum && succeed; j++) {
                int nums = (int) (Math.random() * value);
                if (queue.isEmpty()) {
                    myQueue.offer(nums);
                    queue.offer(nums);
                } else {
                    if (Math.random() < 0.5) {
                        myQueue.offer(nums);
                        queue.offer(nums);
                    } else {
                        Integer my = myQueue.poll();
                        Integer s = queue.poll();
                        if (!isEqual(my, s)) {
                            succeed = false;
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
