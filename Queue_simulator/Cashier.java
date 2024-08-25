public class Cashier extends Thread {
    private final GroceryQueue groceryQueue;
    private final GroceryQueues groceryQueues;

    public Cashier(GroceryQueue groceryQueue, GroceryQueues groceryQueues) {
        this.groceryQueue = groceryQueue;
        this.groceryQueues = groceryQueues;
    }

    @Override
    public void run() {
        while (groceryQueues.isRunning()) {
            Customer servedCustomer = groceryQueue.serveCustomer();
            if (servedCustomer != null) {
                groceryQueues.totalCustomersServed++;
                groceryQueues.totalServiceTime += servedCustomer.getServiceTime();
            }
        }
    }
}
