This program uses a non-directed graph data structure to represent a simple social network program. The network information is stored in two text files: friend.txt and
index.txt. The index file lists the number of people in the network along with their name and associated index. The friend.txt file lists the pairs of friends. 

The program reads this information and displays a simple text menu interface for performing the following actions.
1) View a list of a given person's friends
2) View a list of a given person's friends of friends (uses depth-first search)
3) View friends in common of two given people
4) Delete a person from the network
5) View a ranked list of the most popular people in the network

Simple error checking is performed for user input. The NetworkGraph class is an extension from the Graph class (supplied for the assignment). This project utilises 
object-oriented programming principles of inheritance.