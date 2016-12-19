import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DriverPrim1 {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in;
	        if (args.length > 0) {
	            File inputFile = new File(args[0]);
	            in = new Scanner(inputFile);
	        } else {
	            in = new Scanner(System.in);
	        }

	        Graph g = Graph.readGraph(in);
	        Vertex s = g.getVertex(1);
	        Timer t=new Timer();
	        t.start();
	        System.out.println("MST "+MST.PrimMSTEdge(g, s));
	        t.end();
	        System.out.println(t);
	    }

}
