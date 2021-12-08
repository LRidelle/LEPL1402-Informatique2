public class Level extends AbstractLevel {

    public Level(String input){
        String[] tabInput = input.split("\n");
        size = tabInput.length;
        components = new LevelComponent[size][size];
        for(int i =0; i<size; i++){
            for(int j=0; j<size; j++){
                components[i][j] = getElement(tabInput[i].charAt(j));
            }
        }
    }

    //Example of level building with this String : String s = "#-K\n-D-\n#-K"
    //Gives : LevelComponent[][] componentsObjects = {
    //                                        {new Wall(), new Floor(), new Key()},
    //                                        {new Floor(), new Door(), new Floor()},
    //                                        {new Wall(), new Floor(), new Key()}}

    @Override
    LevelComponent[][] getComponents(){
        return components;
    }

    @Override
    int getSize() {
        return components.length * components[0].length;
    }

    public LevelComponent getElement(char  c){
        ElementFactory elem = ElementFactory.getInstance();
        return elem.getElement(c);
    }

    @Override
    public String toString() {
        String s = "";
        LevelComponent[][] lvl = getComponents();
        for(int i = 0; i < lvl.length; i++){
            for(int j = 0; j < lvl[i].length; j++){
                s += lvl[i][j].draw();
            }
            s += "\n";
        }
        return s;
    }
}
