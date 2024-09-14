import java.util.concurrent.ThreadLocalRandom;

public class QueueSimulator {
    private int totalCustomersArrived;
    private int totalCustomersServed;
    private int totalCustomersLeft;
    private long totalServiceTime;

    private BankQueue bankQueue;


    public QueueSimulator(int numTellers, int maxBankQueueLength) {
        this.bankQueue = new BankQueue(numTellers, maxBankQueueLength);
    }

    public void simulate(int durationMinutes) {
        int currentTime = 0;
        int endTime = durationMinutes*60 ;

        while (currentTime < endTime) {
            int nextArrival = ThreadLocalRandom.current().nextInt(20, 61); // 20 to 60 seconds
            if(nextArrival<=endTime-currentTime) {
                Customer customer = new Customer(currentTime);
                totalCustomersArrived++;
                boolean addedToBankQueue = bankQueue.addCustomer(customer);
                if (!addedToBankQueue ) {
                    totalCustomersLeft++;
                    customer.setWasServed(false);
                }
            }
            currentTime++;
        }
        bankQueue.stop();
    }

    
    public void printStatistics() {
        totalCustomersServed = bankQueue.getTotalservedCustomer();
        totalServiceTime = bankQueue.getTotalServiceTime();
        double averageServiceTime = (double) totalServiceTime / totalCustomersServed;
        System.out.println("Total customers arrived: " + totalCustomersArrived);
        System.out.println("Total customers served: " + totalCustomersServed);
        System.out.println("Total customers left without service: " + totalCustomersLeft);
        System.out.println("Average service time: " + averageServiceTime + " seconds");
    }
}
