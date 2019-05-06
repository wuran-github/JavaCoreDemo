package wuran.study.collection;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class CollectionExample {
    public static void main(String[] args) {
        iteratorTest();
    }
    public static void iteratorTest(){
        StudentCollection students = new StudentCollection();
        students.add(new Student(1,"t1",1));
        students.add(new Student(2,"t2",2));
        students.add(new Student(3,"t3",4));
//        Iterator<Student> studentIterator = students.iterator();
        for(Student s : students){
            System.out.println(s);
        }
    }
}
class Student{
    private int id;
    private String name;
    private int age;
    public Student(int id, String name, int age){
        this.id = id;
        this.name = name;
        this.age = age;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    @Override
    public String toString(){
        return "name:"+name+", age:"+age+", id:"+id;
    }
}
class StudentCollection extends AbstractCollection<Student> implements Collection<Student>{
    private Student[] students;
    private int capacity;
    private int num;
    public StudentCollection(){
        capacity = 100;
        num = 0;
        students = new Student[capacity];
    }
    @Override
    public boolean add(Student s){
        students[num] = s;
        num++;
        return true;
    }
    @Override
    public Iterator<Student> iterator() {
        return new StudentIterator();
    }

    @Override
    public int size() {
        return num;
    }
    private class StudentIterator implements Iterator<Student>{
        private int currentNum;
        private Student current;
        public StudentIterator(){
            currentNum = -1;
            current = null;
        }
        @Override
        public boolean hasNext() {
            return StudentCollection.this.size() > currentNum+1;
        }

        @Override
        public Student next() {
            currentNum++;
            return StudentCollection.this.students[currentNum];
        }
    }
}
