package com.example.threaddemo.threadpool.my;

import java.util.HashSet;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 帅气的景天老师
 * @create 2023/2/23 14:12
 */
public class GpThreadPool {

    //定义第1个核心参数核心线程数
    private volatile int corePoolSize;

    //定义第2个核心参数最大线程数
    private volatile int maximumPoolSize;

    //定义第3个核心参数线程最大存活时间
    private volatile long keepAliveTime;

    //定义第4个核心参数最大线程数
    private final BlockingQueue<Runnable> workQueue;

    //定义第5个核心参数线程创建工程
    private volatile ThreadFactory threadFactory;

    //定义第6个核心参数拒绝策略
    private volatile GpRejectExecutionHandler handler;

    //用来记录当前线程池中在运行的线程数
    private final AtomicInteger ctl = new AtomicInteger(0);

    //锁
    private final ReentrantLock mainLock = new ReentrantLock();

    //装线程的真正池子
    private final HashSet<Worker> workers = new HashSet<Worker>();

    //只提供一种构造方法，在创建对象的时候需要传7个核心参数
    public GpThreadPool(int corePoolSize,
                        int maximumPoolSize,
                        long keepAliveTime,
                        TimeUnit unit,
                        BlockingQueue<Runnable> workQueue,
                        ThreadFactory threadFactory,
                        GpRejectExecutionHandler handler) {
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.keepAliveTime = unit.toNanos(keepAliveTime);
        this.workQueue = workQueue;
        this.threadFactory = threadFactory;
        this.handler = handler;
    }

    //只提供一种提交任务的方法
    public void execute(Runnable command) {
        //如果任务提交的是null则直接抛异常
        if (command == null)
            throw new NullPointerException();

        //拿到当前池子里正在运行的线程数量
        int currentNum = ctl.get();
        //如果正在运行的线程数小于核心线程，则直接创建线程去执行
        if (currentNum < corePoolSize) {
            if (addWorker(command, true))
                return;
            currentNum = ctl.get();
        }
        //如果正在运行的线程数不小于核心线程，则把任务往队列里面放
        if (workQueue.offer(command)) {

        }
        //如果在运行的线程达到核心线程了，且队列也放满了，就尝试添加非核心线程
        else if (!addWorker(command, false))
            //如果线程数达到最大了，队列也满了则执行拒绝策略
            reject(command);
    }

    private boolean addWorker(Runnable firstTask, boolean core) {
        boolean workerStarted = false;
        boolean workerAdded = false;
        Worker w = null;
        try {
            w = new Worker(firstTask);
            ctl.getAndIncrement();
            final Thread t = w.thread;
            if (t != null) {
                final ReentrantLock mainLock = this.mainLock;
                mainLock.lock();
                try {
                    //判断线程是不是已经在执行了
                    if (t.isAlive())
                        throw new IllegalThreadStateException();
                    workers.add(w);
                    workerAdded = true;
                } finally {
                    mainLock.unlock();
                }
                if (workerAdded) {
                    t.start();
                    workerStarted = true;
                }
            }
        } finally {

        }
        return workerStarted;
    }

    private final class Worker
            extends AbstractQueuedSynchronizer
            implements Runnable
    {
        private static final long serialVersionUID = 6138294804551838833L;

        final Thread thread;
        Runnable firstTask;

        Worker(Runnable firstTask) {
            this.firstTask = firstTask;
            this.thread = threadFactory.newThread(this);
        }

        public void run() {
            runWorker(this);
        }

        public void lock()        { acquire(1); }
        public boolean tryLock()  { return tryAcquire(1); }
        public void unlock()      { release(1); }
        public boolean isLocked() { return isHeldExclusively(); }

    }

    final void reject(Runnable command) {
        handler.rejectedExecution(command, this);
    }

    final void runWorker(Worker w) {
        Runnable task = w.firstTask;
        w.firstTask = null;
        try {
            while (task != null || (task = getTask()) != null) {
                try {
                    Throwable thrown = null;
                    try {
                        task.run();
                    } catch (RuntimeException x) {
                        thrown = x; throw x;
                    } catch (Error x) {
                        thrown = x; throw x;
                    } catch (Throwable x) {
                        thrown = x; throw new Error(x);
                    } finally {
                    }
                } finally {
                    task = null;
                }
            }
        } finally {
        }
    }

    private Runnable getTask() {

        for (;;) {
            int wc = ctl.get();
            boolean timed = wc > corePoolSize;

            try {
                Runnable r = timed ?
                        workQueue.poll(keepAliveTime, TimeUnit.NANOSECONDS) :
                        workQueue.take();
                if (r != null)
                    return r;
            } catch (InterruptedException retry) {
            }
        }
    }

}
