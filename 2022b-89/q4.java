public class MTStack<T> {
    private int size;
    private int filled;
    private int top;
    private ArrayList<T> elements;

    public MTStack() {
        this(10);
    }

    public MTStack(int s) {
        this.size = s > 0 ? s : 10;
        this.filled = 0;
        this.top = -1;
    }

    public synchronized void push(T item) {
        boolean inserted = false;
        while (!inserted) {
            try {
                if (size > filled) {
                    elements.add(item);
                    filled++;
                    notifyAll(); // Notify threads waiting in pop
                    inserted = true;
                } else {
                    wait();
                }
            } catch (Exception e) {
                System.out.print(e);
            }
        }
    }

    public synchronized T pop() {
        boolean popped = false;
        while (!popped) {
            try {
                if (size >= filled && filled > 0) {
                    T ret = elements.remove(--filled); // remove the item at the top of the stack
                    filled--;
                    notifyAll(); // Notify threads waiting in push
                    popped = true;
                    return ret;
                } else {
                    wait();
                }
            } catch (Exception e) {
                System.out.print(e);
            }
        }
    }

    public synchronized boolean isEmtpy() {
        return filled == 0;
    }
}

class Driver extends Thread {
    private int sumOfPops;
    private int totalPops;
    private int runs;
    private MTStack stk;

    public Driver(int num, MTStack<Integer> stk) {
        this.sumOfPops = 0;
        this.totalPops = 0;
        this.runs = num;
        this.stk = stk;
    }

    @Override
    public void run() {
        for (int i = 0; i < runs; i++) {
            stk.push(runs);
            Integer popped = stk.pop();
            sumOfPops += popped;
            totalPops++;
            try {
                Thread.sleep(Math.random() * 1000);
            } catch (Exception e) {
                System.out.print(e);
            }

        }
        notifyAll();
    }

    public synchronized int getTotal() {
        while (this.isAlive()) {
            try {
                if (totalPops != runs)
                    wait();
                return sumOfPops;
            } catch (Exception e) {
                System.out.print(e);
            }
        }
    }
}