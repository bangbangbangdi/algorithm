package my_practise.p2022.basic.myClass01;


import java.util.HashSet;

public class Test {
    public static void main(String[] args) {
        HashSet hashSet = new HashSet(2);
        hashSet.add(1);
        hashSet.add(2);
        hashSet.add(3);
//        Iterator iterator = hashSet.iterator();
//        while(iterator.hasNext()){
//            System.out.println(iterator.next());
//        }
        System.out.println(hashSet.size());
    }
}