package basicDataStructure.linkedList;

import java.util.Queue;
import java.util.Stack;

public class TwoStackImplementQueue {

    public static class MyQueue {
        private Stack<Integer> addStack;
        private Stack<Integer> popStack;

        public MyQueue() {
            addStack = new Stack<>();
            popStack = new Stack<>();
        }

        public void add(int value) {
            addStack.add(value);
        }

        public void pourIntoPop() {
            while (!addStack.isEmpty()) {
                popStack.add(addStack.pop());
            }
        }

        public int poll() {
            if (popStack.isEmpty()) {
                pourIntoPop();
            }
            if (popStack.empty()) {
                throw new RuntimeException("queue is Empty");
            }
            return popStack.pop();
        }
    }

    public static void test() {
        MyQueue myQueue = new MyQueue();
        myQueue.add(10);
        myQueue.add(20);
        myQueue.add(30);
        myQueue.add(40);
        System.out.println(myQueue.poll());
        System.out.println(myQueue.poll());
        System.out.println(myQueue.poll());
        System.out.println(myQueue.poll());
    }

    public static void main(String[] args) {
        test();
    }

}
