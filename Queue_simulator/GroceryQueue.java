import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GroceryQueue {
    public Queue<Customer> queue;
    public int maxLength;
    public Lock lock;

    public GroceryQueue(int maxLength) {
        this.queue = new LinkedList<>();
        this.maxLength = maxLength;
        this.lock = new ReentrantLock();
    }

    public boolean addCustomer(Customer customer) {
        lock.lock();
        try {
            if (queue.size() < maxLength) {
                queue.add(customer);
                return true;
            } else {
                return false;
            }
        } finally {
            lock.unlock();
        }
    }

    public Customer serveCustomer() {
        lock.lock();
        try {
            return queue.poll();
        } finally {
            lock.unlock();
        }
    }

    public int getQueueSize() {
        lock.lock();
        try {
            return queue.size();
        } finally {
            lock.unlock();
        }
    }

    public boolean isQueueEmpty() {
        return queue.isEmpty();
    }
}
