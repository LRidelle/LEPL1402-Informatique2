public class Cons {

    // the item inside this list node
    public int v;
    // The next element, null if nothing
    public Cons next;

    // creates a new Cons that applies function f on all elements
    public Cons map(F f) {
        Cons mapped = new Cons(f.apply(this.v), null);
        if(this.next != null){
            mapped.next = this.next.map(f);
        }
        return mapped;
    }

    // creates a new Cons with all elements that matches predicate p
    public Cons filter(P p) {
        Cons filtered = new Cons(0, null);
        Cons runner = this;
        while(runner != null){
            if(p.filter(runner.v)){
                filtered.v = runner.v;
                if(runner.next != null){
                    filtered.next = runner.next.filter(p);
                }
                return filtered;
            }
            runner = runner.next;
        }
        return null;
    }

    // Constructor
    public Cons(int v, Cons next) {
        this.v = v;
        this.next = next;
    }
}