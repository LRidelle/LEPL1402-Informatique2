public class Div extends Node implements Visitable {


    public Div(Visitable l, Visitable r){
        super(l, r);
    }

    @Override
    public int accept(Visitor visitor) {
        return visitor.visit(this);
    }
}