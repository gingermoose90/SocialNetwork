package main;

import java.util.Scanner;

public class NetworkGraph extends Graph {
  private int numEdges;
  
  /**
   * Initialise a <CODE>NetworkGraph</CODE> with <CODE>n</CODE> vertices,
   * no edges, and null labels, and numEdges = 0.
   * @param <CODE>n</CODE>  
   *   The number of vertices for this <CODE>NetworkGraph</CODE>.
   *<dt><b>Precondition:</b><dd>
   *   <CODE>n</CODE> is nonnegative.
   *<dt><b>Postcondition:>/b><dd>
   *   The <CODE>NetworkGraph</CODE> has <CODE>n</CODE> vertices,
   *   numbered <CODE>0</CODE> to <CODE>n-1</CODE>. It has no edges, 
   *   numEdges = 0, and all vertex labels are null.
   *@exception NegativeArraySizeException
   *   Indicates that <CODE>n</CODE> is negative.
   */
  public NetworkGraph(int n) {
    super(n);
    numEdges = 0;
  }
  
  
  /** 
   * Add a new edge to this <CODE>NetworkGraph</CODE>.
   * Overrides inherited method for implementation in an undirected graph.
   * @param <CODE>source</CODE>
   *   one of the end-points of the edge
   * @param <CODE>target</CODE> 
   *   the other end-point of the edge
   * <dt><b>Precondition:</b><dd>
   *   Both <CODE>source</CODE> and <CODE>target</CODE> are nonnegative
   *   and less than <CODE>size()</CODE>
   * <dt><b>Postcondition:</b><dd>
   *   This <CODE>NetworkGraph</CODE> the boolean array edges[][] has 
   *   edges[source][target] and edges[target][source] equal to true to
   *   represent the undirected edges of the <CODE>NetworkGraph</CODE>.
   * @exception ArrayIndexOutOfBoundsException
   *   Indicates that the <CODE>source</CODE> or <CODE>target</CODE> was
   *   not a valid vertex number
   **/
  @Override
  public void addEdge(int source, int target) {
    super.addEdge(source, target);
    super.addEdge(target, source);
    numEdges++;
  }
  
  
  /**
   * Remove an edge from this <CODE>NetworkGraph</CODE>.
   * @param <CODE>source</CODE>
   *   The vertex number of one of the removed edge end-points.
   * @param <CODE>target</CODE>
   *   The vertex number of one of the other removed edge end-points.
   * <dt><b>Precondition:</b><dd>
   *   Both <CODE>source</CODE> and <CODE>target</CODE> are nonnegative
   *   and less than <CODE>size()</CODE>.
   * <dt><b>Postcondition:</b><dd>
   *   This <CODE>NetworkGraph</CODE> has all original edges minus the edge
   *   specified by the end-points <CODE>source</CODE> and 
   *   <CODE>target</CODE>. If the edge was not present, then 
   *   <CODE>NetworkGraph</CODE> is unchanged.
   * @exception ArrayIndexOutOfBoundsException
   *   Indicates that either the <CODE>source</CODE> or <CODE>target</CODE>
   *   variables are not valid vertex numbers.
   */
  @Override
  public void removeEdge(int source, int target) {
    super.removeEdge(source, target);
    super.removeEdge(target, source);
  }
  
  
  /**
   * Removes a vertex from the network, including all associated edges.
   * @param <CODE>label</CODE>
   *   A String object for the vertex label.
   * <dt><b>Precondition:</b><dd>
   *   The <CODE>NetworkGraph</CODE> contains the given vertex.
   * <dt><b>Postcondition:</b><dd>
   *   All entries in the boolean array <CODE>edges</CODE> which have 
   *   the vertex corresponding to <CODE>label</CODE> are set to null. 
   *   The entry for the vertex in the array <CODE>labels</CODE> is set
   *   to  (OR NULL???) the empty String """".
   *@throws NullPointerException
   *   Indicates the <CODE>NetworkGraph</CODE> has not been initialised.
   */
  public void removeFriend(String label) {
    int idx = getVertex(label);
    if (idx == -1) {
      System.err.println(label + " is not in the network.");
      return;
    }
    
    // remove all edges associated with this vertex
    int[] friends = this.neighbors(idx);
    for (int i : friends) {
      this.removeEdge(i, idx);
    }
    
    // set vertex to null
    setLabel(idx, null);
    
    // check results
    System.out.println("Remaining people in the network...");
    for (int i = 0; i < this.size(); i++) {
      System.out.println(" ");
    }
    System.out.println("Checking edges matrix...");
    for (int i = 0; i < this.size(); i++) {
      for (int j = 0; j < this.size(); j++) {
        System.out.print(isEdge(i, j) + " ");
      }
      System.out.print("\n");
    }
  }
  
  
  /** 
   * Utilises the DFS search method of superclass to return list 
   * of friends and friends of friends.
   * @param <CODE>label</CODE>
   *   A nonnull <CODE>label</CODE> of a vertex.
   * <dt><b>Precondition:</b><dd>
   *   <CODE>label</CODE> is a String object.
   * <dt><b>Postcondition:</b><dd>
   *   A depth-first search of the network graph has been conducted, starting
   *   at the index of the corresponding label. Each vertex visited has its label 
   *   printed using the. 
   * @throws NullPointerException
   *   Indicates the <CODE>NetworkGraph</CODE> has not been initialised.
   */ 
  public void fofList(String label) {
    if (this.size() == 0) {
      System.err.println("This network is empty.");
      return;
    }
    
    int idx = getVertex(label);
    if (idx == -1) {
      System.err.println("This name is not in the network.");
      return;
    }
    super.depthFirstPrint(this, idx);
  }
  
  
  /**
   * A list for printing out friends. A <CODE>label</CODE> is passed as an 
   * argument to represent a person in the <CODE>GraphNetwork</CODE>. A list
   * of that person's friends is retrieved. A friends is defined as an 
   * adjacent vertex.
   * @param <CODE>label</CODE>
   *   A String object for the vertex <CODE>label</CODE>.
   * <dt><b>Precondition:</b><dd>
   *   The <CODE>NetworkGraph</CODE> is populated with at least one vertex.
   * <dt><b>Postcondition:</b><dd> 
   *   The corresponding index for the vertex is retrieved. The inherited method
   *   neighbors is called to return a list of friends.
   * @throws NullPointerException
   *   Indicates the <CODE>NetworkGraph</CODE> has not been initialised.
   */
  public void friendList(String label) {
    if (this.size() == 0) {
      System.err.println("This network is empty");
      return;
    }
    // get index of vertex label
    int idx = getVertex(label);
    if (idx == -1) {
      System.err.println("This name is not in the network.");
      return;
    }
    int[] friends = super.neighbors(idx);
    for (int i : friends) {
      System.out.println(getLabel(i));
    }
  }
  
  
  /**
   *A method for printing a list of common friends for two given people
   *in the <CODE>NetworkGraph</CODE>.
   *@param <CODE>l1</CODE>
   *   A String label for a vertex in the <CODE>NetworkGraph</CODE>.
   *@param <CODE>l2</CODE>
   *   A String label for a vertex in the <CODE>NetworkGraph</CODE>.
   *<b><dt>Precondition:</b><dd>
   *   The <CODE>NetworkGraph</CODE> has been initialised and contains at least
   *   two vertices; <CODE>l1</CODE> and <CODE>l2</CODE>
   *<b><dt>Postcondition:</b><dd>
   *   A list of adjacent vertices for each given vertex is generated and then
   *   inspected for any equivalent entries. An equivalent entry indicates a
   *   friend in common. A list of all common friends is then printed out
   *   to the system console.
   *@throws NullPointerException
   *   Indicates the <CODE>NetworkGraph</CODE> has not been initialised.
   */
  public void commonFriends(String l1, String l2) {
    int idx1;
    int idx2;
    int count;
    int[] answer;
    
    // test if graph is empty
    if (this.size() == 0) {
      System.err.println("This network is empty.");
      return;
    }
    
    // obtain indices of vertex labels
    idx1 = getVertex(l1);
    idx2 = getVertex(l2);
    
    /** to delete */
    System.out.println("index 1 is " + idx1);
    System.out.println("index 2 is " + idx2);
    
    // check vertex indices are non-negative
    if (idx1 == -1 || idx2 == -1) {
      System.err.println("One or more of the names are not in the network.");
      return;
    }
    
    // count how many friends in common
    count = 0;
    int[] list1 = neighbors(idx1);
    int[] list2 = neighbors(idx2);
    for (int i = 0; i < list1.length; i++) {
      for (int j = 0; j < list2.length; j++) {
        if (list1[i] == list2[j]) {
          count++;
        }
      }
    }
    System.out.println("They have " + count + " friends in common");
    
    // allocate the array for the answer
    answer = new int[count];
    
    // fill the array for the answer
    count = 0;
    for (int i = 0; i < list1.length; i++) {
      for (int j = 0; j < list2.length; j++) {
        if (list1[i] == list2[j]) {
          answer[count++] = list1[i];
        }
      }
    }
    
    // print the result
    for (int i = 0; i < answer.length; i++) {
      System.out.println(getLabel(answer[i]));
    }
  }
  
  
  /**
   * Method for sorting vertices in order of popularity, i.e. number of neighbours.
   * The method first creates an array counting the number of neighbours of each vertex.
   * Array indices in <CODE>count</CODE> correspond to vertex indices in the 
   * <CODE>labels</CODE> array. Then, two arrays; <CODE>answer</CODE> and 
   * <CODE>names</CODE> are populated with vertex labels and neighbour counts in 
   * descending order. Corresponding labels and counts are at the same array 
   * index in each array. Once a vertex has been sorted into order in the 
   * <CODE>answer</CODE> and <CODE>names</CODE> arrays, its corresponding field 
   * in the <CODE>count</CODE> array is set to 0, so it will not be counted
   * as a candidate next maximum element in future iterations.
   * <dt><b>Precondition:</b><dd>
   *   The <CODE>NetworkGraph</CODE> has been initialised with one or more 
   *   vertices and edges.
   * <dt><b>Postcondition:</b><dd>
   *   Vertices and their corresponding neighbour counts are printed in 
   *   order from highest to lowest. 
   * @throws NullPointerException
   *   Indicates that the <CODE>NetworkGraph</CODE> has not been filled 
   *   with edges and vertices.
   */
  public void mostPopular() {
    int[] count = new int[this.size()]; // unordered array of friend counts
    int[] answer = new int[this.size()]; // ordered array of friend counts
    String[] names = new String[this.size()]; // ordered array of vertex labels
    
    // find out how many friends each person has
    for (int i = 0; i < this.size(); i++) {
      count[i] = this.neighbors(i).length;
    }
    
    // arrange entries in descending order of popularity
    for (int i = 0; i < count.length; i++) {
      int maxIdx = 0;
      int maxCount = count[maxIdx];
      for (int j = 1; j < count.length; j++) {
        if (count[j] > maxCount) {
          maxCount = count[j];
          maxIdx = j;
        }
      }
      answer[i] = maxCount;
      //System.out.println("max count for itr " + i + " : " + answer[i]);
      names[i] = (String) this.getLabel(maxIdx);
      //System.out.println("label for itr " + i + " : " + names[i]);
      count[maxIdx] = -1;
    }
    
    // print result
    System.out.println("Most popular people in network");
    System.out.println("Name \tNumber of friends");
    for (int i = 0; i < this.size(); i++) {
      System.out.printf("%s: %d\n", names[i], answer[i]);
    }
    System.out.print("\n");
  }
  
  
  /**
   * Query method for the number of edges in <CODE>NetworkGraph</CODE>
   *<dt><b>Precondition</b><dd>:
   *   The <CODE>NetworkGraph</CODE> has been initialised and vertices and
   *   edges have been added.
   *@return positive integer count of edges in <CODE>NetworkGraph</CODE> .
   *@throws NullPointerException:
   *   Indicates no edges have been added to the <CODE>NetworkGraph</CODE>.
   */
  public int numEdges() {
    return numEdges;
  }
  
  
  /**
   *Private utility method for checking a given vertex label is in the
   *<CODE>NetworkGraph</CODE>.
   *<dt><b>Precondition:</b><dd>
   *   The <CODE>NetworkGraph</CODE> has been initialised and the array of
   *   <CODE>labels</CODE> is not empty.
   *@param <CODE>label</CODE>
   *   a String vertex label
   *@return
   *   The return value is an integer. If the vertex exists for the 
   *   corresponding label, the returned integer is the index of that
   *   label in the <CODE>labels</CODE> array. If the vertex does not
   *   exist in the graph, the returned integer value is -1.
   *@throws NullPointerException
   *   Indicates that the <CODE>labels</CODE> has not been initialised 
   *   with values.
   */   
  private int getVertex(String label) {
    int idx = -1;
    for (int i = 0; i < this.size(); i++) {
      if (label.equals(this.getLabel(i))) {
        idx = i;
      }
    }
    return idx; 
  }
  
  
}