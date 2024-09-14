
public class Teller extends Thread {
    private final BankQueue bankQueue;
    

    public Teller(BankQueue bankQueue) {
        this.bankQueue = bankQueue;
    }

    @Override
    public void run() {
        while(bankQueue.isRunning()) {
            Customer servedCustomer = bankQueue.serveCustomer();
                if (servedCustomer != null) {
                    synchronized (bankQueue) {
                        bankQueue.totalCustomersServed++; 
                        bankQueue.totalServiceTime += servedCustomer.getServiceTime(); 
                    }
                }

        }
    }

}
