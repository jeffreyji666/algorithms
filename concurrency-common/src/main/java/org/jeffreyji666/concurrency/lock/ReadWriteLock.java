package org.jeffreyji666.concurrency.lock;

/**
 * @author: wgji
 * @date：2014年5月5日 下午2:00:54
 * @comment:使用如下：
private final ReadWriteLock lock = new ReadWriteLock();
......
    lock.readLock();
    try {
    doSomething();
    } finally {
    lock.readUnlock();
    }
    ......
    lock.writeLock();
    try {
    doWrite(c);
    } finally {
    lock.writeUnlock();
    }
 */
public final class ReadWriteLock {
    private int readingReaders = 0; // 实际正在读取的线程数量
    private int waitingWriters = 0; // 正在等待写入的线程数量
    private int writingWriters = 0; // 实际正在写入的线程数量
    private boolean preferWriter = true;// 写入优先的话，值为true

    public synchronized void readLock() throws InterruptedException {
        while (writingWriters > 0 || (preferWriter && waitingWriters > 0)) {
            wait();
        } // 如果有写线程在写入，或者等待写入的线程数大于零且写入优先,等待
        readingReaders++;
    }

    public synchronized void readUnlock() {
        readingReaders--;
        preferWriter = true;// 释放读锁后，写入优先
        notifyAll();
    }

    public synchronized void writeLock() throws InterruptedException {
        waitingWriters++;
        try {
            while (readingReaders > 0 || writingWriters > 0) {
                wait();
            }// 如果有读线程或者写线程，等待
        } finally {
            waitingWriters--;
        }
        writingWriters++;
    }

    public synchronized void writeUnlock() {
        writingWriters--;
        preferWriter = false;// 写入操作完成后，优先读，避免读线程饿死
        notifyAll();
    }
}