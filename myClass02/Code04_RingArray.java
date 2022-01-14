package myClass02;


public class Code04_RingArray {

    public static class MyQueue {
        private int[] arr;
        private int pushi;
        private int polli;
        private int size;
        private final int limit;

        public MyQueue(int l) {
            arr = new int[l];
            pushi = 0;
            polli = 0;
            size = 0;
            limit = l;
        }

        public void push(int i) {
            if (size >= limit) {
                throw new RuntimeException("栈满了");
            }
            arr[pushi] = i;
            pushi = nextIndex(pushi);
            size++;
        }

        public int pop(int i){
            if(size<=0){
                throw new RuntimeException("栈空了");
            }
            int result = arr[polli];
            polli = nextIndex(polli);
            size--;
            return result;
        }

        public boolean isEmpty(){
            return size == 0;
        }

        public int nextIndex(int index) {
            return index < limit - 1 ? index + 1 : 0;
        }
    }
}