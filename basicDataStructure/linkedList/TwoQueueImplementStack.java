package basicDataStructure.linkedList;


import java.util.LinkedList;
import java.util.Queue;

public class TwoQueueImplementStack {

    public static class MyStack<T> {
        private Queue<T> queue;
        private Queue<T> help;

        public MyStack() {
            queue = new LinkedList<>();
            help = new LinkedList<>();
        }

        public void add(T value) {
            queue.add(value);
        }

        public T pop() {
            if (queue.isEmpty()) {
                return null;
            }
            while (queue.size() != 1) {
                help.offer(queue.poll());
            }
            T res = queue.poll();
            Queue<T> tem = queue;
            queue = help;
            help = tem;
            return res;
        }
    }

    public static void test() {
        MyStack<Integer> stack = new MyStack<>();
        stack.add(10);
        stack.add(20);
        stack.add(30);
        stack.add(40);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }

    public static void main(String[] args) {
        test();
    }

}
