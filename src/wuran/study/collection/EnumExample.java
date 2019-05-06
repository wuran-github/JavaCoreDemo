package wuran.study.collection;

import java.util.EnumSet;

public class EnumExample {
    public static void main(String[] args) {
        normalTest();
    }
    static void normalTest(){
        EnumSet<Weekday> weekdays = EnumSet.allOf(Weekday.class);
        EnumSet<Weekday> never = EnumSet.noneOf(Weekday.class);
        EnumSet<Weekday> workday = EnumSet.range(Weekday.Monday, Weekday.Friday);
        EnumSet<Weekday> mwf = EnumSet.of(Weekday.Monday, Weekday.Wednesday, Weekday.Friday);
        mwf.add(Weekday.Saturday);
        mwf.remove(Weekday.Monday);
    }
}
enum Weekday{
    Monday,
    Tuesday,
    Wednesday,
    Thursday,
    Friday,
    Saturday,
    Sunday
}