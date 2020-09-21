package Praktikum02;

import java.util.AbstractList;

public class MyList extends AbstractList {

    private Node head = null;
    private Node tail = null;

    public boolean add(Object object) {
        Node node = new Node(null, null, object);
        if(head == null) {
            head = node;
            tail = node;
        }else {
            Node previousTail = tail;
            tail = node;
            tail.setPreviousNode(previousTail);
            previousTail.setNextNode(tail);
        }
        return true;
    }

    public boolean remove(Object object) {
        Node currentNode = head;
        boolean found = false;
        while(currentNode != null && !found) {
            if(currentNode.getObject().equals(object)) {
                found = true;
            }else {
                currentNode = currentNode.getNextNode();
            }
        }
        if(found) {
            Node previousNode = currentNode.getPreviousNode();
            Node nextNode = currentNode.getNextNode();
            if(previousNode == null && nextNode == null) {
                clear();
            }else if(previousNode == null) {
                currentNode.getNextNode().setPreviousNode(null);
                head = currentNode.getNextNode();
            }else if(nextNode == null){
                currentNode.getPreviousNode().setNextNode(null);
                tail = currentNode.getPreviousNode();
            }
        }
        return found;
    }

    public Object get(int index) {
        int i = 0;
        Node currentNode = head;
        while(i < index && currentNode != tail) {
            currentNode = currentNode.getNextNode();
            i++;
        }
        return currentNode.getObject();
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        Node currentNode = head;
        int i = 0;
        while(currentNode != null) {
            currentNode = currentNode.getNextNode();
            i++;
        }
        return i;
    }

    public void clear() {
        head = null;
    }

    public void printList() {
        Node currentNode = head;
        while(currentNode != null) {
            System.out.print(currentNode.getObject());
            currentNode = currentNode.getNextNode();
        }
        System.out.println();
    }
}
