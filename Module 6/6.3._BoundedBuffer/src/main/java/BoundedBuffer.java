public class BoundedBuffer {
    private Integer[] data;
    private int putPointer, takePointer, size;

    public BoundedBuffer(int capacity){
        this.data = new Integer[capacity];
        this.putPointer = 0;
        this.takePointer = 0;
        this.size = 0;
    }

    /*
     * Mechanics of putting x on the buffer.
     * x is added at the end of the buffer.
     */
    private void doPut(Integer x){
        if(!isFull()){
            this.data[this.putPointer] = x;
            this.putPointer++;
            this.size++;
            if(this.putPointer==this.data.length)
                this.putPointer = 0;
        }
        notify();
    }

    /*
     * Mechanics of getting the first element of the buffer
     */
    private Integer doTake(){
        int result  = this.data[this.takePointer];
        this.data[takePointer] = null;
        this.takePointer++;
        this.size--;
        if(this.takePointer==this.data.length)
            this.takePointer=0;
        notify();
        return result;
    }

    private boolean isFull(){
        return this.data.length == this.size;
    }

    private boolean isEmpty(){
        return size == 0;
    }

    /*
     * put x on the buffer if the buffer is not full
     * if the buffer is full, the thread waits until a place is free
     */
    public synchronized void put(Integer x) throws InterruptedException{
        while(isFull()){
            try {
                wait();
            }
            catch(InterruptedException e){}
        }
        doPut(x);
    }

    /*
     * Get the first element of the buffer
     * if the buffer is empty, the thread waits until an element arrives
     */
    public synchronized Integer take() throws InterruptedException{
        while(isEmpty()){
            try{
                wait();
            }
            catch(InterruptedException e){}
        }
        return doTake();
    }

    /*
     * put x on the buffer if the buffer is not full
     * if the buffer is full, the thread waits ms milliseconds until a place is free
     * if the delay is exceeded, don't put x on the buffer
     * return true if x was added on the buffer, or false
     * return false if an exception occurs
     */
    public synchronized boolean offer(Integer x, long ms){
        try{
            if(isFull())
                wait(ms);
            if(!isFull()){
                doPut(x);
                return true;
            }

        } catch(InterruptedException e){ return false; }
        return false;
    }

    /*
     * get the first element of the buffer
     * if the buffer is empty, the thread waits ms milliseconds until an element arrives
     * if the delay is exceeded or an exception occurs return null
     */
    public synchronized Integer poll(long ms){
        try {
            if (isEmpty()){
                wait(ms);
                return null;
            }
            if(!isEmpty()){
                return doTake();
            }
        } catch(InterruptedException e) { return null; }
        return null;
    }



}
