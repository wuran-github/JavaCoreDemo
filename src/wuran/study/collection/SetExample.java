package wuran.study.collection;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetExample {
    public static void main(String[] args) {
//        HashSetTest();
        TreeSetTest();
    }
    private static void HashSetTest(){
        Set<String> strings = new HashSet<>();
        for (int i = 10; i > 0; i--) {
            strings.add(i+"t");
        }
        for(String s : strings){
            System.out.println(s);
        }
    }
    private static void TreeSetTest(){
        Set<String> strings = new TreeSet<>();
        for (int i = 10; i > 0; i--) {
            strings.add(i+"t");
        }
        for(String s : strings){
            System.out.println(s);
        }
    }
}
