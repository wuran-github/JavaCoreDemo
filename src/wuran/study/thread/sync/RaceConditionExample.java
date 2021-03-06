package wuran.study.thread.sync;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RaceConditionExample {
    public static void main(String[] args) {
//        oneRunnableMultiThreadTest();
        normalTest();
    }

    private static void oneRunnableMultiThreadTest() {
        Runnable r = () -> {
            int i = 0;
            while (true) {
                i++;
                System.out.println(Thread.currentThread().getId() + " " + i);
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

    private static void normalTest() {
        Bank bank = new Bank();
        Runnable r = () -> {
            while (true) {
                Random fromRandom = new Random();
                Random toRandom = new Random();
                int amount = fromRandom.nextInt(500);
                int to = toRandom.nextInt(5);
                int from = fromRandom.nextInt(5);
                if (to == from) {
                    to = (to + 1) % 5;
                }
                bank.transfer(from, to, amount);
                try {
                    Thread.sleep(1500);
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
}

class Bank {
    List<Account> accounts;
    private Lock lock;
    private Condition sufficientFunds;

    public Bank() {
        accounts = new ArrayList<>();
        lock = new ReentrantLock();
        sufficientFunds = lock.newCondition();
        init();
    }

    private void init() {
        for (int i = 0; i < 5; i++) {
            Account account = new Account(i, 1000);
            accounts.add(account);
        }
    }

    public void transfer(int from, int to, int amount) {
        lock.lock();
        try {
            int fromBalance = getBalance(from);
            while (fromBalance < amount) {
                sufficientFunds.await();
            }
            int toBalance = accounts.get(to).getBalance();
            fromBalance -= amount;
            toBalance += amount;
            accounts.get(from).setBalance(fromBalance);
            accounts.get(to).setBalance(toBalance);
            System.out.println(String.format("%d to %d , amount: %d totalBalance: %d ", from, to, amount, getTotalBalance()));
            sufficientFunds.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private int getBalance(int id) {
        return accounts.get(id).getBalance();
    }

    public int getTotalBalance() {
        int balance = 0;
        for (int i = 0; i < size(); i++) {
            balance += accounts.get(i).getBalance();
        }
        return balance;
    }

    public int size() {
        return accounts.size();
    }
}

class Account {
    private int id;
    private int balance;

    public Account(int id, int balance) {
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