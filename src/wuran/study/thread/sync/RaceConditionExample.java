package wuran.study.thread.sync;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class RaceConditionExample {
    public static void main(String[] args) {
        oneRunnableMultiThreadTest();
    }
    private static void oneRunnableMultiThreadTest(){
        Runnable r = () ->{
            int i = 0;
            while(true){
                i++;
                System.out.println(Thread.currentThread().getId() +" "+ i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        Thread t3 = new Thread(r);
        t1.start();
        t2.start();
        t3.start();

    }
    private static void normalTest(){
        Bank bank = new Bank();
        Runnable r =() ->{
            while (true) {
                Random fromRandom = new Random();
                Random toRandom = new Random();
                int amount = fromRandom.nextInt(50);
                int to = toRandom.nextInt(5);
            }
        };
    }
}
class Bank {
    List<Account> accounts;
    public Bank(){
        accounts = new ArrayList<>();
    }
    private void init(){
        for (int i = 0; i < 5; i++) {
            Account account = new Account(i,1000);
            accounts.add(account);
        }
    }
    public void transfer(int from, int to, int amount){
        int fromBalance = accounts.get(from).getBalance();
        int toBalance = accounts.get(to).getBalance();
        fromBalance -= amount;
        toBalance += amount;
        accounts.get(from).setBalance(fromBalance);
        accounts.get(to).setBalance(toBalance);
        System.out.println(String.format("%d to %d , amount: %d totalBalance: %d ",from,to,amount,getTotalBalance()));

    }

    public int getTotalBalance(){
        int balance = 0;
        for (int i = 0; i < size(); i++) {
            balance += accounts.get(i).getBalance();
        }
        return balance;
    }
    public int size(){
        return accounts.size();
    }
}
class Account{
    private int id;
    private int balance;
    public Account(int id,int balance){
        this.id = id;
        this.balance = balance;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}