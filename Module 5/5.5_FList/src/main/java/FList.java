import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class FList<A> implements Iterable<A> {

    /**
     * Returns an empty FList
     */
    public static <A> FList<A> nil() {
        return (Nil<A>) Nil.INSTANCE;
    }

    /**
     * Creates a new list with a prepended to this list
     */
    public final FList<A> cons(final A a) {
        return new Cons(a, this);
    }

    /**
     * @return the number of elements in the list
     */
    public abstract int length();

    /**
     * @return true if the list is empty, false otherwise
     */
    public abstract boolean isEmpty();

    /**
     * @return the head of the list.
     * Throws NoSuchElementException if the list is empty
     */
    public abstract A head();

    /**
     * @return the tail of the list (i.e. the sublist without the first element of this list)
     * Throws NoSuchElementException if the list is empty
     */
    public abstract FList<A> tail();

    /**
     * Returns a new list with the output of the function f applied to each element of this list
     */
    public abstract <B> FList<B> map(Function<A,B> f);

    /**
     * Creates a new list with only the element that fullfill the predicate f (i.e. f(elem) == true).
     */
    public abstract FList<A> filter(Predicate<A> f);


    public Iterator<A> iterator() {
        return new Iterator<A>() {
            private FList<A> list = FList.this;

            public boolean hasNext() {
                return list.length() > 0;
            }

            public A next() {
                if(list.length() == 0)
                    throw new NoSuchElementException("List is empty");
                A next  = list.head();
                list = list.tail();
                return next;
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }


    private static final class Nil<A> extends FList<A> {

        public static final Nil<Object> INSTANCE = new Nil();

        @Override
        public boolean isEmpty(){
            return true;
        }

        @Override
        public int length(){
            return 0;
        }

        @Override
        public A head(){
            throw new NoSuchElementException();
        }

        @Override
        public FList<A> tail(){
            throw new NoSuchElementException();
        }

        @Override
        public <B> FList<B> map(Function<A,B> f){
            return new Nil<B>();
        }

        @Override
        public FList<A> filter(Predicate<A> p){
            return new Nil<A>();
        }
    }

    private static final class Cons<A> extends FList<A> {

        private A value;
        private FList<A> next;

        public Cons(A value, FList<A> next){
            this.value = value;
            this.next = next;
        }

        @Override
        public boolean isEmpty(){
            return false;  //if empty -> Nil
        }

        @Override
        public int length(){
            int length = 1;
            Cons<A> runner = this;
            while(!runner.next.isEmpty()){
                length++;
                runner = (Cons<A>) runner.next;
            }
            return length;
        }

        @Override
        public A head(){
            return this.value;
        }

        @Override
        public FList<A> tail(){
            return this.next;
        }

        @Override
        public <B> FList<B> map(Function<A,B> f){
            Cons<B> newFlist = new Cons<B>(f.apply(this.value), FList.nil());
            newFlist.next = this.next.map(f);
            return newFlist;
        }

        @Override
        public FList<A> filter(Predicate<A> p){
            Cons<A> newFlist = new Cons<A>(this.value, FList.nil());
            Cons<A> current = this;
            boolean finished = false;
            while(!finished) {
                if (p.test(current.value)) {
                    newFlist.value = current.value;
                    if (!current.next.isEmpty()) {
                        newFlist.next = current.next.filter(p);
                    }
                    return newFlist;
                } else if (!current.next.isEmpty()) {
                    current = (Cons<A>) current.next;
                } else {
                    finished = true;
                }
            }
            return FList.nil();
        }
    }

}