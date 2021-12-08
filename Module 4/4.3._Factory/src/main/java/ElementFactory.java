public class ElementFactory extends Factory {

    private static final ElementFactory myelem = new ElementFactory();

    private ElementFactory(){}

    public static ElementFactory getInstance(){
        return myelem;
    }

    LevelComponent getElement(char c) throws IllegalArgumentException{
        if(c=='D')
            return new Door();
        if(c=='#')
            return new Wall();
        if(c=='-')
            return new Floor();
        if(c=='K')
            return new Key();
        throw new IllegalArgumentException();
    }
}
