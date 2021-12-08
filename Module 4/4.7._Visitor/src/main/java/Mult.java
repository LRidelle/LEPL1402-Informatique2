public class Mult extends Node implements Visitable {

    public Mult(Visitable l, Visitable r){
        super(l, r);
    }

    @Override
    public int accept(Visitor visitor) {
        return visitor.visit(this);
    }

}