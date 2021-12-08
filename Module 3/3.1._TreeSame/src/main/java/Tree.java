public class Tree {

    public Node root;

    public Tree(Node root){
        this.root = root;
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof Tree){
            Tree T = (Tree) o;
            if(this.root == null && T.root == null){
                return true;
            }
            else if(this.root != null && T.root == null || this.root == null && T.root != null){
                return false;
            }
            else if(this.root.equals(T.root)){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }

}