package Praktikum02;

public class Node {

    private final Object object;
    private Node previousNode;
    private Node nextNode;

    public Node(Node nextNode, Node previousNode, Object object) {
        this.object = object;
        setNextNode(nextNode);
        setPreviousNode(previousNode);
    }

    public void setNextNode(Node node) {
        nextNode = node;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public void setPreviousNode(Node node) {
        previousNode = node;
    }

    public Node getPreviousNode() {
        return previousNode;
    }

    public Object getObject() {
        return object;
    }
}
