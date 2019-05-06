package wuran.study.collection;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class LinkedHashExample {
    public static void main(String[] args) {
//        linkedHashTest();
        normalTest();
    }
    private static void linkedHashTest(){

        LinkedHashMap<Integer,String> map = new LinkedHashMap<Integer, String>(10,0.75f,true){
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer,String> eldest){
                System.out.println("eldest:"+eldest.getKey());
                return size() > 3;
            }
        };
        map.put(10,"A");
        map.put(5,"D");
        map.put(1,"B");
        System.out.println(map.get(10));
        map.put(3,"C");

        for(Integer key : map.keySet()){
            System.out.println(key);
        }
    }
    private static void normalTest(){
        LinkedHashMap<Integer,String> map = new LinkedHashMap<>(10,0.75f,true);
        map.put(1,"A");
        map.put(2,"B");
        map.put(3,"C");
        map.get(1);
        for(Integer key : map.keySet()){
            System.out.println(key);
        }
    }
}
