import java.util.Stack;

public class MyQueue<E> {

    Stack<E> s1;
    Stack<E> s2;

    private E front;

    /*
     * Constructor
     */
    public MyQueue() {
        s1 = new Stack<>();
        s2 = new Stack<>();
        this.front = null;
    }

    /*
     * Push element x to the end of the queue (remember, a queue is FIFO)
     */
    public void enqueue(E elem) {
        if(empty()){
            s1.push(elem);
            this.front = elem;
        }
        else {
            s1.push(elem);
        }
    }

    /*
     * Removes the front element of this queue
     */
    public E dequeue() {
        E resultat;
        while(!empty()){
            s2.push(s1.pop());
        }
        resultat = s2.pop();
        while(!s2.empty()){
            enqueue(s2.pop());
        }
        return resultat;
    }

    /*
     * Get the first element of this list but does not remove it
     */
    public E peek() {
        return front;
    }

    /*
     * Tells if the queue is empty or not.
     */
    public boolean empty() {
        return s1.empty();
    }

}
