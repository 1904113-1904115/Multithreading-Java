import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;

public class Customer {
    private int arrivalTime;
    private int serviceTime;
    private AtomicBoolean wasServed;

    public Customer(int arrivalTime) {
        this.arrivalTime = arrivalTime;
        this.serviceTime = ThreadLocalRandom.current().nextInt(60, 301); // 60 to 300 seconds
        this.wasServed = new AtomicBoolean(true);
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public boolean wasServed() {
        return wasServed.get();
    }

    public void setWasServed(boolean served) {
        wasServed.set(served);
    }
}
