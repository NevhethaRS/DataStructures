//=============================================================================================================================================================================================//
//	  							*** SkipListImpl : Class that implements SkipList Interface ***    												   //
//=============================================================================================================================================================================================//
/*
 *  @dateCreated:		-October-18-2016
 *  @dateLastModified:	-November-05-2016
 *  @author: 			-Nevhetha
 */
//=============================================================================================================================================================================================//
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;



// Skeleton for skip list implementation.

public class SkipListImpl<T extends Comparable<? super T>> implements
		SkipList<T> {
            /*----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
             *  @dateCreated:		-October-30-2016
             *  @dateLastModified:	-November-05-2016
             *  @author: 			-Nevhetha
             *----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
             *
             *  @comment:			-
             *
             *  @memberVariable: 	-variableName_dataType:					accessSpecifier:									description:
             *  					-maxLevel_int:								private											maximum level of pointers that a node can have
             *  					-size_int:									private											number of elements in the skip list except the header and the tail
             *  					-header_Entry<T>:							private											header node of the skip list(marks the start), has null value and maxLevel pointers
             *  					-tail_Entry<T>:								private											tail node of the skip list(marks the end), has null value and maxLevel pointers.
             *
             *  @constructor: 		-constructorSignature:																		description:
             *  					-SkipListImpl():																			Non-Parameterized Constructor
             *
             *  @memberFunction: 	-methodSignature:																			description:
             *  					-public boolean contains(T x)																checks if the given element is present or not
             *  					-public Entry<T>[] find(T x)																finds the given element
             *  					-public boolean checkRebuild()																checks if it is necessary to rebuild the skipList
             *  					-public void rebuild()																		rebuilds the skip list
             *  					-private void updatePointers(Entry<T>[] arr,int k)											updates the pointers during rebuild
             *  					-public boolean add(T x)																	adds the element to the skip list
             *  					-private void updateIndex(Entry<T>[] prev,Entry<T> newNode, int newLevel)					updates the index values after insertion
             *  					-private int choice()																		decides the level for the node to be inserted
             *  					-public T ceiling(T x)																		returns the ceil of the element
             *  					-public T first()																			returns the first element
             *  					-public T last()																			returns the last element
             *  					-public T floor(T x)																		returns the floor of the element
             *  					-public boolean isEmpty()																	checks if the skip list is empty
             *  					-private void rebuild(Entry<T>[] arr, int low, int high,int d)								creates a perfect skip list
             *  					-public T findIndex(int n)																	finds the element at a given position
             *  					-public int size()																			returns the size of the skip list
             *  					-public T remove(T x)																		removes the element from the skip list
             *  					-private void updateDelIndex(Entry<T>[] prev,Entry<T> delNode, int length)					updates the index values after deletion
             *  --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------		
             */
	
	// Class entry to hold a node
	class Entry<T>{
        /*----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
         *  @dateCreated:		-October-30-2016
         *  @dateLastModified:	-November-05-2016
         *  @author: 			-Nevhetha
         *----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
         *
         *  @comment:			-
         *
         *  @memberVariable: 	-variableName_dataType:					accessSpecifier:									description:
         *  					-element_T:								private												value of the node
         *  					-next_Entry<T>[]:						private												pointers to elements in the skipList
         *  					-index_int[]:							private												index[i] holds number of elements between the node and node's next[i]
         *
         *  @constructor: 		-constructorSignature:																		description:
         *  					-Entry(T x,int level):																		Parameterized Constructor
         *
         *  @memberFunction: 	-methodSignature:																			description:
         *  --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
         */
        
        /*---------------------------------------------------------------
         * @memberVariable of Entry<T>
         * ---------------------------------------------------------------
         */
		T element;
		Entry<T>[]  next;
		int[] index;
		
        /*---------------------------------------------------------------
         * @constructor function:
         * ---------------------------------------------------------------
         */
		@SuppressWarnings("unchecked")
		Entry(T x,int level){
			element=x;
			next=new Entry[level+1];
			index=new int[level+1];
		}
	}
	
            /*---------------------------------------------------------------
             * @memberVariable of SkipListImpl
             * ---------------------------------------------------------------
             */
	int maxLevel=32;
	Entry<T> header,tail,last;
	int size;
	
            /*---------------------------------------------------------------
             * @constructor function:
             * ---------------------------------------------------------------
             */
	SkipListImpl(){
		header=new Entry(null,maxLevel);
		for(int i=0;i<=maxLevel;i++)
			header.index[i]=1;
		tail=new Entry(null,maxLevel);
		for(int i=maxLevel;i>=0;i--){
			header.next[i]=tail;
			tail.next[i]=tail;
		}
	}
	
            /*-------------------------------------------------------------------------------------------------------------------------------------------------------
             *  @dateCreated:		-October-30-2016
             *  @dateLastModified:	-November-05-2016
             *  @author: 			-Nevhetha
             *-------------------------------------------------------------------------------------------------------------------------------------------------------
             *
             *  @comment:			- public boolean contains(...) is a overridden function from SkipList interface that checks if the given element is in the skip list
             *
             *  @param: 			-variableName_dataType:							description:
             *  					-x_T:											element to be checked
             *
             *  @localVariables: 	-variableName_dataType:							description:
             *						-prev_Entry<T>[]:								Holds the positions from 0 through maxLevel where the search ended at each of the levels
             *																		(search starting with the header).
             *
             *  @return:			-variableName_dataType:							description:
             *  					-X_boolean:										true if the element is present, false otherwise.
             *  	   
             *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
             *  
             *  	
             */
	@Override
    public boolean contains(T x) {
		Entry<T>[] prev=find(x);
		if(prev[0].next[0]!=tail)	
			return (prev[0].next[0].element.compareTo(x)==0) ? true:false;
		return false;
    }
	
            /*-------------------------------------------------------------------------------------------------------------------------------------------------------
             *  @dateCreated:		-October-30-2016
             *  @dateLastModified:	-November-05-2016
             *  @author: 			-Nevhetha
             *-------------------------------------------------------------------------------------------------------------------------------------------------------
             *
             *  @comment:			- public Entry<T>[] find(...) is a helper function that returns the points from 0 through maxLevel
             *  					 where the search ended at each of the levels (while searching for a given element).
             *
             *  @param: 			-variableName_dataType:							description:
             *  					-x_T:											element to be checked
             *
             *  @localVariables: 	-variableName_dataType:							description:
             *						-node_Entry<T>:									current node being processed
             *
             *  @return:			-variableName_dataType:							description:
             *						-prev_Entry<T>[]:								returns the positions from 0 through maxLevel where the search ended at each of the levels
             *																		(search starting with the header).
             *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
             *  
             *  	
             */
	public Entry<T>[] find(T x) {
		@SuppressWarnings("unchecked")
		Entry<T>[] prev=new Entry[maxLevel+1];
		Entry<T> node=header;
		for(int i=maxLevel;i>=0;i--){
			while( node.next[i] != tail && node.next[i].element.compareTo(x) < 0 ){
				node=node.next[i];
			}
			prev[i]=node;
		}
		return prev;
	}

            /*-------------------------------------------------------------------------------------------------------------------------------------------------------
             *  @dateCreated:		-October-30-2016
             *  @dateLastModified:	-November-05-2016
             *  @author: 			-Nevhetha
             *-------------------------------------------------------------------------------------------------------------------------------------------------------
             *
             *  @comment:			- public boolean add(...) is a overridden function that adds the given element at the correct position if it is not available in the list
             *  					- It Replaces the element if it is already available in the list
             *
             *  @param: 			-variableName_dataType:							description:
             *  					-x_T:											element to be added
             *
             *  @localVariables: 	-variableName_dataType:							description:
             *						-prev_Entry<T>[]:								Holds the positions from 0 through maxLevel where the search ended at each of the levels
             *																		(search starting with the header).
             *						-level_int:										number of next pointers for this new node
             *						-newNode_Entry<T>:								new node t be inserted with element = x and 'level' next pointer
             *
             *  @return:			-variableName_dataType:							description:
             *  					-x_boolean:										true if the element is not present and it was inserted
             *  																	false if it was replaced.
             *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
             *  
             *  	
             */
	@Override
    public boolean add(T x) {
		Entry<T>[] prev=find(x);
		if(prev[0].next[0]!=tail&&prev[0].next[0].element.compareTo(x)==0){
			prev[0].next[0].element=x;
			return false;
		}
		int level=choice();
		Entry<T> newNode=new Entry<>(x,level);
		if(prev[0].next[0].element==null)
			last=newNode;
		if(prev.length>=newNode.next.length){
			for(int i=0;i<newNode.next.length;i++){
				newNode.next[i]=prev[i].next[i];
				prev[i].next[i]=newNode;
			}
		}
		else {
			for(int i=0;i<prev.length;i++){
				newNode.next[i]=prev[i].next[i];
				prev[i].next[i]=newNode;
			}
			for(int i= prev.length;i<newNode.next.length;i++){
				newNode.next[i]=tail;
				header.next[i]=newNode.next[i];
			}
		}
		size++;
		updateIndex(prev,newNode,newNode.index.length);
		return true;
    }
            /*-------------------------------------------------------------------------------------------------------------------------------------------------------
             *  @dateCreated:		-October-30-2016
             *  @dateLastModified:	-November-05-2016
             *  @author: 			-Nevhetha
             *-------------------------------------------------------------------------------------------------------------------------------------------------------
             *
             *  @comment:			- private void updateIndex(...) is a helper function that updates the index values due to new insertion.
             *
             *  @param: 			-variableName_dataType:							description:
             *  					-prev_Entry<T>[]:								Holds the positions from 0 through maxLevel where the search ended at each of the levels
             *																		(search starting with the header).
             *						-newNode_Entry<T>:								new node t be inserted with element = x and 'level' next pointer
             *						-newLevel_int:									level of pointers for the newly inserted node.
             *
             *  @localVariables: 	-variableName_dataType:							description:
             *  					-prevGap_int:									previous value of the index of the node being considered at  considered level.
             *  					-node_Entry<T>:									node next to the node being considered
             *						-gap_int:										Number of elements from the node being considered to the next node to the new node.
             *
             *  @return:			-variableName_dataType:							description:
             *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
             *  
             *  	
             */
	private void updateIndex(SkipListImpl<T>.Entry<T>[] prev, SkipListImpl<T>.Entry<T> newNode, int newLevel) {
		for(int i=0;i<=maxLevel;i++){
			if(i>newLevel-1){
				prev[i].index[i]=prev[i].index[i]+1;
			}
			else {
				if(i==0){
					newNode.index[i]=prev[i].index[i];
					prev[i].index[i]=1;
				}
				else {
					int prevGap=prev[i].index[i];
					if(prev[i].element==prev[i-1].element){
						prev[i].index[i]=prev[i-1].index[i-1];
						newNode.index[i]=prevGap-prev[i].index[i]+1;
					}
					else{
						Entry<T> node=prev[i].next[0];
						int gap=1;
						while(node!=newNode){
							gap++;
							node=node.next[0];
						}
						prev[i].index[i]=gap;
						newNode.index[i]=prevGap-prev[i].index[i]+1;
					}
				}
			}
		}	
	}

            /*-------------------------------------------------------------------------------------------------------------------------------------------------------
             *  @dateCreated:		-October-30-2016
             *  @dateLastModified:	-November-05-2016
             *  @author: 			-Nevhetha
             *-------------------------------------------------------------------------------------------------------------------------------------------------------
             *
             *  @comment:			- private int choice() is a helper function that returns the length for the next array in a random manner
             *
             *  @param: 			-variableName_dataType:							description:
             *  					-random_Random:									random number being generated for the pass
             *  					-b_boolean:										boolean value corresponding to the random number being generated
             *
             *  @localVariables: 	-variableName_dataType:							description:
             *
             *  @return:			-variableName_dataType:							description:
             *  					-l_int:											calculated level
             *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
             *  
             *  	
             */
    private int choice() {
		int l=0;
		while(l<maxLevel){
			Random random=new Random();
			boolean b=random.nextBoolean();
			if(b)
				break;
			else
				l++;
		}
		return l;
	}

            /*-------------------------------------------------------------------------------------------------------------------------------------------------------
             *  @dateCreated:		-October-30-2016
             *  @dateLastModified:	-November-05-2016
             *  @author: 			-Nevhetha
             *-------------------------------------------------------------------------------------------------------------------------------------------------------
             *
             *  @comment:			-public T ceiling(...) is a function that returns ceil of the given element
             *
             *  @param: 			-variableName_dataType:							description:
             *
             *  @localVariables: 	-variableName_dataType:							description:
             *  					-prev_Entry<T>[]:								Holds the positions from 0 through maxLevel where the search ended at each of the levels
             *																		(search starting with the header).
             *
             *  @return:			-variableName_dataType:							description:
             *						-X_T:											element itself if it is present in the skip list else the next element.
             *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
             *  
             *  	
             */
            @Override
    public T ceiling(T x) {
		Entry<T>[] prev=find(x);
		//System.out.println("Ceil ("+x+")"+prev[0].next[0].element);
		return prev[0].next[0].element;
    }

            /*-------------------------------------------------------------------------------------------------------------------------------------------------------
             *  @dateCreated:		-October-30-2016
             *  @dateLastModified:	-November-05-2016
             *  @author: 			-Nevhetha
             *-------------------------------------------------------------------------------------------------------------------------------------------------------
             *
             *  @comment:			-public T first() is a function that returns the first element of the list
             *
             *  @param: 			-variableName_dataType:							description:
             *
             *  @localVariables: 	-variableName_dataType:							description:
             *
             *  @return:			-variableName_dataType:							description:
             *						-X_T:											first element in the skip list(excluding the header).
             *																		null, if size of the skiplist is 0.
             *  -----------------------------------------------------------------------------------------------------------------------------------------------------
             *  
             *  	
             */

    @Override
    public T first() {
    	//System.out.println("First:"+header.next[0].element);
	return header.next[0].element;
    }
    
            /*-------------------------------------------------------------------------------------------------------------------------------------------------------
             *  @dateCreated:		-October-30-2016
             *  @dateLastModified:	-November-05-2016
             *  @author: 			-Nevhetha
             *-------------------------------------------------------------------------------------------------------------------------------------------------------
             *
             *  @comment:			-public T last() is a function that returns the last element of the list
             *
             *  @param: 			-variableName_dataType:							description:
             *
             *  @localVariables: 	-variableName_dataType:							description:
             *
             *  @return:			-variableName_dataType:							description:
             *						-X_T:											last element in the skip list(excluding the tail).
             *																		null, if size of the skiplist is 0.
             *  -----------------------------------------------------------------------------------------------------------------------------------------------------
             *  
             *  	
             */
    @Override
    public T last() {
    	if(size!=0){
    		return last.element;
    	}
	return null;
    }
    
            /*-------------------------------------------------------------------------------------------------------------------------------------------------------
             *  @dateCreated:		-October-30-2016
             *  @dateLastModified:	-November-05-2016
             *  @author: 			-Nevhetha
             *-------------------------------------------------------------------------------------------------------------------------------------------------------
             *
             *  @comment:			-public T floor(...) is a function that returns floor of the given element
             *
             *  @param: 			-variableName_dataType:							description:
             *						-x_T:											element whose floor is to be found.
             *  @localVariables: 	-variableName_dataType:							description:
             *  					-prev_Entry<T>[]:								Holds the positions from 0 through maxLevel where the search ended at each of the levels
             *																		(search starting with the header).
             *
             *  @return:			-variableName_dataType:							description:
             *						-X_T:											element itself if it is present in the skip list else the previous  element.
             *  -----------------------------------------------------------------------------------------------------------------------------------------------------
             *  
             *  	
             */
    @Override
    public T floor(T x) {
    	Entry<T>[] prev=find(x);
    	if(prev[0].next[0]!=tail && prev[0].next[0].element.compareTo(x)==0){
    		//System.out.println("Floor ("+x+")"+prev[0].next[0].element);
    		return prev[0].next[0].element;
    	}
    	else
    	{
    		//System.out.println("Floor ("+x+")"+prev[0].element);
    		return prev[0].element;
    	}
    }

            /*-------------------------------------------------------------------------------------------------------------------------------------------------------
             *  @dateCreated:		-October-30-2016
             *  @dateLastModified:	-November-05-2016
             *  @author: 			-Nevhetha
             *-------------------------------------------------------------------------------------------------------------------------------------------------------
             *
             *  @comment:			-public boolean isEmpty(...) is a function that returns true if the list is empty else false
             *
             *  @param: 			-variableName_dataType:							description:
             *
             *  @localVariables: 	-variableName_dataType:							description:
             *
             *  @return:			-variableName_dataType:							description:
             *						-X_boolean:										if the size of the skip list is 0, else, false.
             *  -----------------------------------------------------------------------------------------------------------------------------------------------------
             *  
             *  	
             */    @Override
    public boolean isEmpty() {
    	return size==0 ? true:false;
    }

    //returns the iterator for the skip list
    @Override
    public Iterator<T> iterator() {
    	return (Iterator<T>) new SkipListIterator(header);
    }
    private class SkipListIterator<E> implements Iterator<E>{
    	Entry<E> current,previous;
    	SkipListIterator(Entry<E> head){
    		current =head;
    		previous=null;
    	}

		@Override
		public boolean hasNext() {
			return current.next[0]!=tail ? true: false;
		}

		@Override
		public E next() {
			previous=current;
			current=current.next[0];
			return previous.element;
		}
    }
            /*-------------------------------------------------------------------------------------------------------------------------------------------------------
             *  @dateCreated:		-October-30-2016
             *  @dateLastModified:	-November-05-2016
             *  @author: 			-Nevhetha
             *-------------------------------------------------------------------------------------------------------------------------------------------------------
             *
             *  @comment:			- public boolean checkRebuild() is a function that checks if it necessary to rebuild the skip list.
             *
             *  @param: 			-variableName_dataType:							description:
             *
             *  @localVariables: 	-variableName_dataType:							description:
             *
             *  @return:			-variableName_dataType:							description:
             *						-X_boolean:										true, if the (2^maxLevel)<=size
             *																		false, otherwise
             *  -----------------------------------------------------------------------------------------------------------------------------------------------------
             *
             *
             */
            public boolean checkRebuild(){
                if(Math.pow(2, maxLevel)<=size)
                    return false;
                else
                    return true;
            }
            
            /*-------------------------------------------------------------------------------------------------------------------------------------------------------
             *  @dateCreated:		-October-30-2016
             *  @dateLastModified:	-November-05-2016
             *  @author: 			-Nevhetha
             *-------------------------------------------------------------------------------------------------------------------------------------------------------
             *
             *  @comment:			- public void rebuild() is a overridden function that rebuilds the skiplist to perfect skiplist
             *
             *  @param: 			-variableName_dataType:							description:
             *  					-k_int:											new maxLevel calculated
             *
             *  @localVariables: 	-variableName_dataType:							description:
             *						-arr_Entry<T>[]:								array of the size of the skip list being considered to which the elements
             *																		will be copied to
             *
             *  @return:			-variableName_dataType:							description:
             *  -----------------------------------------------------------------------------------------------------------------------------------------------------
             *
             *
             */
            @Override
            public void rebuild() {
                int k=(int)Math.ceil(Math.log(size+1)/Math.log(2));
                Entry<T>[] arr=new Entry[size];
                rebuild(arr,0,size-1,k);
                updatePointers(arr,k);
            }
            
            /*-------------------------------------------------------------------------------------------------------------------------------------------------------
             *  @dateCreated:		-October-30-2016
             *  @dateLastModified:	-November-05-2016
             *  @author: 			-Nevhetha
             *-------------------------------------------------------------------------------------------------------------------------------------------------------
             *
             *  @comment:			- private void updatePointers(...) is a helper function that helps to update the next pointers at each node during rebuilding the
             *  					skipList
             *
             *  @param: 			-variableName_dataType:							description:
             *  					-k_int:											new maxLevel calculated
             *  					-arr_Entry<T>[]:								array of the size of the skip list being considered to which the elements
             *																		will be copied to
             *
             *  @localVariables: 	-variableName_dataType:							description:
             *						-node_Entry<T>:									current node being considered(processed)
             *  					-tempPointers_Entry<T>[]:						temporary array that holds the last node processed at that level
             *
             *  @return:			-variableName_dataType:							description:
             *  -----------------------------------------------------------------------------------------------------------------------------------------------------
             *  
             *  	
             */
            private void updatePointers(Entry<T>[] arr,int k) {
                Entry<T> node=header.next[0];
                maxLevel=k;
                Entry<T>[] tempPointers=new Entry[k+1];
                this.header=new Entry<>(null,k);
                for(int i=maxLevel;i>=0;i--){
                    tempPointers[i]=header;
                    header.index[i]=1;
                }
                for(int i=0;i<size-1;i++){
                    arr[i].element=node.element;
                    for(int j=0;j<arr[i].next.length;j++){
                        
                        tempPointers[j].next[j]=arr[i];
                        tempPointers[j]=arr[i];
                    }
                    for(int j=arr[i].next.length;j<=maxLevel;j++){
                        tempPointers[j].index[j]++;
                    }
                    node=node.next[0];
                }
                this.tail=new Entry<>(null,k);
                for(int i=maxLevel;i>=0;i--){
                    tempPointers[i].next[i]=tail;
                    tail.next[i]=tail;
                }
            }
            
   
            /*-------------------------------------------------------------------------------------------------------------------------------------------------------
           	 *  @dateCreated:		-October-30-2016
           	 *  @dateLastModified:	-November-05-2016
           	 *  @author: 			-Nevhetha
           	 *-------------------------------------------------------------------------------------------------------------------------------------------------------
           	 *  
           	 *  @comment:			-private void rebuild(...) is a helper function that helps to create a new perfect skip list
           	 *
           	 *  @param: 			-variableName_dataType:							description:	
           	 *						-arr_Entry<T>[]:								new array for the perfect skip list
           	 *						-low_int:										start index from which the perfect list is to be built
           	 *						-hight_int:										end index to which the perfect list is to built
           	 *						-d_int:											level for the node (number of next pointers)
           	 *		
           	 *  @localVariables: 	-variableName_dataType:							description:
           	 *  					-mid_int:										middle index
           	 *  					
           	 *  @return:			-variableName_dataType:							description:
           	 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
           	 *  
           	 *  	
           	 */

        	private void rebuild(Entry<T>[] arr, int low, int high,int d) {
            	if(low<=high){
            		if(d==0){
            			for(int i=low;i<=high;i++)
            				arr[i]=new Entry(null,0);	
            		}
            		else {
            			int mid=(low+high)/2;
            			arr[mid]=new Entry(null,d);
            			rebuild(arr,low,mid-1,d-1);
            			rebuild(arr,mid+1,high,d-1);
            		}
            	}	
        	}


            /*-------------------------------------------------------------------------------------------------------------------------------------------------------
             *  @dateCreated:		-October-30-2016
             *  @dateLastModified:	-November-05-2016
             *  @author: 			-Nevhetha
             *-------------------------------------------------------------------------------------------------------------------------------------------------------
             *
             *  @comment:			-public T findIndex(...) is a method to find the element at the given index RT: O(log n)
             *
             *  @param: 			-variableName_dataType:							description:
             *						-n_int:											index of the element to be returned
             *
             *  @localVariables: 	-variableName_dataType:							description:
             *						-node_Entry<T>:									current node being considered
             *
             *  @return:			-variableName_dataType:							description:
             *  					-X_T:											element at the index n
             *  -----------------------------------------------------------------------------------------------------------------------------------------------------
             *  
             *  	
             */	@Override
    public T findIndex(int n) {
		 Entry<T> node = header;
	     n = n + 1;
	     for(int level = maxLevel; level >= 0 ; level--) {
	          while (node != tail && n >= node.index[level]) {
	              n = n - node.index[level];
	              node = node.next[level];
	          }
	     }
	     return node.element;
    }
    
            /*-------------------------------------------------------------------------------------------------------------------------------------------------------
             *  @dateCreated:		-October-30-2016
             *  @dateLastModified:	-November-05-2016
             *  @author: 			-Nevhetha
             *-------------------------------------------------------------------------------------------------------------------------------------------------------
             *
             *  @comment:			-public int size() is a method to return the size of the skip list
             *
             *  @param: 			-variableName_dataType:							description:
             *
             *  @localVariables: 	-variableName_dataType:							description:
             *
             *  @return:			-variableName_dataType:							description:
             *  					-X_int:											number of elements in the skip list(excluding header and tail)
             *  -----------------------------------------------------------------------------------------------------------------------------------------------------
             *  
             *  	
             */
    @Override
    public int size() {
	return size;
    }
    
            /*------------------------------------------------------------------------------------------------------------------------------------------------------
             *  @dateCreated:		-October-30-2016
             *  @dateLastModified:	-November-05-2016
             *  @author: 			-Nevhetha
             *-------------------------------------------------------------------------------------------------------------------------------------------------------
             *
             *  @comment:			-public int size() is a method to return the size of the skip list
             *
             *  @param: 			-variableName_dataType:							description:
             *
             *  @localVariables: 	-variableName_dataType:							description:
             *
             *  @return:			-variableName_dataType:							description:
             *  					-X_int:											number of elements in the skip list(excluding header and tail)
             *  -----------------------------------------------------------------------------------------------------------------------------------------------------
             *  
             *  	
             */    @Override
    public T remove(T x) {
    	Entry<T>[] prev=find(x);
    	if(prev[0].next[0]!=tail&&prev[0].next[0].element.compareTo(x)==0){
       		Entry<T> delNode=prev[0].next[0];
       		updateDelIndex(prev,delNode,delNode.index.length);
    		for(int i=0;i<delNode.next.length;i++){
    			if(prev[i].next[i]==delNode){
    				prev[i].next[i]=delNode.next[i];
    			}
    		}
    		if(delNode.element.compareTo(last.element)==0)
    			last=prev[0];
    		return delNode.element;
    	}
	return null;
    }

            /*-------------------------------------------------------------------------------------------------------------------------------------------------------
             *  @dateCreated:		-October-30-2016
             *  @dateLastModified:	-November-05-2016
             *  @author: 			-Nevhetha
             *-------------------------------------------------------------------------------------------------------------------------------------------------------
             *
             *  @comment:			-private void updateDelIndex(...) is a method removes the element and return it if present
             *  					-Else returns null
             *
             *  @param: 			-variableName_dataType:							description:
             *  					-prev_Entry<T>[]:								Holds the positions from 0 through maxLevel where the search ended at each of the levels
             *																		(search starting with the header).
             *						-delNode_Entry<T>:								node that was deleted
             *
             *  @localVariables: 	-variableName_dataType:							description:
             *
             *  @return:			-variableName_dataType:							description:
             *  -----------------------------------------------------------------------------------------------------------------------------------------------------
             *  
             *  	
             */
	private void updateDelIndex(SkipListImpl<T>.Entry<T>[] prev, SkipListImpl<T>.Entry<T> delNode, int length) {
		for(int i=0;i<=maxLevel;i++){
			if(i>=length){
				prev[i].index[i]=prev[i].index[i]-1;
			}
			else{
				prev[i].index[i]=prev[i].index[i]+delNode.index[i]-1;
			}
		}
		
	}
}
