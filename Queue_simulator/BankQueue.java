import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankQueue {
    private Queue<Customer> queue;
    private final Teller tellers[];
    private int maxLength;
    private Lock lock;
    int totalCustomersServed=0;
    int totalServiceTime=0;
    int ntellers;
    private volatile boolean running;


    public BankQueue(int ntellers, int maxLength) {
        this.queue = new LinkedList<>();
        this.running=true;
        this.ntellers=ntellers;
        this.tellers = new Teller[ntellers];
        this.maxLength = maxLength;
        this.lock = new ReentrantLock();
        for(int i=0;i<ntellers;i++){
            tellers[i]=new Teller(this);
            tellers[i].start();
        }
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

    public boolean isQueueEmpty() {
        return queue.isEmpty();
    }
    public void stop() {
        running = false;
    }
    public boolean isRunning(){
        return running;
    }
    public int getTotalServiceTime() {
        return totalServiceTime;
    }
    public int getTotalservedCustomer(){
        return totalCustomersServed;
    }
}
