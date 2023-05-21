package my_practise.p2022.basic.myClass11;

import java.util.*;

public class Code04_ReverseStackUsingRecursive {

    public static void reverse(Stack<Integer> stack){
        if (stack.isEmpty()){
            return;
        }
        int i = func(stack);
        reverse(stack);
        stack.add(i);
    }

    public static int func(Stack<Integer> stack){
        int result = stack.pop();
        if (stack.isEmpty()){
            return result;
        }else{
            int last = func(stack);
            stack.add(result);
            return last;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.add(1);
        stack.add(2);
        stack.add(3);
        stack.add(4);
        stack.add(5);
        reverse(stack);
        while(!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }

}
