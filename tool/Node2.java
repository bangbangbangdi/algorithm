package tool;

public class Node2<T> {

    public T value;
    public Node2<T> pre;
    public Node2<T> next;

    public Node2(T value) {
        this.value = value;
    }
}
