import src.Array2DBuilderInterface;

public class MyBuilder implements Array2DBuilderInterface {

  public int[][] build_from(String s) {
    String[] sp1 = s.split("\n");
    int[][] a = new int[sp1.length][];
    for(int i=0; i<sp1.length; i++){
      String[] sp2 = sp1[i].trim().split(" ");
      int[] array = new int[sp2.length];
      for(int j=0; j<sp2.length; j++){
        array[j] = Integer.parseInt(sp2[j]);
      }
      a[i] = array;
    }
    return a;
  }

  public int sum(int[][] array) {
    int sum = 0;
    for(int i=0; i<array.length; i++){
      for(int j=0; j<array[i].length; j++){
        sum += array[i][j];
      }
    }
    return sum;
  }

  public int[][] transpose(int[][] matrix) {
    int[][] transp = new int[matrix[0].length][matrix.length];
    for(int i=0; i<matrix.length; i++){
      for(int j=0; j<matrix[0].length; j++){
        transp[j][i] = matrix[i][j];
      }
    }
    return transp;
  }

  public int[][] product(int[][] matrix1, int[][] matrix2) {
    int[][] sum = new int[matrix1.length][matrix2[0].length];
    for(int i=0; i<matrix.length; i++){
      for(int j=0; j<; j++){

      }
    }
  }
}
