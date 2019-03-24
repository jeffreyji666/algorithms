package org.jeffreyji666.concurrency.cas;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class LockFreeStack<V> {
    //the queue node
    private class Node<V> {
        public V value = null;
        public AtomicReference<Node<V>> next = null;

        public Node(V value, Node next) {
            this.value = value;
            this.next = new AtomicReference<Node<V>>(next);
        }
    }


    private AtomicReference<Node<V>> head = null;                                //queue head
    private AtomicReference<Node<V>> tail = null;                                //queue tail
    private AtomicInteger queueSize = new AtomicInteger(0);                      //size of the queue

    public LockFreeStack() {
        Node<V> dummy = new Node<V>(null, null);                                  //init an dummy node
        //init head and tail,reference to  the same dummy node
        head = new AtomicReference<Node<V>>(dummy);
        tail = new AtomicReference<Node<V>>(dummy);
    }

    /**
     * <p>Add an value to the end of the queue</p>
     * <p>This method is based on CAP operation,and is thread safe.</p>
     * <p>It guarantee the value will eventually add into the queue</p>
     *
     * @param value the value to be added into the queue
     */
    public void enQueue(V value) {
        Node<V> newNode = new Node<V>(value, null);
        Node<V> oldTail = null;
        while (true) {
            oldTail = tail.get();
            AtomicReference<Node<V>> nextNode = oldTail.next;
            if (nextNode.compareAndSet(null, newNode)) {
                break;
            } else {
                tail.compareAndSet(oldTail, oldTail.next.get());
            }
        }
        queueSize.getAndIncrement();
        tail.compareAndSet(oldTail, oldTail.next.get());
    }

    /**
     * <p>Get an Value from the queue</p>
     * <p>This method is based on CAP operation,thread safe</p>
     * <p>It guarantees return an value or null if queue is empty eventually</p>
     *
     * @return value on the head of the queue,or null when queue is empty
     */
    public V deQueue() {
        while (true) {
            Node<V> oldHead = head.get();
            Node<V> oldTail = tail.get();
            AtomicReference<Node<V>> next = oldHead.next;
            if (next.get() == null) {
                return null;              ///queue is empty
            }
            if (oldHead == tail.get()) {
                tail.compareAndSet(oldTail, oldTail.next.get());   //move the tail to last node
                continue;
            }
            if (head.compareAndSet(oldHead, oldHead.next.get())) {
                queueSize.getAndDecrement();
                return oldHead.next.get().value;
            }
        }
    }

    /**
     * <p>Get the size of the stack</p>
     * <p>This method doesn't reflect timely state when used in concurrency environment</p>
     *
     * @return size of the stack
     */
    public int size() {
        return queueSize.get();
    }

    /**
     * <p>Check if the stack is empty</p>
     * <p>This method doesn't reflect timely state when used in concurrency environment</p>
     *
     * @return false unless stack is empty
     */
    public boolean isEmpty() {
        return queueSize.get() == 0;
    }
}

