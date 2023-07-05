//a.
public class Manager {
    private int resourcesAmount;
    private int[] resources;
    private int counetr;

    public Manager(int numOfResources) {
        this.resourcesAmount = numOfResources;
        this.counter = 0;
        this.resources = new int[resourcesAmount];
        for (int i = 0; i < resourcesIds; i++) {
            resources[i] = 1;
        }
    }

    public synchronized int allocate() {
        boolean found = false;
        int freeID = -1;
        while (!found) {
            try {
                for (int i = 0; i < resourcesAmount; i++) {
                    if (resources[i] == 1) {
                        found = true;
                        freeID = i;
                        resources[i] = 0; // Mark the resource as allocated
                        break; // Break the loop
                    }
                }
                if (!found) {
                    wait(); // Wait for a resource to be released
                }
            } catch (InterruptedException e) {
                System.out.print(e);
            }
        }
        counter++;
        notifyAll(); // Notify threads waiting in waitCount
        return freeID;
    }

    public synchronized void free(int index) {
        resources[index] = 1;
        notifyAll();
    }

    public synchronized void waitCount(int num) {
        while (counter != num) {
            try {
                wait(); // Wait for a resource to be released
            } catch (InterruptedException e) {
                System.out.print(e);
            }
        }
    }
}

// b.

public class Driver extends Thread {
    private Manager manager;
    private Resources[] resources;
    private boolean running;

    public Driver(Manager mngr, Resources[] resources) {
        this.manager = mngr;
        this.resources = resources;
        this.running = true;
    }

    @Override
    public void run() {
        while (running) {
            int freeRec = manager.allocate();
            int output = resources[freeRec].calculate();
            mngr.free(freeRec);
            System.out.print(output);
        }
    }

    public void finish() {
        this.running = false;
    }
}