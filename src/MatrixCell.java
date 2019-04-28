
import java.util.ArrayList;


public class MatrixCell{

  private ArrayList<Footballer> footballers;
  private int totalPrice;
  private int totalRating;

  public MatrixCell(){

    footballers = new ArrayList<Footballer>();
    totalPrice = 0;
    totalRating = 0;
  }

  public void add(Footballer footballer){

    this.totalPrice += footballer.getPrice();
    this.totalRating += footballer.getRating();
    this.footballers.add(footballer);
  }

  public ArrayList<Footballer> getFootballers(){
    return this.footballers;
  }

  public int getTotalPrice(){
    return this.totalPrice;
  }

  public int getTotalRating(){
    return this.totalRating;
  }

  public void setFootballers(ArrayList<Footballer> footballers){
    this.footballers = footballers;
  }

  public void setTotalPrice(int totalPrice){
    this.totalPrice = totalPrice;
  }

  public void setTotalRating(int totalRating){
    this.totalRating = totalRating;
  }

  @SuppressWarnings("unchecked")
  public static void copy(MatrixCell matrixCell1, MatrixCell matrixCell2){

    matrixCell1.setFootballers((ArrayList<Footballer>)matrixCell2.getFootballers().clone());
    matrixCell1.setTotalPrice(matrixCell2.getTotalPrice());
    matrixCell1.setTotalRating(matrixCell2.getTotalRating());
  }
}
