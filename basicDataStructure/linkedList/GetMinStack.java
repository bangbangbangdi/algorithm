package basicDataStructure.linkedList;

import java.util.Stack;

public class GetMinStack {

    public static class MyStack {
        private Stack<Integer> stack;
        private Stack<Integer> minStack;

        public MyStack() {
            stack = new Stack<Integer>();
            minStack = new Stack<Integer>();
        }

        public void add(int value) {
            stack.add(value);
            if (minStack.isEmpty() || minStack.peek() >= value) {
                minStack.add(value);
            }
        }

        public int pop() {
            int res = stack.pop();
            if (res == minStack.peek()) {
                minStack.pop();
            }
            return res;
        }

        public int getMin(){
            return minStack.peek();
        }

    }

    public static void test(){
        MyStack myStack = new MyStack();
        myStack.add(10);
        myStack.add(20);
        myStack.add(30);
        myStack.add(5);
        System.out.println(myStack.getMin());
        System.out.println(myStack.pop());
        System.out.println(myStack.getMin());
    }

    public static void main(String[] args) {
        test();
    }

}
