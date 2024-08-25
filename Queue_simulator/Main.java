public class Main {
    public static void main(String[] args) {


        int durationMinutes = 120;

        // BankQueue simulation
        System.out.println("Bank Queue Simulation Results:");
        QueueSimulator bankQueueSimulator = new QueueSimulator(5,5); // 3 tellers, max queue length 5
        bankQueueSimulator.simulate(durationMinutes);
        bankQueueSimulator.printStatistics();

        // GroceryQueue simulation
        System.out.println("\nGrocery Queue Simulation Results:");
        GrocerySimulator grocerySimulator = new GrocerySimulator(3, 2); // 3 cashiers, max queue length 2
        grocerySimulator.simulate(durationMinutes);
        grocerySimulator.printStatistics();
    }
}
