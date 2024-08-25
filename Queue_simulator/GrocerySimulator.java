import java.util.concurrent.ThreadLocalRandom;

public class GrocerySimulator {
    private int totalCustomersArrived;
    private int totalCustomersServed;
    private int totalCustomersLeft;
    private long totalServiceTime;

    private GroceryQueues groceryQueues;

    public GrocerySimulator(int numCashiers, int maxGroceryQueueLength) {
        this.groceryQueues = new GroceryQueues(numCashiers, maxGroceryQueueLength);
    }

    public void simulate(int durationMinutes) {
        int currentTime = 0;
        int endTime = durationMinutes * 60;

        while (currentTime < endTime) {
            int nextArrival = ThreadLocalRandom.current().nextInt(20, 61); // 20 to 60 seconds
            if (nextArrival <= endTime - currentTime) {
                Customer customer = new Customer(currentTime);
                totalCustomersArrived++;
                boolean addedToGroceryQueue = groceryQueues.addCustomer(customer);
                if (!addedToGroceryQueue) {
                    totalCustomersLeft++;
                    customer.setWasServed(false);
                }
            }
            currentTime++;
        }
        groceryQueues.stop();
    }

    public void printStatistics() {
        totalCustomersServed = groceryQueues.getTotalServedCustomers();
        totalServiceTime = groceryQueues.getTotalServiceTime();
        double averageServiceTime = (double) totalServiceTime / totalCustomersServed;
        System.out.println("Total customers arrived: " + totalCustomersArrived);
        System.out.println("Total customers served: " + totalCustomersServed);
        System.out.println("Total customers left without service: " + totalCustomersLeft);
        System.out.println("Average service time: " + averageServiceTime + " seconds");
    }
}
