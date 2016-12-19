CS 5V81.012 : Implementation of Data Structures and Algorithms
Mandatory Project- nxr153230

Contents of the folder
**************************
SkipList
Study.pdf
README.txt

SkipList/src contains
	SkipListImpl.java			:	Class that implements Skip List Interface
	SkipListDriver.java			:	Driver program to check the functionality of skip list 
	SkipList.java				:	Skip list Interface
	TreeMapStudy.java			:	Class to study tree map running time.
		
Instructions for Compiling
******************************

Input graph file is to be given as an command line argument.
compile		: java SkipListDriver.java
run		: java SkipListDriver <filename> 

Alternatively,
run		:java SkipListDriver
Input format	:
	To add an element 					: Add <element to be added>
	To remove an element					: Remove <element to be removed>
	To find first element 					: First
	To find the last element				: Last
	To find the floor of a value from the list		: Floor <value>
	To find the ceil of a value from the list 		: Ceiling <value>
	To retrieve the element of given index		: FindIndex <index_value>
	To check if the element is present in the list	: Contains <value>

***********************************************************************************************************************************************************************************
 compile	 :  javac SkipListDriver.java
 run	 	 :  java SkipListDriver a8.txt

***********************************************************************************************************************************************************************************
Output		:	130122
			Time: 2366 msec.
			Memory: 116 MB / 616 MB.


***********************************************************************************************************************************************************************************
