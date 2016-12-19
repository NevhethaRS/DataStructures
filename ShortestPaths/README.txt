CS 5V81.012 : Implementation of Data Structures and Algorithms
Mandatory Project- nxr153230

Contents of the folder
**************************
MP3
Study.pdf
README.txt

SkipList/src contains
	BinaryHeap.java			:	Class that represents binary heap
	DriverPrim1.java		:	Driver for Prim1
	Driver.java			:	Driver for Prim2 and shortest path
	TSP.java			:	Class that performs TSP
	IndexedHeap.java		:	Class that represents Indexed heap
	Index.java			:	Index Interface
	PQ.java				:	Priority Queue interface
	ShortestPath.java		:	Class that computes shortest path
	Timer.java			:	Class that represents timer
	Edge.java			:	Class that represents an edge
	Graph.java			:	Class that represents a graph
	Vertex.java			:	Class that represents a vertex
	MST.java			:	Class that performs Prim1 and Prim2
		
Instructions for Compiling
******************************
———————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
For Prim2 and Shortest Path
****************************
Input graph file is to be given as an command line argument along with the verbose
Verbose should be a multiple of 3 to perform MST and a multiple of 5 to perform Shortest path

compile		: java Driver.java
run		: java Driver <filename> <Verbose> 

Alternatively,
run		:java Driver
Input format	:
	<# of vertices> <# of edges>
	<from_vertex> <to_vertex>
	..
	..
	..
	<Source_Vertex> <destination_vertex>

***********************************************************************************************************************************************************************************
 compile	 :  javac Driver.java
 run	 	 :  java  Driver g1.txt

***********************************************************************************************************************************************************************************
Output		:	MST: 84950
			Distance: 12020
			Time: 4 msec.
			Memory: 1 MB / 123 MB.

***********************************************************************************************************************************************************************************
———————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
For Prim1
*********
Input graph file is to be given as an command line argument

compile		: java DriverPrim1.java
run		:java DriverPrim1 <filename>

Alternatively,
run		:java DriverPrim1
Input format	:
	<# of vertices> <# of edges>
	<from_vertex> <to_vertex>
	..
	..
	..
	<Source_Vertex> <destination_vertex>
***********************************************************************************************************************************************************************************
 compile	 :  javac DriverPrim1.java
 run	 	 :  java  Driver g1.txt

***********************************************************************************************************************************************************************************
Output		:	MST: 84950
			Time: 3 msec.
			Memory: 1 MB / 123 MB.

***********************************************************************************************************************************************************************************
———————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
For TSP
*******
Input graph file is to be given as an command line argument

compile		: java TSP.java
run		:java TSP <filename>

Alternatively,
run		:java TSP
Input format	:
	<# of vertices> <# of edges>
	<from_vertex> <to_vertex>
	..
	..
	..
	<Source_Vertex> <destination_vertex>
***********************************************************************************************************************************************************************************
 compile	 :  javac TSP.java
 run	 	 :  java  TSP 

***********************************************************************************************************************************************************************************
Input		:	4 6
			1 2 10
			2 4 25
			1 4 20
			1 3 15
			4 3 30
			2 3 35
			1 2
Output		:	tour
			1
			2
			4
			3
			1
			Valid tour
***********************************************************************************************************************************************************************************

