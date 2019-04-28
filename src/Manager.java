
import java.util.ArrayList;

public class Manager{

  public int getTotalRating(ArrayList<Footballer> footballers, int n, int k, int bullet){

    int[][] matrix = new int[n*k+1][bullet + 1];

    for(int i = 1; i < matrix.length ; i++){
      for(int j = 1 ; j < matrix[0].length ; j++){
        if(footballers.get(i-1).getPrice() > j){
          matrix[i][j] = matrix[i-1][j];
        }
        else if(footballers.get(i-1).getPrice() <= j){
          matrix[i][j] = max(matrix[i-1][j], footballers.get(i-1).getRating() + matrix[i-footballers.get(i-1).getOrder()][j - footballers.get(i-1).getPrice()]);
        }
      }
    }
    //printMatrix(matrix);
    return matrix[matrix.length - 1][matrix[0].length -1];
  }


  public int max(int a, int b){
    if(a > b)
      return a;
    return b;
  }

  public void printMatrix(int[][] matrix){

    for(int i = 0 ; i < matrix.length ; i++){
      for(int j = 0 ; j < matrix[0].length ; j++){
        System.out.print(matrix[i][j] + " ");
      }
      System.out.println();
    }
  }
}
