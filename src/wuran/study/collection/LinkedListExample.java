package wuran.study.collection;

import java.util.LinkedList;
import java.util.ListIterator;

public class LinkedListExample {
    public static void main(String[] args) {
        linkedListTest();
    }
    public static void linkedListTest(){
        addTest();
    }
    public static void addTest(){
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1,1);
        ListIterator<Integer> iterator = list.listIterator();
        iterator.remove();
    }

}
