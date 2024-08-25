import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Teller extends Thread {
    private final BankQueue bankQueue;
    
    private Lock lock= new ReentrantLock();

    public Teller(BankQueue bankQueue) {
        this.bankQueue = bankQueue;
        //TODO Auto-generated constructor stub
    }

    @Override
    public void run() {
        while(bankQueue.isRunning()) {
            Customer servedCustomer = bankQueue.serveCustomer();
                if (servedCustomer != null) {
                    bankQueue.totalCustomersServed++;
                    bankQueue.totalServiceTime +=servedCustomer.getServiceTime();
                }
            // lock.lock();
            // try{
                
            // }finally{
            //     lock.unlock();
            // }

        }
    }
   

}
