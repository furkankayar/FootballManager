

public class MatrixCell{


  public static enum Direction{
    NORTH,
    NORTH_WEST,
  }

  private int rating;
  private Direction direction;


  public Direction getDirection(){
    return this.direction;
  }

  public int getRating(){
    return this.rating;
  }

  public void setDirection(Direction direction){
    this.direction = direction;
  }

  public void setRating(int rating){
    this.rating = rating;
  }

  public void increaseRating(int amount){

    this.rating += amount;
  }

}
