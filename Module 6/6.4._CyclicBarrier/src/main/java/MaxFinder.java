import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class MaxFinder {

    private final int nThreads,length,width,depth;
    private final int[][][] data;
    private final CyclicBarrier barrier;
    private int[] sums;
    private int max;

    /*
     * Worker constructor takes only one parameter int r, which is his associated row number
     * A worker is responsible of the calculation of the sum of each 2D-Array with row == r + nThread * round; with round >= 0
     *
     * Run should compute the sum of a 2D-array and store the result in sums[] then wait for the cyclic barrier to get the result
     * And restart computing nThreads further
     */
    class Worker implements Runnable {

        final int r;
        
        public Worker(int r){
            this.r = r;
        }

        @Override
        public void run(){
            for(int i = r; i < data.length; i+=nThreads){
                int sum = 0;
                for(int j = 0; j<data[0].length; j++){
                    for(int k=0; k<data[0][0].length; k++){
                        sum += data[i][j][k];
                    }
                }
                sums[r] = sum;
                try {
                    barrier.await();
                } catch(Exception e){}
            }
        }
    }
	
    
    /*
     *
     * Initialize all the instance variable and start the right amount of Threads
     *
     */
    private MaxFinder(int[][][] matrix, int nThreads) throws InterruptedException{
        
        this.data = matrix;
        this.nThreads = nThreads;
        this.length = matrix.length;
        this.width = matrix[0].length;
        this.depth = matrix[0][0].length;
        this.sums = new int[nThreads];

        Runnable barAction = () -> {
            int max = sums[0];
            for(int i=1; i<nThreads; i++){
                max = Math.max(max, sums[i]);
            }
            this.max = Math.max(this.max, max);
        };

        this.barrier = new CyclicBarrier(nThreads, barAction);

        Thread[] threads = new Thread[nThreads];
        for(int i = 0; i<nThreads; i++){
            threads[i] = new Thread(new Worker(i));
            threads[i].start();
        }

        // wait until done
        for (Thread thread : threads)
            thread.join();
    }
    /*
    * subSize is the length of the subarray
    * rowSize is the rowlength for all the array
    *
    */
    public static int getMaxSum(int[][][] matrix, int nThreads){
        try{
            MaxFinder mf = new MaxFinder(matrix, nThreads);
            return mf.max;
        }catch(InterruptedException e){
            return -1;
        }

    }
}
