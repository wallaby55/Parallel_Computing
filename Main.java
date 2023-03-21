package Philosophers;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {


    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Thread[] threads = new Thread[5];
        Chopstick[] chopsticks = new Chopstick[5];
        for(int i = 0 ; i < chopsticks.length; i++){
            chopsticks[i] = new Chopstick(i);
        }

        for(int i = 0 ; i < threads.length; i++) {
            threads[i] = new Thread(new Philosopher("Philosopher"+i, chopsticks, lock));
            threads[i].start();
        }
        System.out.println("First I will build this timer environment");
    }
}

