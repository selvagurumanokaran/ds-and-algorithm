package concurrency;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

//Print foo and bar alternatively
public class FooBar {
    private int n;
    ReentrantLock locker;
    Condition printCondition;
    Integer turn;

    public FooBar(int n) {
        this.n = n;
        locker = new ReentrantLock();
        printCondition = locker.newCondition();
        turn = 0;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            locker.lock();
            while (turn != 0)
                printCondition.await();
            printFoo.run();
            turn = 1;
            printCondition.signalAll();
            locker.unlock();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            locker.lock();
            while (turn != 1)
                printCondition.await();
            printBar.run();
            turn = 0;
            printCondition.signalAll();
            locker.unlock();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        /*Runnable foo = () -> System.out.println("Foo");
        Runnable bar = () -> System.out.println("bar");
        FooBar fooBar = new FooBar(2);
        fooBar.foo(foo);
        fooBar.bar(bar);
        Semaphore hydrogenSema = new Semaphore(2);
        hydrogenSema.availablePermits();*/
        ConcurrentSkipListSet<Integer> integers = new ConcurrentSkipListSet<>(Arrays.asList(0, 0));
        System.out.println(integers.size());
    }
}
