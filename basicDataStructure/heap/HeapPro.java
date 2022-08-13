package basicDataStructure.heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class HeapPro {

    public static class MyHeap<T>{
        private ArrayList<T> heap;
        private HashMap<T,Integer> map;
        private int heapSize;
        private Comparator<? super T> com;

        public MyHeap(Comparator com){
            this.com = com;
        }



        private void swap(int a,int b){
            T o1 = heap.get(a);
            T o2 = heap.get(b);

        }
    }

}
