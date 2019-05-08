package wuran.study.thread.sync;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SynchronizedExample {
    public static void main(String[] args) {
        normalTest();
    }
    private static void normalTest() {
        SyncBank bank = new SyncBank();
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
class SyncBank {
    List<Account> accounts;

    public SyncBank() {
        accounts = new ArrayList<>();
        init();
    }

    private void init() {
        for (int i = 0; i < 5; i++) {
            Account account = new Account(i, 1000);
            accounts.add(account);
        }
    }

    public synchronized void transfer(int from, int to, int amount) {
        try {
            int fromBalance = getBalance(from);
            while (fromBalance < amount) {
                wait();
            }
            int toBalance = accounts.get(to).getBalance();
            fromBalance -= amount;
            toBalance += amount;
            accounts.get(from).setBalance(fromBalance);
            accounts.get(to).setBalance(toBalance);
            System.out.println(String.format("%d to %d , amount: %d totalBalance: %d ", from, to, amount, getTotalBalance()));
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
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
    private class Account {
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
}

