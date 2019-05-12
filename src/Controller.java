
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.OutOfMemoryError;


public class Controller{

  private ArrayList<Footballer> footballers;
  private Manager manager;
  private Scanner scan;
  private int x;
  private int n;
  private int k;


  private final String FILENAME = "input.txt";

  public Controller(){

    this.footballers = new ArrayList<Footballer>();
    manager = new Manager();
    scan = new Scanner(System.in);
    readInputFile(FILENAME);
    sortFootballers();
    run();
  }

  public void run(){

    int inputNumber = 3;

    while(true){

    for(int i = 0 ; i != inputNumber ; ){
      try{
        if(i == 0){
          System.out.print("Enter the amount to spend(X): ");
          x = scan.nextInt();
          if(x < 0) throw new InputMismatchException();
          i++;
        }
        else if(i == 1){
          System.out.print("Enter the number of the positions (N): ");
          n = scan.nextInt();
          if(n < 0) throw new InputMismatchException();
          i++;
        }
        else if(i == 2){
          System.out.print("Enter the number of the available players for each position (K): ");
          k = scan.nextInt();
          if(k < 0) throw new InputMismatchException();
          i++;
        }
      }
      catch(InputMismatchException ex){
        System.out.println("Wrong value entered! ");
        scan = new Scanner(System.in);
      }
    }

    ArrayList<Footballer> chosenPartOfFootballers = getPartOfFootballers(n, k);
    System.out.println("\033[1m\nDP results:\033[0m");
    try{
      manager.createBestTeamDynamic(chosenPartOfFootballers, n, k, x);
    }
    catch(OutOfMemoryError err){
      System.out.println("Error: Heap space has been exceeded! Please set up your heap size from virtual machine arguments.");
    }
    System.out.println("\033[0;1m\nGreedy Approach results:\033[0m");
    manager.createBestTeamGreedy(chosenPartOfFootballers, n, x);

    System.out.print("\nPress \033[1mENTER\033[0m for a new query.");
    scan = new Scanner(System.in);
    scan.nextLine();
    System.out.println("\n\n");
    }
  }

  public void sortFootballers(){

    Collections.sort(footballers, new Comparator<Footballer>(){
      @Override
      public int compare(Footballer f1, Footballer f2){
        return f1.getPosition() >= f2.getPosition() ? 1 : -1;
      }
    });
    int lastPosition = footballers.get(0).getPosition();
    int order = 1;
    for(Footballer footballer : footballers){
      if(footballer.getPosition() > lastPosition){
        order = 1;
        lastPosition = footballer.getPosition();
      }
      footballer.setOrder(order++);
    }
  }

  public ArrayList<Footballer> getPartOfFootballers(int n, int k){

    ArrayList<Footballer> chosenFootballers = new ArrayList<Footballer>();
    for(Footballer footballer : footballers){
      if(footballer.getPosition() <= n){
        if(footballer.getOrder() <= k)
          chosenFootballers.add(footballer);
      }
      else
        break;
    }

    return chosenFootballers;
  }


  public void readInputFile(String fileName){

    try{
      BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
      String row = null;
      while((row = br.readLine()) != null){
        String[] properties = row.split(",");
        footballers.add(new Footballer(properties[0], Integer.valueOf(properties[1]), Integer.valueOf(properties[2]), Integer.valueOf(properties[3])));
      }
      br.close();
    }
    catch(FileNotFoundException ex){
      System.out.println("Error: " + fileName + " has not been found!");
    }
    catch(IOException ex){
      System.out.println("Error: " + ex.getMessage());
    }
    catch(ArrayIndexOutOfBoundsException ex){
      System.out.println("Error: Input file has missing fields!");
    }
  }


  public static void main(String[] args){

    new Controller();
  }
}
