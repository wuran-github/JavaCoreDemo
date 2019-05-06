package wuran.study.collection;

import java.lang.reflect.Array;
import java.util.*;

public class ViewsExample {
    public static void main(String[] args) {
//        viewTest();
//        collectionsTest();
//        subrangeTest();
//        sortedSubrangeTest();
        unmodifiableTest();
    }
    static void viewTest(){
        String[] strs = {"1","2","3"};
        List<String> list = Arrays.asList(strs);//not ArrayList
        list.set(2,"4");//ok
        for(String s : list){
            System.out.println(s);
        }
        list.add("4");//error
    }
    static void collectionsTest(){
        List<String> list = Collections.nCopies(100,"yes");
        Set<String> singleton = Collections.singleton("No");
        Set<String> emptySet = Collections.emptySet();
    }
    static void subrangeTest(){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        List<Integer> subList = list.subList(5,10);//子视图范围startIndex<=range<toIndex
        for(int i : subList){
            System.out.println(i);
        }
        subList.clear();
        for(int i : list){
            System.out.println(i);
        }
    }
    static void sortedSubrangeTest(){
        SortedSet<Integer> set = new TreeSet<>();
        for (int i = 10; i > 0 ; i--) {
            set.add(i);
        }
        SortedSet<Integer> subSet = set.subSet(5,7);
        SortedSet<Integer> subSet2 = set.headSet(5);
        SortedSet<Integer> subSet3 = set.tailSet(-1);
        for(int i: subSet3){
            System.out.println(i);
        }
    }
    static void unmodifiableTest(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        List<Integer> unmodifiableList = Collections.unmodifiableList(list);
        unmodifiableList.set(1,3);//NotSupported
    }
    static void synchronizedTest(){
        Map<String, Integer> map = new HashMap<>();
        map.put("1",1);
        Map<String,Integer> map2 = Collections.synchronizedMap(map);
    }
    static void chekcedViewTest(){
        ArrayList<String> list = new ArrayList<>();
        ArrayList list2 = list;
        list2.add(1);
        List<String> list3 = Collections.checkedList(list,String.class);
        ArrayList rawList = (ArrayList)list3;
        rawList.add(1);//throw Exception

    }
}
