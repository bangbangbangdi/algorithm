package my_practise.p2022.basic.myClass02;


import java.util.LinkedList;
import java.util.Stack;

public class Code06_TwoStacksImplementQueue {
    public static class TwoStackQueue {
        private Stack<Integer> pushStack;
        private Stack<Integer> popStack;

        public TwoStackQueue() {
            pushStack = new Stack<Integer>();
            popStack = new Stack<Integer>();
        }

        private void pushToPop() {
            if (!popStack.isEmpty()) {
                return;
            }
            while (!pushStack.isEmpty()) {
                popStack.push(pushStack.pop());
            }
        }

        public void push(int i) {
            pushStack.push(i);
        }

        public int pop() {
            pushToPop();
            if (popStack.isEmpty()) {
                throw new RuntimeException("栈空了");
            }
            return popStack.pop();
        }
    }

    public static int getRandomInt(int max) {
        int value = (int) (Math.random() * max - Math.random() * max);
        return value;
    }

    public static void main(String[] args) {
        TwoStackQueue myQueue = new TwoStackQueue();
        LinkedList<Integer> queue = new LinkedList<>();

        for (int i = 0; i < 500000; i++) {
            int size = (int) (Math.random() * 20 + 1);
            for (int j = 0; j < size; j++) {
                int randomInt = getRandomInt(100);
                myQueue.push(randomInt);
                queue.offer(randomInt);
            }
            for (int j = 0; j < size; j++) {
                int myPop = myQueue.pop();
                int pop = queue.pop();
                if (myPop != pop){
                    System.out.println("Fuck!!");
                    return;
                }
            }
        }
        System.out.println("succeed");
    }
}