package my_practise.p2023.basic.datastructure.stack;

import java.util.Stack;

/**
 * projectName:    algorithm1
 * package:        my_practise.p2023.basic.datastructure.linkedList
 * className:      GetMinStack
 * author:     BangDi
 * description:
 * 整一个栈,可以返回当前栈中的最小值
 * date:    2023/6/16 17:23
 * version:    1.0
 */
public class GetMinStack {

    public static class MyStack{

        private Stack<Integer> stack;
        private Stack<Integer> min;

        public MyStack(){
            stack = new Stack<>();
            min = new Stack<>();
        }

        public void add(int value){
            stack.add(value);
            if (min.isEmpty() || min.peek() > value){
                min.add(value);
            }
        }

        public int pop(){
            int ans = stack.pop();
            if (ans == min.peek()){
                min.pop();
            }
            return ans;
        }

        public int getMin(){
            return min.peek();
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