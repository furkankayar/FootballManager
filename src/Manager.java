
import java.util.ArrayList;

public class Manager{

  public int getTotalRating(ArrayList<Footballer> footballers, int n, int k, int budget){

    MatrixCell[][] matrix = new MatrixCell[n*k+1][budget + 1];
    for(int i = 0; i < matrix.length ; i++)
      for(int j = 0 ; j < matrix[0].length ; j++)
        matrix[i][j] = new MatrixCell();

    for(int i = 1; i < matrix.length ; i++){
      for(int j = 1 ; j < matrix[0].length ; j++){
        if(footballers.get(i-1).getPrice() > j){
          MatrixCell.copy(matrix[i][j], matrix[i-1][j]);
        }
        else if(footballers.get(i-1).getPrice() <= j){

          MatrixCell max = max(matrix[i-1][j], footballers.get(i-1).getRating(), matrix[i-footballers.get(i-1).getOrder()][j - footballers.get(i-1).getPrice()]);
          MatrixCell.copy(matrix[i][j], max);
          if(footballers.get(i-1).getRating() + matrix[i-footballers.get(i-1).getOrder()][j - footballers.get(i-1).getPrice()].getTotalRating() >= matrix[i-1][j].getTotalRating()){
            matrix[i][j].add(footballers.get(i-1));
          }
        }
      }
    }
    //printMatrix(matrix);
    for(Footballer footballer : matrix[matrix.length - 1][matrix[0].length -1].getFootballers()) System.out.println(footballer);
    System.out.println("Total Price: " + matrix[matrix.length - 1][matrix[0].length -1].getTotalPrice());
    return matrix[matrix.length - 1][matrix[0].length -1].getTotalRating();
  }


  public MatrixCell max(MatrixCell a, int b, MatrixCell c){
    if(a.getTotalRating() > b + c.getTotalRating())
      return a;
    return c;
  }

  public void printMatrix(MatrixCell[][] matrix){

    for(int i = 0 ; i < matrix.length ; i++){
      for(int j = 0 ; j < matrix[0].length ; j++){
        System.out.print(matrix[i][j].getTotalRating() + "\t");
      }
      System.out.println();
    }
  }
}
