public class Leaf implements Visitable {

    public int val;

    public Leaf(int val){
        this.val = val;
    }

    @Override
    public int accept(Visitor visitor) {
        return getValue();
    }

    public int getValue() {
        return this.val;
    }
}