//a.
public class Manager {
    private final int MAX_ROWS;

    private int[][] matrix;
    private int rowsAllocated = 0;
    private int matrixSum = 0;

    public Manager(int[][] matrix) {
        this.matrix = matrix;
        MAX_ROWS = matrix.length;
    }

    public synchronized int[] allocateRow() {
        if (rowsAllocated < MAX_ROWS) {
            rowsAllocated++;
            return matrix[rowsAllocated - 1];
        }
        return null;
    }

    public synchronized void setSum(int rowNum) {
        for (int i = 0; i < matrix[rowNum].length; i++)
            matrixSum += matrix[rowNum][i];

        if (rowsAllocated == MAX_ROWS)
            notifyAll();
    }

    public synchronized int getTotal() {
        while (rowsAllocated < MAX_ROWS) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return matrixSum;
    }
}

// b.

public class Worker extends Thread {
    private Manager manager;

    public Worker(Manager monitor) {
        this.manager = monitor;
    }

    @Override
    public void run() {
        int[] arr = manager.allocateRow();
        while (arr != null) {
            int sum = sumRow(arr);
            manager.setSum(sum);
            arr = manager.allocateRow();
        }
    }

    private int sumRow(int[] row) {
        int total = 0;
        for (int i = 0; i < row.length; i++) {
            total += rows[i];
        }
        return total;
    }

}

// c.

public class Driver {
    final static int ROWS = 100, COLS = 100, WORKERS = 10;

    public static void main(String[] argc) {
        int[][] matrix = createMatrix(ROWS, COLS);
        Manager mngr = new Manager(matrix);
        Worker[] workers = new Worker[WORKERS];

        for (int i = 0; i < WORKERS; i++) {
            workers[i] = new Worker(mngr);
            workers[i].start();
        }
        System.out.print("Sum of all rows is: " + mngr.getTotal());
    }

    private static int[][] createMatrix(int rows, int cols) {
        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = (Math.random() * 1000) + 1;
            }
        }
        return matrix;
    }
}
