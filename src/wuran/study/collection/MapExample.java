package wuran.study.collection;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapExample {
    public static void main(String[] args) {
//        hashMapTest();
        updateMapTest();
    }
    public static void hashMapTest(){
        Map<String, Integer> map = new HashMap<>();
        map.put("t1",1);
        map.put("t2",2);
        System.out.println(map.get("t1"));
        for(String key : map.keySet()){

        }
        for(Integer i : map.values()){

        }
        System.out.println(map.get("null"));//null
        System.out.println(map.getOrDefault("null",0));
    }
    private static void updateMapTest(){
        Map<String,Integer> counts = new TreeMap<>();
        String[] words = {"word","hello","world","hello"};
        for(String word : words){
            counts.merge(word,1,(n1,n2) -> n1+n2);
//            counts.put(word,counts.getOrDefault(word,0)+1);
        }
        counts.forEach((k,v) -> System.out.println("word:"+k+", count:"+v));
    }
    private static void mapViewTest(){
        Map<String,Integer> counts = new TreeMap<>();
        String[] words = {"word","hello","world","hello"};
        for(String word : words){
            counts.merge(word,1,(n1,n2) -> n1+n2);
        }
    }

}
