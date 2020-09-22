package Praktikum02;

import java.util.AbstractList;

public class MyListOld extends AbstractList implements java.util.List{

    private Node head = null;

    public boolean add(Object object) {
        Node node = new Node(null, null, object);
        if(head == null) {
            head = node;
        }else {
            Node currentNode = head;
            while(currentNode.getNextNode() != null) {
                currentNode = currentNode.getNextNode();
            }
            currentNode.setNextNode(node);
            node.setPreviousNode(currentNode);
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
                nextNode.setPreviousNode(null);
                head = nextNode;
            }else if(nextNode == null){
                previousNode.setNextNode(null);
            }else {
                nextNode.setPreviousNode(previousNode);
                previousNode.setNextNode(nextNode);
            }
        }
        return found;
    }

    public Object get(int index) {
        int i = 0;
        Node currentNode = head;
        while(i < index && i < size()) {
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
