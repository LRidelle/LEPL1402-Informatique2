import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class MyArrayList<Item> implements Iterable<Item> {

    private Object [] list;
    private int size;


    public MyArrayList(int initSize) throws IndexOutOfBoundsException{
        if(initSize < 0){
            throw new IndexOutOfBoundsException();
        }
        this.size = 0;
        this.list = new Object[initSize];
    }


    /*
    * Checks if 'list' needs to be resized then add the element at the end 
    * of the list.
    */
    public void enqueue(Item item){
        if(this.size >= this.list.length){
            increaseSize();
        }
        list[size] = item;
        size ++;
    }


    /*
    * Removes the element at the specified position in this list.
    * Returns the element that was removed from the list. You dont need to 
    * resize when removing an element.
    */
    public Item remove(int index) throws IndexOutOfBoundsException{
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }
        Item item = (Item) list[index];
        list[index] = null;
        shiftLeft(index);
        size --;
        return item;
    }


    public int size(){
        return this.size;
    }

    public void shiftLeft(int from){
        for(int i=from; i<list.length-1; i++){
            list[i] = list[i+1];
        }
        list[list.length-1] = null;
    }

    public void increaseSize(){
        Object[] nList = new Object[size + 1];
        for(int i=0; i < this.size; i++){
            nList[i] = list[i];
        }
        list = nList;
    }

    @Override
    public Iterator<Item> iterator() {
        return new MyArrayListIterator();
    }


    private class MyArrayListIterator implements Iterator<Item> {

        private int sInitial = size;
        private int index = 0;


        public Item next(){
            if(!hasNext()) {
                return null;
            }
            else{
                Item item = (Item) list[index];
                index ++;
                return item;
            }
        }

        public void remove(){ throw new UnsupportedOperationException(); }

        public boolean hasNext(){
            return failFastCheck() && index < size;
        }

        public boolean failFastCheck() throws ConcurrentModificationException{
            if(sInitial != size){
                throw new ConcurrentModificationException();
            }
            else{
                return true;
            }
        }
    }

}