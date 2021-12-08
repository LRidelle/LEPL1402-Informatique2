public class Add extends Node implements Visitable {

    public Add(Visitable l, Visitable r){
        super(l, r);
    }

    @Override
    public int accept(Visitor visitor) {
        return visitor.visit(this);
    }

}