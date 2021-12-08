public class Tree {

    public Node root;

    public Tree(Node root){
        this.root = root;
    }

    public Tree combineWith(Tree o){
        if(o == null)
            return this;
        if(o == null && this.root == null)
            return null;
        if(o.root == null)
            return this;
        if(this.root == null)
            return o;
        Node nNode = nodeSum(this.root, o.root);
        Tree nTree = new Tree(nNode);
        return nTree;
    }

    private Node nodeSum(Node a, Node b){
        if(a==null && b==null)
            return null;

        Node nNode = new Node(0);
        if(a==null){
            nNode.val = b.val;
            nNode.left = nodeSum(null, b.left);
            nNode.right = nodeSum(null, b.right);
        }
        else if(b==null){
            nNode.val = a.val;
            nNode.left = nodeSum(a.left, null);
            nNode.right = nodeSum(a.right, null);
        }
        else{
            nNode.val = a.val + b.val;
            nNode.left = nodeSum(a.left, b.left);
            nNode.right = nodeSum(a.right, b.right);
        }
        return nNode;
    }

}