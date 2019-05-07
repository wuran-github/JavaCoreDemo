package wuran.study.thread;

import java.util.Scanner;

public class ThreadDemo {
    public static void main(String[] args) {
//        caputreInterruptTest();
//        stateTest();
//        daemonTest();
        captureThreadUnchekedExceptionTest();
    }

    private static void normalTest() {
        Runnable r = () -> {
            try {
                int result = 0;
                for (int i = 0; i < 1000; i++) {
                    result += i;
                    Thread.sleep(1000);
                    System.out.println(result);
                }
            } catch (InterruptedException ex) {
                System.out.println("thread is already stop");
            } finally {
            }
        };
        Thread t = new Thread(r);
        t.start();
        Scanner in = new Scanner(System.in);
        String read = in.nextLine();
        System.out.println(read);
        t.interrupt();
    }

    private static void captureInterruptTest() {
        Runnable r = new SumOneToThousand();
        Thread t = new Thread(r);
        t.start();
        Scanner in = new Scanner(System.in);
        String read = in.nextLine();
        t.interrupt();
    }

    private static void stateTest() {
        SumOneToThousand r = new SumOneToThousand();
        Thread t = new Thread(r);
        System.out.println(t.getState());
        t.start();
        System.out.println(t.getState());
        Scanner in = new Scanner(System.in);
        String read = in.nextLine();
        t.interrupt();
        System.out.println(t.getState());
    }
    private static void daemonTest(){
        Runnable r = new DaemonSimple();
        Thread t = new Thread(r);
        t.start();
    }
    private static void captureThreadUnchekedExceptionTest(){
        Runnable r = new CaptureExceptionDemo();
        Thread t= new Thread(r);
        t.setUncaughtExceptionHandler(new Captor());
        t.start();
    }

}
class Captor implements Thread.UncaughtExceptionHandler{

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println(e.getMessage());
        System.out.println("capture Exception success ");
    }
}
class CaptureExceptionDemo implements Runnable{
    @Override
    public void run() {
        int[] t = new int[1];
        t[1] = 0;
    }
}
class DaemonSimple implements Runnable{
    private ChildDaemon daemon = null;
    private Thread daemonThread = null;
    @Override
    public void run() {
        try {
            task();
        } catch (InterruptedException e) {
            System.out.println("interrupted");
        }
    }
    private void task() throws InterruptedException {
        startDaemon();
        int i = 1;
        while(true) {
            Thread.sleep(1000);
            System.out.println("task already run " + i);
            i++;
            if (daemon.times > 10) {
                System.out.println("times is ten. thread stop.");
                break;
            }
        }
    }
    private void startDaemon(){
        daemon = new ChildDaemon();
        Thread t = new Thread(daemon);
        t.setDaemon(true);
        t.start();
        daemonThread = t;
    }
    private class ChildDaemon implements Runnable{
        public int times = 0;
        @Override
        public void run() {
            try {
                task();
            } catch (InterruptedException e) {
                System.out.println("daemon thread was interrupted");
            }
        }
        private void task() throws InterruptedException {
            while(true) {
                Thread.sleep(1000);
                times++;
            }
        }
    }
}
class SumOneToThousand implements Runnable {
    @Override
    public void run() {
//        runTaskOne();
        runTaskTwo();
    }

    private void runTaskOne() {
        myTask();
        if (Thread.currentThread().isInterrupted()) {
            System.out.println("capture interrupt success");
        }
    }

    private void runTaskTwo() {
        try {
            myTask2();
        } catch (InterruptedException e) {
            System.out.println("capture interruptedException success");
        }
    }

    private void myTask() {
        try {
            int result = 0;
            for (int i = 0; i < 1000; i++) {
                result += i;
                Thread.sleep(1000);
                System.out.println(result);
            }
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    private void myTask2() throws InterruptedException {
        int result = 0;
        for (int i = 0; i < 1000; i++) {
            result += i;
            Thread.sleep(1000);
            System.out.println(result);
        }
    }

}
