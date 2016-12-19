//=============================================================================================================================================================================================//
//	  							*** TreeMapStudy : Driver program that performs addition, deletion and contains function on Java's Tree Map ***    												   //
//=============================================================================================================================================================================================//
/*
 	 *  @dateCreated:		-October-30-2016
	 *  @dateLastModified:	-November-05-2016
	 *  @author: 			-Nevhetha
 */
//=============================================================================================================================================================================================//
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeMap;

public class TreeMapStudy<T extends Comparable<? super T>> {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc;
		if (args.length > 0) {
		    File file = new File(args[0]);
		    sc = new Scanner(file);
		} else {
		    sc = new Scanner(System.in);
		}
		String operation = "";
		long operand = 0;
		int modValue = 999983;
		long result = 0;
		TreeMap<Long,Long> tree=new TreeMap<>();
		// Initialize the timer
		Timer timer = new Timer();
		while (!((operation = sc.next()).equals("End"))) {
		    switch (operation) {
		    case "Add": {
			operand = sc.nextLong();
			if(tree.put(operand,operand) != null) {
			    result = (result + 1) % modValue;
			}
			break;
		    }
		    case "Remove": {
			operand = sc.nextLong();
			if (tree.remove(operand, operand)) {
			    result = (result + 1) % modValue;
			}
			break;
		    }
		    case "Contains":{
			operand = sc.nextLong();
			if (tree.containsKey(operand)) {
			    result = (result + 1) % modValue;
			}
			break;
		    }
			
		    }
		}
		sc.close();
		timer.end();
		System.out.println(result);
		System.out.println(timer);
	    }
}
