package basicDataStructure.linkedList;

import tool.Node2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class LinkedListToStackAndQueue {

    public static class NodeLink<T> {
        private Node2<T> head;
        private Node2<T> tail;

        public void addFromHead(T value) {
            Node2<T> cur = new Node2<>(value);
            if (head == null || tail == null) {
                this.head = cur;
                this.tail = cur;
                return;
            }
            head.pre = cur;
            cur.next = head;
            head = cur;
        }

        public void addFromBottom(T value) {
            Node2<T> cur = new Node2<>(value);
            if (head == null) {
                this.head = cur;
                this.tail = this.head;
                return;
            }
            tail.next = cur;
            cur.pre = tail;
            tail = cur;
        }

        public T popHead() {
            if (head == null){
                return null;
            }
            T res = head.value;
            Node2<T> next = head.next;
            if (next != null) {
                next.pre = null;
            }
            head = next;
            return res;
        }

        public T popTail() {
            if (tail == null){
                return null;
            }
            T res = tail.value;
            Node2<T> pre = tail.pre;
            if (pre != null) {
                pre.next = null;
            }
            tail = pre;
            return res;
        }
    }

    public static class MyStack<T> {
        private NodeLink<T> stack;

        public MyStack() {
            this.stack = new NodeLink<T>();
        }

        public void add(T value) {
            stack.addFromHead(value);
        }

        public T poll() {
            return stack.popHead();
        }
    }

    public static class MyQueue<T> {
        private NodeLink<T> queue;

        public MyQueue() {
            queue = new NodeLink<T>();
        }

        public void add(T value) {
            queue.addFromHead(value);
        }

        public T poll() {
            return queue.popTail();
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

    public static void test(){
        int oneTestDataNum = 100;
        int value = 10000;
        int testTimes = 100000;
        boolean succeed = true;
        for (int i = 0; i < testTimes && succeed; i++) {
            MyStack<Integer> myStack = new MyStack<>();
            MyQueue<Integer> myQueue = new MyQueue<>();
            Stack<Integer> stack = new Stack<>();
            Queue<Integer> queue = new LinkedList<>();
            for (int j = 0; j < oneTestDataNum && succeed; j++) {
                int nums = (int) (Math.random() * value);
                if (stack.isEmpty()) {
//                    System.out.println("stack add : " + nums);
                    myStack.add(nums);
                    stack.push(nums);
                } else {
                    if (Math.random() < 0.5) {
//                        System.out.println("stack add : " + nums);
                        myStack.add(nums);
                        stack.push(nums);
                    } else {
                        Integer my = myStack.poll();
                        Integer s = stack.pop();
                        if (!isEqual(my, s)) {
                            succeed = false;
                            System.out.println("oops!");
                            System.out.println("my : " + my);
                            System.out.println("s : " + s);
                        }
                    }
                }
                int numq = (int) (Math.random() * value);
                if (stack.isEmpty()) {
//                    System.out.println("queueAdd : " + numq);
                    myQueue.add(numq);
                    queue.offer(numq);
                } else {
                    if (Math.random() < 0.5) {
//                        System.out.println("queueAdd : " + numq);
                        myQueue.add(numq);
                        queue.offer(numq);
                    } else {
                        Integer my = myQueue.poll();
                        Integer q = queue.poll();
//                        System.out.println("my : " + my);
//                        System.out.println("q : " + q);
                        if (!isEqual(my, q)) {
                            succeed = false;
                            System.out.println("oops!");
                            System.out.println("my : " + my);
                            System.out.println("p : " + q);
                        }
                    }
                }
            }
        }
        System.out.println("finish!");
    }

    public static void main(String[] args) {
//        MyQueue<Integer> queue = new MyQueue<>();
//        queue.add(10);
//        System.out.println(queue.poll());
//        System.out.println(queue.poll());
//        queue.add(20);
//        System.out.println(queue.poll());
        test();
    }

}
