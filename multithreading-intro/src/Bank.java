import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;

public class Bank {
    String accountNumber;
    OperationsQueue operationsQueue;
    Lock lock = new ReentrantLock(); // Lock to synchronize access to balance
    int balance = 0;

    public Bank(String accountNumber, OperationsQueue operationsQueue) {
        this.accountNumber = accountNumber;
        this.operationsQueue = operationsQueue;
    }

    // A deposit function that will run in parallel on a separate thread. It will be a loop where in each iteration, it reads the amount from the operationQueue and deposits the amount.
    public void deposit() {
        while (true) {
            int amount = operationsQueue.getNextItem();
            if (amount == -9999) {
                break;
            }
            if (amount > 0) {
                lock.lock(); // Acquire the lock
                try {
                    balance = balance + amount;
                    System.out.println("Deposited: " + amount + " Balance: " + balance);
                } finally {
                    lock.unlock(); // Release the lock
                }
            } else {
                operationsQueue.add(amount);
                System.out.println("Operation added back " + amount);
            }
        }
    }

    // A withdraw function that will run in parallel on a separate thread. It will be a loop where in each iteration, it reads the amount from the operationQueue and withdraws the amount.
    public void withdraw() {
        while (true) {
            int amount = operationsQueue.getNextItem();
            if (amount == -9999) {
                break;
            }

            lock.lock(); // Acquire the lock
            try {
                if (balance + amount < 0) {
                    System.out.println("Not enough balance to withdraw " + amount);
                    continue;
                }

                if (amount < 0) {
                    balance = balance + amount;
                    System.out.println("Withdrawn: " + amount + " Balance: " + balance);
                } else {
                    operationsQueue.add(amount);
                    System.out.println("Operation added back " + amount);
                }
            } finally {
                lock.unlock(); // Release the lock
            }
        }
    }
}
