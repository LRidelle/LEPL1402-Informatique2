public class VisitableList extends Visitable {

    public VisitableList(Object[] o) {
        this.elements = o;
    }

    @Override
    void accept(Visitor visitor) {
        for (Object o : this.elements) {
            visitor.visit(o);
        }
    }
}
