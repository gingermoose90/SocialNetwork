package main;

import java.util.*;
import java.io.*;

public class SocialNetworkClass {
  /** declare main method variables */
  private static File file;
  private static Scanner scanner;
  private static String path;
  private static int n; // number of vertices
  private static int v; // vertex index
  private static String label; // vertex label
  private static int m; // number of edges
  private static int n1; // edge end-point 1
  private static int n2; // edge end-point 2
  private static NetworkGraph graph;
  
  
  public static void main(String[] args) throws FileNotFoundException, IOException {
    /** 
     * Task 1
     * read the index.txt file to generate <CODE>NetworkGraph</CODE>
     * and add vertex labels
     */
    System.out.println("Starting Social Network Program...");
    System.out.println("Please ensure input files are located in current working directory");
    readIndexFile();
    readFriendsFile();
    
    // menu loop
    int choice;
    scanner = new Scanner(System.in);
    do {
      System.out.println("SOCIAL NETWORK MENU\n");
      System.out.println("Please select an option...");
      System.out.println("1. View Friends List");
      System.out.println("2. View Friends of Friends List");
      System.out.println("3. View Friends in Common List");
      System.out.println("4. Delete a Friend from Network");
      System.out.println("5. View Most Popular List");
      System.out.println("6. Enter new Index and Friends files");
      System.out.println("7. Exit Program");
      System.out.print("Please enter the integer number of the desired option: ");
      choice = scanner.nextInt();
      
      switch(choice) {
        case 1:
          System.out.print("\nWho's friends would you like to see?: ");
          String name = scanner.next();
          graph.friendList(name);
          break;
        case 2:
          System.out.print("\nWho's friends of friends would you like to see?: ");
          name = scanner.next();
          graph.fofList(name);
          break;
        case 3:
          System.out.print("\nPlease enter first name: ");
          String firstName = scanner.next();
          System.out.print("Please enter second name: ");
          String secondName = scanner.next();
          graph.commonFriends(firstName, secondName);
          break;
        case 4:
          System.out.print("\nAre you sure you wish to delete someone from the network? (Y/N): ");
          String input = scanner.next();
          if (!input.toUpperCase().equals("Y")) {
            break;
          }
          System.out.print("Please enter a name to delete: ");
          name = scanner.next();
          graph.removeFriend(name);
          break;
        case 5: 
          graph.mostPopular();
          break;
        case 6:
          readIndexFile();
          readFriendsFile();
          break;
        case 7:
          System.out.println("Exiting Program...");
      }
    } while (choice != 7);
    
    scanner.close();
    
  } /** end main method class */
  
 
  public static void readIndexFile() {
    path = System.getProperty("user.dir") + "/src/main/index.txt";
    System.out.println("Reading index file...\n");
    
    try {
      file = new File(path);
      scanner = new Scanner(file);
      n = scanner.nextInt(); // number of vertices
      graph = new NetworkGraph(n); // create NetworkGraph object
      for (int i = 0; i < n; i++) {
        v = scanner.nextInt(); // vertex index
        label = scanner.next(); // vertex label
        graph.setLabel(v, label); // add to NetworkGraph
      }
      
      // test label input is correct
      for (int i = 0; i < graph.size(); i++) {
        System.out.println(i + " " + graph.getLabel(i));
      }
      System.out.println("");
      
    } catch (NullPointerException e) {
      System.err.println("Invalid path name.");
      e.printStackTrace();
    } catch (IOException e) {
      System.err.println("Could not read input.");
      e.printStackTrace();
    } catch (NoSuchElementException e) { // occurs if number of vertices declared is not equal to actual number
      System.err.println("Number of declared vertices greater than number of vertices in file.");
      e.printStackTrace();
    }
  }
  
  public static void readFriendsFile() {
    path = System.getProperty("user.dir") + "/src/main/friend.txt";
    System.out.println("Reading friends file...\n");
    
    try {
      file = new File(path);
      scanner = new Scanner(file);
      m = scanner.nextInt(); // number of edges
      while (scanner.hasNext()) {
        n1 = scanner.nextInt(); // first end-point vertex
        n2 = scanner.nextInt(); // second end-point vertex
        graph.addEdge(n1, n2); // add to NetworkGraph
      }
      
      // check number of declared edges is equal to actual number
      if (m != graph.numEdges()) { 
        throw new NoSuchElementException();
      }
      
    } catch (NullPointerException e) {
      System.err.println("Inavlid path name.");
      e.printStackTrace();
    } catch (IOException e) {
      System.err.println("Could not read input.");
      e.printStackTrace();
    } catch (ArrayIndexOutOfBoundsException e) {
      System.err.println("Number of declared vertices less than number of vertices in file");
      e.printStackTrace();
    } catch (NoSuchElementException e) { // occurs if number of declared vertices not equal to actual number
      System.err.println("Number of edges declared not equal to number of edges in file");
      e.printStackTrace();
    }
  }
  
  
}