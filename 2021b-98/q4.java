
import java.util.*;

import javax.annotation.Resource;

public class ResourcePool {
    private Resource[] resources;
    private int[] resourcesIdCalled;
    private boolean[] resourcesIdBeingUsed;

    private int numOfResources;

    public ResourcePool(int n) {
        this.numOfResources = n;
        resources = new Resources[n];
        resourcesIdCalled = new int[n];
        resourcesIdBeingUsed = new boolean[n];
        for (int i = 0; i < n; i++) {
            resources[i] = new Resource(i + 1);
            resourcesIdCalled[i] = 0;
            resourcesIdBeingUsed[i] = false;
        }
    }

    public synchronized Resource getResource() {
        try {
            while (true) {
                for (int i = 0; i < numOfResources; i++) {
                    if (!resourcesIdBeingUsed[i]) {
                        resourcesIdBeingUsed[i] = true;
                        resourcesIdCalled[i]++;
                        return resources[i];
                    }
                }
                wait();
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public synchronized void returnResource(Resource resource) {
        try {
            resourcesIdBeingUsed[resource.getId() - 1] = false;
            notifyAll();
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public int getUseCount(int id) {
        return resourcesIdCalled[id - 1];
    }
}

// b.
public class MyThread extends Thread {
    private ResourcePool pool;

    public MyThread(ResourcePool pool) {
        this.pool = pool;
    }

    public ResourcePool getPool() {
        return this.pool;
    }

    @Override
    public void run() {
        try {
            Resource resource = pool.getResource();
            System.out.println("Resource id activated is: " + resource.getId());
            resource.use();
            pool.returnResource(resource);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

    // c.

public static void main(String[] argv){
    ResourcePool pool = new ResourcePool(3);
    MyThread[] threads = new MyThread[10];

    for(int i = 0; i<10;i++){
        threads[i] = new MyThread(pool);
        threads[i].start();
    }
     for(int i = 0; i < 10; i++){
        try {
            threads[i].join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // after all threads have finished, print the use count of each resource
    for(int i = 0; i < 3; i++){
        int useCount = pool.getUseCount(i);  
        System.out.println("Use count of resource #" + (i) + " is: " + useCount);
    }
}