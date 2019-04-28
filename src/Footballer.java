
public class Footballer{

  private String name;
  private int rating;
  private int price;
  private int position;
  private int order;

  public Footballer(String name, int position, int rating, int price, int order){

    this.name = name;
    this.rating = rating;
    this.price = price;
    this.position = position;
    this.order = order;
  }

  public String getName(){
    return this.name;
  }

  public void setNamer(String name){
    this.name = name;
  }

  public int getRating(){
    return this.rating;
  }

  public void setRating(int rating){
    this.rating = rating;
  }

  public int getPrice(){
    return this.price;
  }

  public void setPrice(int price){
    this.price = price;
  }

  public int getPosition(){
    return this.position;
  }

  public void setPosition(int position){
    this.position = position;
  }

  public int getOrder(){
    return this.order;
  }

  public void setOrder(int order){
    this.order = order;
  }

  @Override
  public String toString(){

    return this.order + " " + this.name + " " + this.position + " " + this.rating + " " + this.price;
  }
}
