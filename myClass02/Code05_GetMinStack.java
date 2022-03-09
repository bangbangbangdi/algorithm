package myClass02;


import java.util.Stack;

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
            }else{
                stackMin.push(min);
            }
        }

        public int pop(){
            stackMin.pop();
            return stackData.pop();
        }

        public int getMin(){
            stackData.pop();
            return stackMin.pop();
        }
    }

    public static void main(String[] args) {

    }
}