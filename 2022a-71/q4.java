//a.
public class ServiceAllocator {
    final int numOfServicePoints;
    final boolean[] servicePointsAvailablity;

    public ServiceAllocator(int numOfServicePoints) {
        this.numOfServicePoints = numOfServicePoints;
        this.servicePointsAvailablity = new boolean[numOfServicePoints];
        for (boolean point : servicePointsAvailablity)
            point = true;
    }

    public synchronized int allocateServicePoint() {
        while (true) {
            try {
                for (int i = 0; i < numOfServicePoints; i++) {
                    if (servicePointsAvailablity[i] == true) {
                        servicePointsAvailablity[i] = false;
                        return i;
                    }
                }
                wait();
            } catch (Exception e) {
                System.out.print(e);
            }
        }
    }

    public synchronized void freeSP(int servicePointToSetFree) {
        servicePointsAvailablity[servicePointToSetFree] = true;
        notifyAll();
    }
}

// b.
/*
 * 1) a - false, b -true, c - false, d - false
 * 2)
 * a. a
 * b. b
 * c. e
 */