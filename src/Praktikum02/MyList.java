package Praktikum02;

import java.util.AbstractList;

public class MyList extends AbstractList implements java.util.List{

    protected Node head = null;

    public boolean add(Object object) {
        Node node = new Node(null, null, object);
        if(head == null) {
            head = node;
        }else {
            Node tempNode = getNode(size()- 1);
            tempNode.setNextNode(node);
            node.setPreviousNode(tempNode);
        }
        return true;
    }

    public boolean remove(Object object) {
        Node tempNode = head;
        boolean found = false;
        while(tempNode != null && !found) {
            if(tempNode.getObject().equals(object)) {
                found = true;
            }else {
                tempNode = tempNode.getNextNode();
            }
        }
        if(found) {
            Node previousNode = tempNode.getPreviousNode();
            Node nextNode = tempNode.getNextNode();
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
        return getNode(index).getObject();
    }

    private Node getNode(int index) {
        int i = 0;
        Node tempNode = head;
        while(i < index && i < size()) {
            tempNode = tempNode.getNextNode();
            i++;
        }
        return tempNode;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        Node tempNode = head;
        int i = 0;
        while(tempNode != null) {
            tempNode = tempNode.getNextNode();
            i++;
        }
        return i;
    }

    public void clear() {
        head = null;
    }

    public void printList() {
        Node tempNode = head;
        while(tempNode != null) {
            System.out.print(tempNode.getObject());
            tempNode = tempNode.getNextNode();
        }
        System.out.println();
    }

    public void print() {
        System.out.println(recursion(head, 0));
    }

    public int recursion(Node n, int i) {
        if(n.getNextNode() != null) {
            i = recursion(n.getNextNode(), i);
        }
        i++;
        System.out.println(n.getObject().toString() + i);
        return i;
    }
}
