package Praktikum02;

import java.util.ArrayList;
import java.util.Collections;

public class SortedList extends MyList implements java.util.List {

    @Override
    public boolean add(Object object) {
        if(object instanceof Comparable) {
            add((Comparable) object);
            return true;
        }else {
            return false;
        }
    }

    public boolean add(Comparable comparable) {
        super.add(comparable);
        sort();
        printList();
        return true;
    }

    public void sort() {
        ArrayList<Comparable> tempList = new ArrayList<>();
        Node tempNode = head;
        while(tempNode != null) {
            tempList.add((Comparable) tempNode.getObject());
            tempNode = tempNode.getNextNode();
        }
        Collections.sort(tempList);
        clear();
        for(Comparable comparable : tempList) {
            super.add(comparable);
        }
    }
}
