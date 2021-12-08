import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class CircularLinkedList<Item> implements Iterable<Item> {

    private int n;          // size of the list
    private Node last;   // trailer of the list

    // helper linked list class
    private class Node {

        private Item item;
        private Node next;

        private Node(Item item){
            this.item = item;
            this.next = null;
        }

    }

    public CircularLinkedList() {
        last = null;
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public Node getLast(){
        return last;
    }

    public Item getItem(Node n){
        return n.item;
    }

    public void print() {
        for(Item i : this)
            System.out.println(i);
    }

    public void main(String[] args) {
        CircularLinkedList<String> liste = new CircularLinkedList<>();
        liste.enqueue("1");
        liste.enqueue("2");
        liste.enqueue("3");
        liste.enqueue("4");
        liste.print();
    }


    /**
     * Append an item at the end of the list
     * @param item the item to append
     */
    public void enqueue(Item item) {
        if(isEmpty()){
            last = new Node(item);
            last.next = last;
        }
        else{
            Node a = new Node(item);
            a.next = last.next;
            last.next = a;
            last = a;
        }
        n++;
    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their indices).
     * Returns the element that was removed from the list.
     */
    public Item remove(int index) {
        if (index < 0 || index > n - 1){
            throw new IndexOutOfBoundsException("ERREUR : index invalide");
        }
        if(n==0){
            return null;
        }
        Node a;
        if(n==1){
            a = last;
            last = null;
            n--;
            return a.item;
        }
        else {
            Node prevA = last;
            for(int i=0; i < index; i++){
                prevA = prevA.next;
            }
            a = prevA.next;i
            prevA.next = a.next;
            a.next = null;
            if(index == n-1){
                last = prevA;
            }
            n--;
            return a.item;
        }
    }


    /**
     * Returns an iterator that iterates through the items in FIFO order.
     * @return an iterator that iterates through the items in FIFO order.
     */
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    /**
     * Implementation of an iterator that iterates through the items in FIFO order.
     */
    private class ListIterator implements Iterator<Item> {

        final int nInitial = n;
        private Node runner = last;
        private int opMake = 0;

        /**
         * Check if the List has been changed during iteration
         */
        private boolean failFastCheck() throws ConcurrentModificationException {
            if(nInitial != n){
                throw new ConcurrentModificationException("ERREUR : Liste modifier durant iteration");
            }
            else{
                return true;
            }
        }

        /**
         * Returns the next element in the iteration
         */
        public Item next() {
            failFastCheck();
            if(!hasNext()){
                return null;
            }
            if(opMake == 0){
                runner = runner.next;
            }
            Item item = runner.item;
            runner = runner.next;
            opMake ++;
            return item;
        }

        public boolean hasNext() {
            return failFastCheck() && runner != null && opMake < nInitial;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

}
