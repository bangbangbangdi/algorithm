package my_practise.p2023.basic.datastructure.linkedList;

import reference.basic.class02.Code03_DoubleEndsQueueToStackAndQueue;
import tool.Node;
import tool.Node2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.basic.datastructure.linkedList
 * className:      LinkedListToStackAndQueue
 * author:     BangDi
 * description:  TODO
 * 用一个双端链表拼成一个栈或队列
 * date:    2023/6/17 01:02
 * version:    1.0
 */
public class DoubleEndsQueueToStackAndQueue {

    public static class NodeLinked<T> {
        private Node2<T> head;
        private Node2<T> tail;

        public void addFromHead(T value) {
            Node2<T> node = new Node2<>(value);
            if (head == null) {
                tail = node;
            } else {
                head.pre = node;
                node.next = head;
            }
            head = node;
        }

        public void addFromTail(T value) {
            Node2<T> node = new Node2<>(value);
            if (head == null) {
                head = node;
            } else {
                tail.next = node;
                node.pre = tail;
            }
            tail = node;
        }

        public T popFromHead() {
            if (head == null) {
                return null;
            }
            Node2<T> ans = head;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = ans.next;
                ans.next = null;
                head.pre = null;
            }
            return ans.value;
        }

        public T popFromTail() {
            if (head == null) {
                return null;
            }
            Node2<T> ans = tail;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                tail = ans.pre;
                ans.pre = null;
                tail.next = null;
            }
            return ans.value;
        }

        public boolean isEmpty() {
            return head == null;
        }
    }

    public static class MyStack<T>{
        private NodeLinked<T> stack;

        public MyStack(){
            stack = new NodeLinked<>();
        }

        public boolean isEmpty(){
            return stack.isEmpty();
        }

        public void push(T value){
            stack.addFromTail(value);
        }

        public T pop(){
//            if (isEmpty()){
//                throw new RuntimeException("Stack is empty");
//            }
            return stack.popFromTail();
        }
    }

    public static class MyQueue<T>{
        private NodeLinked<T> queue;

        public MyQueue(){
            queue = new NodeLinked<>();
        }

        public boolean isEmpty(){
            return queue.isEmpty();
        }

        public void push(T value){
            queue.addFromHead(value);
        }

        public T poll(){
//            if (isEmpty()){
//                throw new RuntimeException("Queue is empty");
//            }
            return queue.popFromTail();
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

    public static void main(String[] args) {
        int oneTestDataNum = 100;
        int value = 10000;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            MyStack<Integer> myStack = new MyStack<>();
            MyQueue<Integer> myQueue = new MyQueue<>();
            Stack<Integer> stack = new Stack<>();
            Queue<Integer> queue = new LinkedList<>();
            for (int j = 0; j < oneTestDataNum; j++) {
                int nums = (int) (Math.random() * value);
                if (stack.isEmpty()) {
                    myStack.push(nums);
                    stack.push(nums);
                } else {
                    if (Math.random() < 0.5) {
                        myStack.push(nums);
                        stack.push(nums);
                    } else {
                        if (!isEqual(myStack.pop(), stack.pop())) {
                            System.out.println("oops!");
                        }
                    }
                }
                int numq = (int) (Math.random() * value);
                if (stack.isEmpty()) {
                    myQueue.push(numq);
                    queue.offer(numq);
                } else {
                    if (Math.random() < 0.5) {
                        myQueue.push(numq);
                        queue.offer(numq);
                    } else {
                        if (!isEqual(myQueue.poll(), queue.poll())) {
                            System.out.println("oops!");
                        }
                    }
                }
            }
        }
        System.out.println("finish!");
    }

}
