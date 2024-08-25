import java.util.concurrent.ThreadLocalRandom;

public class Customer {
    private int arrivalTime;
    private int serviceTime;
    private boolean wasServed;

    public Customer(int arrivalTime) {
        this.arrivalTime = arrivalTime;
        this.serviceTime = ThreadLocalRandom.current().nextInt(60, 301); // 60 to 300 seconds
        this.wasServed = true;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public boolean wasServed() {
        return wasServed;
    }

    public void setWasServed(boolean wasServed) {
        this.wasServed = wasServed;
    }
}
