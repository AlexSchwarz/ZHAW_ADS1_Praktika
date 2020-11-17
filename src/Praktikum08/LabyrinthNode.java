package Praktikum08;

import java.util.ArrayList;
import java.util.List;

public class LabyrinthNode<E> extends Node<E>{

    private boolean marked;
    private LabyrinthNode<E> previous;

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    public LabyrinthNode<E> getPrev() {
        return previous;
    }

    public void setPrev(LabyrinthNode<E> previous) {
        this.previous = previous;
    }
}
