public class Fibonacci {


    public static int fiboRecursive(int index){
        if(index <= 1){
            return index;
        }
        return fiboRecursive(index-2) + fiboRecursive(index-1);
    }


    public static int fiboIterative(int index){
        if(index <= 1){
            return index;
        }
        int f = 0;
        int fn = 1;
        for(int i=2; i<=index; i++){
            int val = fn;
            fn = val + f;
            f = val;
        }
        return fn;
    }
}
