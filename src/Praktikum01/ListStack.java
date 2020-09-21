package Praktikum01;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListStack {

    private List<Object> list = new ArrayList<>();
    private static final int STACK_SIZE = 100;

    public ListStack() {

    }

    public void push(Object x) {
        assert(Objects.nonNull(x));
        if(isFull()) throw new StackOverflowError();
        list.add(x);
    }

    public Object pop() {
        Object object = peek();
        if(!isEmpty()) list.remove(list.size() - 1);
        return object;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public Object peek() {
        Object object = null;
        if(!isEmpty()) object = list.get(list.size() - 1);
        return object;
    }

    public boolean isFull() {
        boolean isFull = false;
        if(list.size() == STACK_SIZE) {
            isFull = true;
        }
        return isFull;
    }
}
