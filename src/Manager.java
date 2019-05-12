
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;
import java.util.Comparator;

public class Manager{



  public void createBestTeamDynamic(ArrayList<Footballer> footballers, int n, int k, int budget){

    MatrixCell[][] matrix = new MatrixCell[footballers.size() + 1][budget + 1];
    for(int i = 0; i < matrix.length ; i++)
      for(int j = 0 ; j < matrix[0].length ; j++)
        matrix[i][j] = new MatrixCell();

    for(int i = 1; i < matrix.length ; i++){

      Footballer footballer = footballers.get(i - 1);
      for(int j = 1 ; j < matrix[0].length ; j++){

        if(footballer.getPrice() > j){
          matrix[i][j].setRating(matrix[i-1][j].getRating());
          matrix[i][j].setDirection(MatrixCell.Direction.NORTH);
        }
        else if(footballer.getPrice() <= j){
          matrix[i][j].setRating(max(matrix[i-1][j].getRating(), footballer.getRating() + matrix[i-footballer.getOrder()][j - footballer.getPrice()].getRating()));
          if(footballer.getRating() + matrix[i-footballer.getOrder()][j - footballer.getPrice()].getRating() >= matrix[i-1][j].getRating()){
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
      System.out.println("Total price : " + totalPrice);
      System.out.println("Total rating: " + totalRating + "\n\nPlayers:");
      return;
    }

    if(matrix[x][y].getDirection() == MatrixCell.Direction.NORTH_WEST){
      Footballer footballer = footballers.get(x-1);
      printPlayers(footballers, matrix, x - footballer.getOrder(), y - footballer.getPrice(), totalPrice + footballer.getPrice(), totalRating + footballer.getRating());
      System.out.println(footballer);
    }
    else if(matrix[x][y].getDirection() == MatrixCell.Direction.NORTH){
      printPlayers(footballers, matrix, x -1, y, totalPrice, totalRating);
    }
  }


  public void createBestTeamGreedy(ArrayList<Footballer> footballers, int n, int budget){

    Collections.sort(footballers, new Comparator<Footballer>(){
      @Override
      public int compare(Footballer f1, Footballer f2){
        return f1.compareTo(f2);
      }
    });

    ArrayList<Footballer> chosenFootballers = new ArrayList<Footballer>();
    boolean[] availablePositions = new boolean[n];
    int totalRating = 0;
    int budgetLeft = budget;
    Arrays.fill(availablePositions, true);

    for(Footballer footballer : footballers){
      if(footballer.getPrice() <= budgetLeft && availablePositions[footballer.getPosition() - 1]){
        chosenFootballers.add(footballer);
        budgetLeft -= footballer.getPrice();
        totalRating += footballer.getRating();
        availablePositions[footballer.getPosition() - 1] = false;
      }
    }

    System.out.println("Total price : " + (budget - budgetLeft));
    System.out.println("Total rating: " + totalRating + "\n\nPlayers:");
    for(Footballer footballer : chosenFootballers) System.out.println(footballer);
  }


  public int max(int a, int b){
    if(a > b)
      return a;
    return b;
  }
}
