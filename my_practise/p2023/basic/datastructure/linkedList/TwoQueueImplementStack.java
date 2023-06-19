package my_practise.p2023.basic.datastructure.linkedList;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * projectName:    algorithm1
 * package:        my_practise.p2023.basic.datastructure.linkedList
 * className:      TwoQueueImplementStack
 * author:     BangDi
 * description:
 * date:    2023/6/19 14:12
 * version:    1.0
 */
public class TwoQueueImplementStack {

    public static class MyStack<T>{
        private Queue<T> queue;
        private Queue<T> helpQueue;

        public MyStack(){
            queue = new LinkedList<>();
            helpQueue = new LinkedList<>();
        }

        public boolean isEmpty(){
            return queue.isEmpty();
        }

        public void push(T value){
            queue.offer(value);
        }

        public T pop(){
            if (isEmpty()){
                throw new RuntimeException("stack is empty");
            }
            while(queue.size() > 1){
                helpQueue.offer(queue.poll());
            }
            T ans = queue.poll();
            Queue<T> tmp = helpQueue;
            helpQueue = queue;
            queue = tmp;
            return ans;
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
            MyStack<Integer> myStack = new MyStack<>();
            Stack<Integer> stack = new Stack<>();
            for (int j = 0; j < oneTestDataNum && succeed; j++) {
                int nums = (int) (Math.random() * value);
                if (stack.isEmpty()) {
                    myStack.push(nums);
                    stack.push(nums);
                } else {
                    if (Math.random() < 0.5) {
                        myStack.push(nums);
                        stack.push(nums);
                    } else {
                        Integer my = myStack.pop();
                        Integer s = stack.pop();
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
