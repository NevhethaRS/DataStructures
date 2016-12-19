//=============================================================================================================================================//
//	  							*** CircularLinkedList:Class that represents a Circular Linked List ***    																								   //
//=============================================================================================================================================//
/*
 	 *  @dateCreated:		-September-25-2016
	 *  @dateLastModified:	-September-29-1016
	 *  @author: 			-Nevhetha
 */
//=============================================================================================================================================//
import java.util.Iterator;

public class CircularLinkedList<T> implements Iterable<T> {
	    
    public class Entry {
     /*----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
   	  *								*** Entry:Class that represents a node of the list ***    																								   //
   	 /*----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
   		 *  @dateCreated:		-September-25-2016
   		 *  @dateLastModified:	-September-29-2016
   		 *  @author: 			-Nevhetha
   		 *----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
   		 *  
   		 *  @comment:			-
   		 *
   		 *  @memberVariable: 	-variableName_dataType:					accessSpecifier:		description:	
   		 *  					-element_T								private					value of the item
   		 *  					-next_Entry								private					to hold pointer to the next item
   		 *
   		 *  @constructor: 		-constructorSignature:											description:
   		 *  					-Entry(T x, Entry nxt):											Parameterized constructor
   		 *  
   		 *  @memberFunction: 	-methodSignature:												description:
   		 *  					
   		 *  --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------		
   		 */
    	/*---------------------------------------------------------------
    	* @memberVariable:
    	* ---------------------------------------------------------------
    	*/
        T element;
        Entry next;
        /*---------------------------------------------------------------
       	* @constructor function
       	* ---------------------------------------------------------------
       	*/
        Entry(T x, Entry nxt) {
            element = x;
            next = nxt;
        }
    }
    
    /*----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  @dateCreated:		-September-25-2016
	 *  @dateLastModified:	-September-29-2016
	 *  @author: 			-Nevhetha
	 *----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  
	 *  @comment:			-
	 *
	 *  @memberVariable: 	-variableName_dataType:					accessSpecifier:		description:	
	 *  					-header_Enrty							private					head of the circular linked list
	 *  					-tail_Entry								private					tail of the circular linked list
	 *
	 *  @constructor: 		-constructorSignature:											description:
	 *  					-CircularLinkedList():											Non-Parameterized constructor
	 *  
	 *  @memberFunction: 	-methodSignature:												description:
	 *  					-public Iterator<T> iterator():									Creates an iterator on the circular linked list
	 *						-void add(T item):												Adds an item to the list
	 *						-void printlist():												Prints the list
	 *  --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------		
	 */
    
    /*---------------------------------------------------------------
	* @memberVariable:
	* ---------------------------------------------------------------
	*/
    Entry header, tail;
    
    
    
    /*---------------------------------------------------------------
   	* @constructor function
   	* ---------------------------------------------------------------
   	*/
    CircularLinkedList() {
        header = new Entry(null, null);
        tail = header;
    }
    
    
    
    /*-------------------------------------------------------------------------------------------------------------------------------------------------------
   	 *  @dateLastModified:	-September-29-2016
   	 *  @author: 			-Nevhetha
   	 *-------------------------------------------------------------------------------------------------------------------------------------------------------
   	 *  
   	 *  @comment:			-iterator() is a method to create iterator from the circular linked list
   	 *  					
   	 *  @param: 			-variableName_dataType:							description:	
   	 *  			
   	 *  @localVariables: 	-variableName_dataType:							description:
   	 *  				
   	 *  @return:			-variableName_dataType:							description:
   	 *  					-it_Iterator<T>									iterator that start with the head of the circular Linked list
   	 *  
   	 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
   	 *  
   	 *  	
   	 */
	public Iterator<T> iterator() {
		 return new CLLIterator(header);
	}
	
	
	private class CLLIterator implements Iterator<T> {
		 /*----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	   	  *								*** CLLITerator:Class that creates Iterator on the Circular Linked List ***    																								   //
	   	 /*----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	   		 *  @dateCreated:		-September-25-2016
	   		 *  @dateLastModified:	-September-29-2016
	   		 *  @author: 			-Nevhetha
	   		 *----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	   		 *  
	   		 *  @comment:			-
	   		 *
	   		 *  @memberVariable: 	-variableName_dataType:					accessSpecifier:		description:	
	   		 *  					-current_Entry							private					current item on the list
	   		 *
	   		 *  @constructor: 		-constructorSignature:											description:
	   		 *  					-CLLIterator(Entry head):										Parameterized constructor
	   		 *  
	   		 *  @memberFunction: 	-methodSignature:												description:
	   		 *  					-public boolean hasNext():										Checks if the tail of the list has been reached
	   		 *  					-public T next():												Returns the next element form the list
	   		 *  
	   		 *  --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------		
	   		 */
		/*---------------------------------------------------------------
		* @memberVariable:
		* ---------------------------------------------------------------
		*/
		Entry current;
		
