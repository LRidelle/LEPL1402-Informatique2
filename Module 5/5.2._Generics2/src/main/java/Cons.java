import java.util.function.Predicate;
import java.util.function.Function;

public class Cons<E> {

    // the item inside this list node
    public E v;

    // The next element, null if nothing
    public Cons<E> next;

    // Constructor
    public Cons(E v, Cons<E> next) {
        this.v = v;
        this.next = next;
    }

    // creates a new Cons that applies function, 'function' on all elements
    public <R> Cons <R> map(Function <E,R> function) {
        E mappedv = (E) function.apply(this.v);
        Cons mapped = new Cons(mappedv, null);
        if(this.next != null){
            mapped.next = this.next.map(function);
        }
        return mapped;
    }

    // creates a new Cons with all elements that matches with predicate, 'predicate'
    public Cons <E> filter(Predicate <E> predicate) {
        Cons filtered = new Cons(0, null);
        Cons runner = this;
        while(runner != null){
            if(predicate.test((E) runner.v)){
                filtered.v = runner.v;
                if(runner.next != null){
                    filtered.next = runner.next.filter(predicate);
                }
                return filtered;
            }
            runner = runner.next;
        }
        return null;
    }
}
