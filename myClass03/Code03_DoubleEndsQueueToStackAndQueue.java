package myClass03;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Code03_DoubleEndsQueueToStackAndQueue {
    public static class Node<T> {
        public T value;
        public Node<T> last;
        public Node<T> next;

        public Node(T data) {
            value = data;
        }
    }

    public static class DoubleEndsQueue<T> {
        public Node<T> head;
        public Node<T> tail;

        public void addFromHead(T value) {
            Node<T> cur = new Node<T>(value);
            if (head == null) {
                head = cur;
                tail = cur;
            } else {
                cur.next = head;
                head.last = cur;
                head = cur;
            }
        }

        public T popFromHead() {
            if (head == null) {
                return null;
            }
            Node<T> cur = head;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                cur.next = null;
                head.last = null;
            }
            return cur.value;
        }

        public void addFromBottom(T value) {
            Node<T> cur = new Node<T>(value);
            if (tail == null) {
                head = cur;
                tail = cur;
            } else {
                tail.next = cur;
                cur.last = tail;
                tail = cur;
            }
        }

        public T popFromBottom() {
            if (head == null) {
                return null;
            }
            Node<T> cur = tail;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                tail = tail.last;
                tail.next = null;
                cur.last = null;
            }
            return cur.value;
        }

        public boolean isEmpty() {
            return head == null;
        }
    }

    public static class MyStack<T> {
        private DoubleEndsQueue<T> queue;

        public MyStack() {
            queue = new DoubleEndsQueue<>();
        }

        public void push(T value) {
            queue.addFromHead(value);
        }

        public T pop() {
            return queue.popFromHead();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }

    public static class MyQueue<T> {
        private DoubleEndsQueue<T> queue;

        public MyQueue() {
            queue = new DoubleEndsQueue<T>();
        }

        public void push(T value) {
            queue.addFromHead(value);
        }

        public T poll() {
            return queue.popFromBottom();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }

    public static boolean isEqual(Integer o1, Integer o2) {
        if (o1 == null && o2 == null) {
            return true;
        }
        if (o1 != null && o2 == null) {
            return false;
        }
        if (o1 == null && o2 != null) {
            return false;
        }
        return o1.equals(o2);
    }

    public static void Test() {
        int testTime = 500000;
        int maxValue = 1000;
        int oneTestDataNum = 100;

        for (int i = 0; i < testTime; i++) {
            MyQueue<Integer> myQueue = new MyQueue<>();
            MyStack<Integer> myStack = new MyStack<>();
            Stack<Integer> stack = new Stack<>();
            Queue<Integer> queue = new LinkedList<>();
            for (int j = 0; j < oneTestDataNum; j++) {
                int num = (int) (maxValue * Math.random());
                if (queue.isEmpty()) {
                    myQueue.push(num);
                    queue.offer(num);
                } else {
                    if (Math.random() < 0.5) {
                        myQueue.push(num);
                        queue.offer(num);
                    } else {
                        if (!isEqual(myQueue.poll(),queue.poll())) {
                            System.out.println("MyQueue Fuck!!");
                            break;
                        }
                    }
                }
                if (stack.isEmpty()) {
                    stack.push(num);
                    myStack.push(num);
                } else {
                    if (Math.random() < 0.5) {
                        stack.push(num);
                        myStack.push(num);
                    } else {
                        if (!isEqual(stack.pop(),myStack.pop())) {
                            System.out.println("stack Fuck!!");
                            break;
                        }
                    }
                }
            }
        }
        System.out.println("Nice!!");
    }

    public static void main(String[] args) {
        Test();
    }
}