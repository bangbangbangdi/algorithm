package myClass02;


import java.util.*;

public class Code05_GetMinStack {
    public static class MyStack1 {
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        public MyStack1() {
            this.stackData = new Stack<Integer>();
            this.stackMin = new Stack<Integer>();
        }

        public void push(int i) {
            if (stackData.isEmpty()) {
                stackData.push(i);
                stackMin.push(i);
                return;
            }
            stackData.push(i);
            int min = stackMin.peek();
            if (i < min) {
                stackMin.push(i);
            } else {
                stackMin.push(min);
            }
        }

        public int pop() {
            if (stackData.isEmpty()) {
                throw new RuntimeException("没东西了！！");
            }
            stackMin.pop();
            return stackData.pop();
        }

        public int getMin() {
            return stackMin.peek();
        }
    }

    public static class MyStack2 {
        private Stack<Integer> stackData;

        public MyStack2(){
            stackData = new Stack<Integer>();
        }

        public void push(int i) {
            stackData.push(i);
        }

        public int getMin() {
            ArrayList<Integer> arr = new ArrayList<>();
            while (!stackData.isEmpty()) {
                arr.add(stackData.pop());
            }
            int min = Collections.min(arr);
//            arr.remove(Integer.valueOf(min));
            for (int i = arr.size()-1; i >=0 ; i--) {
                stackData.push(arr.get(i));
            }
            return min;
        }

        public int pop(){
            return stackData.pop();
        }
    }

    public static int getRandomInt(int max) {
        int value = (int) (Math.random() * max - Math.random() * max);
        return value;
    }

    public static void main(String[] args) {
        MyStack1 myMinStack = new MyStack1();
        MyStack2 myMinStack2 = new MyStack2();

        for (int i = 0; i < 500000; i++) {
            int size = (int) Math.random()*50 + 1;
            for (int j = 0; j < size; j++) {
                int randomInt = getRandomInt(100);
                myMinStack.push(randomInt);
                myMinStack2.push(randomInt);
            }
            for (int j = 0; j < size; j++) {
                int min1 = myMinStack.getMin();
                int min2 = myMinStack2.getMin();
                System.out.println("min1: "+min1+"  min2: "+min2);
                if (min1 != min2){
                    System.out.println("Fuck!");
                    return;
                }
                myMinStack.pop();
                myMinStack2.pop();
            }
        }
        System.out.println("Succeed");
    }
}