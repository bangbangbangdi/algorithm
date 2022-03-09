package myClass02;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Code07_TwoQueueImplementStack {
    public static class MyStack {
        private Queue<Integer> queue;
        private Queue<Integer> help;

        public MyStack() {
            queue = new LinkedList<Integer>();
            help = new LinkedList<Integer>();
        }

        public int pop() {
            if (queue.isEmpty()) {
                throw new RuntimeException("栈空了");
            }
            int size = queue.size();
//            System.out.println("size: "+size);
            for (int i = 0; i < size - 1; i++) {
                int poll = queue.poll();
                help.offer(poll);
//                System.out.print("queToHelp: "+poll+"--");
//                System.out.println();
            }
            int result = queue.poll();
            for (int i = 0; i < size - 1; i++) {
                Integer poll = help.poll();
                queue.offer(poll);
//                System.out.print("helpToQue: "+poll+"--");
//                System.out.println();
            }
            return result;
        }

        public void push(int i) {
            queue.offer(i);
        }

        public void printAll() {

        }
    }

    public static int getRandomInt(int max) {
        int value = (int) (Math.random() * max - Math.random() * max);
        return value;
    }

    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 500000; i++) {
            int size = (int) (Math.random() * 20 + 1);
            for (int j = 0; j < size; j++) {
                int randomInt = getRandomInt(100);
                myStack.push(randomInt);
                stack.push(randomInt);
            }
            for (int j = 0; j < size; j++) {
                int myPop = myStack.pop();
                int pop = stack.pop();
                if (myPop != pop){
                    System.out.println("Fuck!!");
                    return;
                }
            }
        }
        System.out.println("succeed!!");
    }
}