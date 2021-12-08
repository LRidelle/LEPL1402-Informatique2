import java.util.function.Supplier;
import java.util.stream.Stream;

public class Node {

    public int val;
    public Node left;
    public Node right;

    public Node(int val){
        this.val = val;
    }

    public boolean isLeaf(){
        return this.left == null && this.right == null;
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof Node){
            Node N = (Node) o;
            if(this.val == N.val){
                if(this.isLeaf() && N.isLeaf()){
                    return true;
                }
                else if(this.left != null && N.left != null && this.right == null && N.right == null){
                    return this.left.equals(N.left);
                }
                else if(this.right != null && N.right != null && this.left == null && N.left == null){
                    return this.right.equals(N.right);
                }
                else{
                    return this.left.equals(N.left) && this.right.equals(N.right);
                }
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }

    public static void main(String[] args){

        Supplier<Integer> rnd = () -> (int) (Math.random() * 100);

        Integer [] seeds = Stream.generate(rnd).limit(7).toArray(Integer[]::new);

        Node root = new Node(seeds[0]);
        root.left = new Node(seeds[1]);
        root.right = new Node(seeds[2]);
        root.left.left = new Node(seeds[3]);
        root.left.right = new Node(seeds[4]);
        root.right.left = new Node(seeds[5]);
        root.right.right = new Node(seeds[6]);

        Tree tree = new Tree(root);

        Node root2 = new Node(seeds[0]);
        root2.left = new Node(seeds[1]);
        root2.right = new Node(seeds[2]);
        root2.left.left = new Node(seeds[3]);
        root2.left.right = new Node(seeds[4]);
        root2.right.left = new Node(seeds[5]);
        root2.right.right = new Node(seeds[5]);

        Tree tree2 = new Tree(root2);

        System.out.println(tree.equals(tree2));
    }

}