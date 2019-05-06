package wuran.study.generics;


import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Arrays;

public class GenericExample {
    public static void main(String[] args) throws NoSuchMethodException {
//        extendsBoundTest();
//        superBoundTest();
//        superBoundApplyTest();
        reflectGenericTest();
    }
    public static void extendsBoundTest(){
        Pair<? extends Employee> pe = null;
        pe = new Pair<Manager>();
        Employee e = pe.getFirst();
//        pe.setFirst(new Employee());//Error
    }
    public static void superBoundTest(){
        Pair<? super Manager> pm = null;
        pm = new Pair<Employee>();
//        Employee e = pm.getFirst();//Error
//        Manager m = pm.getFirst();//Error
        pm.setFirst(new Manager());
//        pm.setFirst(new Employee());//Error
    }
    public static void superBoundApplyTest(){
        LocalDate date = LocalDate.now();
        LocalDate date2 = LocalDate.of(1995,12,12);
        LocalDate date3 = min(date,date2);//OK
        System.out.println(date3);
    }
    public static <T extends Comparable<? super T>> T min(T... ts){
        Arrays.sort(ts);
        return ts[0];
    }
    public static void notBoundTest(){
        Pair<?> pair = new Pair<>();
//        pair.setFirst(new Object());//Error
        hasNulls(pair);
    }
    public static boolean hasNulls(Pair<?> p){
        return p.getFirst() == null || p.getSecond() == null;
    }

    public static void captureWildcardTest(){

    }
    public static void swap(Pair<?> p){
        swapHelper(p);
    }
    public static <T> void swapHelper(Pair<T> p){
        T t = p.getFirst();
        p.setFirst(p.getSecond());
        p.setSecond(t);
    }
    public static void reflectGenericTest() throws NoSuchMethodException {
        Method method = GenericExample.class.getMethod("min", Comparable[].class);
        Type type = method.getGenericReturnType();
        printType(type);
    }
    public static void printGeneric(Class<?> c){

        TypeVariable[] typeVariables = c.getTypeParameters();
        for (int i = 0; i < typeVariables.length ; i++) {
            TypeVariable typeVariable = typeVariables[i];
            System.out.println(typeVariable.getTypeName());
        }
    }
    public static void printType(Type type){
        System.out.println(type.getTypeName());
        System.out.println(type.getClass());
    }
}
class Pair<T>{
    private T first;
    private T second;

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public T getSecond() {
        return second;
    }

    public void setSecond(T second) {
        this.second = second;
    }
}
class Employee {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
class Manager extends Employee{

}
