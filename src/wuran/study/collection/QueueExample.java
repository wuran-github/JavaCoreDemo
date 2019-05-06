package wuran.study.collection;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class QueueExample {
    public static void main(String[] args) {
//        queueTest();
        dequeTest();
    }
    private static void queueTest(){
        Queue<String> queue = new LinkedList<>();
        queue.add("t1");
        queue.add("t2");
        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }
    private static void dequeTest(){
        Deque<String> deque = new LinkedList<>();
        deque.addFirst("t1");
        deque.addFirst("t2");
        System.out.println(deque.peek());
    }
}
