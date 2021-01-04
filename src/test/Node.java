package test;

public class Node<T> {

    private Node<T> next;
    private final T data;

    public Node(T data) {
        next = null;
        this.data = data;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public Node<T> getNext() {
        return next;
    }

    public T getData() {
        return data;
    }
}
