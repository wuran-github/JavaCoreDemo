package wuran.study.collection;

import java.util.*;

public class AlgoExample {
    public static void main(String[] args) {
//        sortTest();
        searchTest();
    }
    static void sortTest(){
        List<Integer> list = new LinkedList<>();
        fillData(list);
        Collections.sort(list);
        print(list);
        list.sort(Collections.reverseOrder());
        print(list);
        System.out.println("shuffle");
        Collections.shuffle(list);
        print(list);

    }
    static void searchTest(){
        List<Integer> list = new LinkedList<>();
        fillData(list);
        int value = 0;
        value = Collections.binarySearch(list,5);
        System.out.println(value);
        Collections.sort(list);
        value = Collections.binarySearch(list,5);
        System.out.println(value);
    }
    static void simpleAlgoTest(){
        List<Integer> list = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        fillData(list);
        Collections.reverse(list);//倒置list
        Collections.max(list);//max
        Collections.copy(list,list2);//copy
        Collections.fill(list,1);//填充
        list.removeIf(i -> i > 5);
        list.replaceAll(i -> i+1);
    }
    static void BatchTest(){
        List<Integer> list = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        fillData(list,1,6);
        fillData(list2,3,9);
        list.removeAll(list2);//删除和list2的交集部分
        list.retainAll(list2);//保留交集部分
    }
    static void transferTest(){
        Integer[] ints = {1,2,3,4,5,6,7};//用int会报错
        List<Integer> list = Arrays.asList(ints);
        Integer[] ints2 = list.toArray(new Integer[0]);//泛型不支持基本类型，不能用int

    }
    static void fillData(List<Integer> list){
        for (int i = 10; i > 0 ; i--) {
            list.add(i);
        }
    }
    static void fillData(List<Integer> list, int start, int end){
        for (int i = start; i < end ; i++) {
            list.add(i);
        }
    }
    static <T> void print(Iterable<T> iterable){
        for(T t : iterable){
            System.out.println(t);
        }
    }
}
