package wuran.study.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorExample {
    public static void main(String[] args) {
//        iteratorTest();
        lambdaForeachTest();
    }
    public static void iteratorTest(){
        List<Integer> ints = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ints.add(i);
        }
        Iterator<Integer> iterator = ints.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        for(Integer i : ints){
            System.out.println(i);
        }
    }
    public static void lambdaForeachTest(){
        List<Integer> ints = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ints.add(i);
        }
        Iterator<Integer> iterator = ints.iterator();
        iterator.forEachRemaining((i) -> System.out.println(i));
    }
}
