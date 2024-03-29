package my_practise.p2022.basic.myClass02;

public class Code02_DeleteGivenValue {
    public static class Node{
        public int value;
        public Node next;
        public Node(int data){
            value = data;
        }
    }

    public static Node removeValue(Node head,int value){
        while(head != null){
            if(head.value != value){
                break;
            }
            head = head.next;
        }
        Node pre = head;
        Node cur = head;
        while(cur!=null){
            if(cur.value == value){
                pre.next = cur.next;
            }else{
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }
}