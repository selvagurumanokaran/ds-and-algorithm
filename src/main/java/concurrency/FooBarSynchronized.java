package concurrency;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

//Print foo and bar alternatively
public class FooBarSynchronized {
    private int n;
    private Integer turn;

    public FooBarSynchronized(int n) {
        this.n = n;
        turn = 0;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; ) {
            synchronized (turn) {
                if (turn == 0) {
                    printFoo.run();
                    turn = 1;
                    i++;
                }
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; ) {
            synchronized (turn) {
                if (turn == 1) {
                    printBar.run();
                    turn = 0;
                    i++;
                }
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Runnable foo = () -> System.out.println("Foo");
        Runnable bar = () -> System.out.println("bar");
        FooBarSynchronized fooBar = new FooBarSynchronized(2);
        fooBar.foo(foo);
        fooBar.bar(bar);
    }
}

