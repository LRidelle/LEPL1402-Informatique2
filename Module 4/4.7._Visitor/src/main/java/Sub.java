public class Sub extends Node implements Visitable {

    public Sub(Visitable l, Visitable r){
        super(l, r);
    }

    @Override
    public int accept(Visitor visitor) {
        return visitor.visit(this);
    }

}