		/*---------------------------------------------------------------
	   	* @constructor function
	   	* ---------------------------------------------------------------
	   	*/
		CLLIterator(Entry head) {
			current =head;
		}
		
		/*-------------------------------------------------------------------------------------------------------------------------------------------------------
	   	 *  @dateLastModified:	-September-29-2016
	   	 *  @author: 			-Nevhetha
	   	 *-------------------------------------------------------------------------------------------------------------------------------------------------------
	   	 *  
	   	 *  @comment:			-hasNext() is a method to check if the tail of the list has been reached
	   	 *  					
	   	 *  @param: 			-variableName_dataType:							description:	
	   	 *  
	   	 *  @localVariables: 	-variableName_dataType:							description:
	   	 *  				
	   	 *  @return:			-variableName_dataType:							description:
	   	 *						-X_boolean:										true if tail of the list has not been reached, otherwise, false
	   	 *  
	   	 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
	   	 *  
	   	 *  	
	   	 */
		public boolean hasNext() {
			if(current.next!=header)
				return true;
			return false;
		}
		
		/*-------------------------------------------------------------------------------------------------------------------------------------------------------
	   	 *  @dateLastModified:	-September-29-2016
	   	 *  @author: 			-Nevhetha
	   	 *-------------------------------------------------------------------------------------------------------------------------------------------------------
	   	 *  
	   	 *  @comment:			-next() is a method that returns the next element from the list
	   	 *  					
	   	 *  @param: 			-variableName_dataType:							description:	
	   	 *  
	   	 *  @localVariables: 	-variableName_dataType:							description:
	   	 *  				
	   	 *  @return:			-variableName_dataType:							description:
	   	 *						-X_T:											returns the next element from the list
	   	 *  
	   	 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
	   	 *  
	   	 *  	
	   	 */
		public T next() {
			current=current.next;
			return current.element;
		}
	}
	 /*-------------------------------------------------------------------------------------------------------------------------------------------------------
   	 *  @dateLastModified:	-September-29-2016
   	 *  @author: 			-Nevhetha
   	 *-------------------------------------------------------------------------------------------------------------------------------------------------------
   	 *  
   	 *  @comment:			-add(...) is a method to add an item to the circular linked list
   	 *  					
   	 *  @param: 			-variableName_dataType:							description:	
   	 *  					-item_T											item to be added to the circular linked list
   	 *  
   	 *  @localVariables: 	-variableName_dataType:							description:
   	 *  				
   	 *  @return:			-variableName_dataType:							description:
   	 *  					-it_Iterator<Vertex>							iterator on the vertices of the graph
   	 *  
   	 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
   	 *  
   	 *  	
   	 */
	void add(T item){
		tail.next=new Entry(item,null);
		tail=tail.next;
		tail.next=header;
	}
	 /*-------------------------------------------------------------------------------------------------------------------------------------------------------
   	 *  @dateLastModified:	-September-29-2016
   	 *  @author: 			-Nevhetha
   	 *-------------------------------------------------------------------------------------------------------------------------------------------------------
   	 *  
   	 *  @comment:			-printlist() is a method to print the elements of the circular linked list
   	 *  					
   	 *  @param: 			-variableName_dataType:							description:	
   	 *  			
   	 *  @localVariables: 	-variableName_dataType:							description:
   	 *  					-cursor_Entry									current node(entry object) being processed
   	 *  
   	 *  @return:			-variableName_dataType:							description:
   	 *  
   	 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
   	 *  
   	 *  	
   	 */
	void printlist(){
		Entry cursor=header.next;
		while(cursor.element!=null){
			System.out.println(cursor.element);
			cursor=cursor.next;
		}
	}
}