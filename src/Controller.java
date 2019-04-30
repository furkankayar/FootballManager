
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Controller{

  private ArrayList<Footballer> footballers;

  public Controller(){

    this.footballers = new ArrayList<Footballer>();
    try{
      readInputFile("input.txt", 10, 10);
    }
    catch(Exception ex){}


    new Manager().getTotalRating(footballers, 10, 10, 50000);
  }


  public void readInputFile(String fileName, int n, int k) throws FileNotFoundException, IOException, ArrayIndexOutOfBoundsException{

    BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
    String row = null;
    int order = 1;
    int lastPosition = 1;
    while((row = br.readLine()) != null){
      String[] properties = row.split(",");
      if(lastPosition <= Integer.valueOf(properties[1])){
        order = 1;
        lastPosition += 1;
      }
      else order++;
      if(order <= k && lastPosition <= n + 1)
        footballers.add(new Footballer(properties[0], Integer.valueOf(properties[1]), Integer.valueOf(properties[2]), Integer.valueOf(properties[3]), order));
    }
    br.close();
  }


  public static void main(String[] args){

    new Controller();
  }
}
