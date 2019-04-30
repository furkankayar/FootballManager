
import java.util.ArrayList;

public class Manager{



  public void getTotalRating(ArrayList<Footballer> footballers, int n, int k, int budget){

    MatrixCell[][] matrix = new MatrixCell[n*k+1][budget + 1];
    for(int i = 0; i < matrix.length ; i++)
      for(int j = 0 ; j < matrix[0].length ; j++)
        matrix[i][j] = new MatrixCell();

    for(int i = 1; i < matrix.length ; i++){
      for(int j = 1 ; j < matrix[0].length ; j++){
        if(footballers.get(i-1).getPrice() > j){
          matrix[i][j].setRating(matrix[i-1][j].getRating());
          matrix[i][j].setDirection(MatrixCell.Direction.NORTH);
        }
        else if(footballers.get(i-1).getPrice() <= j){
          matrix[i][j].setRating(max(matrix[i-1][j].getRating(), footballers.get(i-1).getRating() + matrix[i-footballers.get(i-1).getOrder()][j - footballers.get(i-1).getPrice()].getRating()));
          if(footballers.get(i-1).getRating() + matrix[i-footballers.get(i-1).getOrder()][j - footballers.get(i-1).getPrice()].getRating() >= matrix[i-1][j].getRating()){
            matrix[i][j].setDirection(MatrixCell.Direction.NORTH_WEST);
          }
          else{
            matrix[i][j].setDirection(MatrixCell.Direction.NORTH);
          }
        }
      }
    }

    printPlayers(footballers, matrix, matrix.length - 1, matrix[0].length - 1, 0, 0);
  }

  public void printPlayers(ArrayList<Footballer> footballers, MatrixCell[][] matrix, int x, int y, int totalPrice, int totalRating){

    if(matrix[x][y].getRating() == 0){
      System.out.println("\nTotal price : " + totalPrice);
      System.out.println("Total rating: " + totalRating);
      return;
    }

    if(matrix[x][y].getDirection() == MatrixCell.Direction.NORTH_WEST){
      Footballer footballer = footballers.get(x-1);
      System.out.println(footballer);
      printPlayers(footballers, matrix, x - footballer.getOrder(), y - footballer.getPrice(), totalPrice + footballer.getPrice(), totalRating + footballer.getRating());
    }
    else if(matrix[x][y].getDirection() == MatrixCell.Direction.NORTH){
      printPlayers(footballers, matrix, x -1, y, totalPrice, totalRating);
    }
  }


  public int max(int a, int b){
    if(a > b)
      return a;
    return b;
  }
}
