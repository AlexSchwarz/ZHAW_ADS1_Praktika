package test;

public class LinkedList<T> {

    private Node<T> head;

    public void add(T object) {
        if(head.getNext() == null) {
            head.setNext(new Node<>(object));
        }else {

        }
    }
}
