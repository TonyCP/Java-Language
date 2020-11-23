package com.company;

import java.util.*;
import java.util.stream.*;
import java.util.function.*;
import java.util.concurrent.ConcurrentHashMap;
/*
 * Find the smallest dominating set for a graph
 *
 * On EOS, compile with the following command
 * javac DominatingSet.java
 */

public class DominatingSet {

    private static int n;
    private static int adjacencyMatrix[][];
    /*
      Return the number of elements in a set
      encoded by long integer e.
      The graph has n vertices
    */
    private static int countBits(long x,int n) {
        int result = 0;
        long mask = 1;
        for(int i=0;i < n;i++) {
            if ((x & mask) != 0) {
                result++;
            }
            mask = mask * 2;
        }
        return result;
    }

    /*
  Return an array of integer of size n
  that corresponds to the set encoded by long integer e.
  The graph has n vertices
*/
    private static int[] createArray(long x,int n) {
        int result[] = new int[n];
        long mask = 1;
        for(int i=0;i < n;i++) {
            if ((x & mask) != 0) {
                result[i] = 1;
            }
            else {
                result[i] = 0;
            }
            mask = mask * 2;
        }
        return result;
    }

    /* Return true if the long x encodes+
       a subset that is a dominating set
    */
    private static boolean isDominatingSet(long x,int n) {
        int verticeSet[] = createArray(x, n);


        for(int i: verticeSet) {
            for(int j: verticeSet) {
                if(j != i && adjacencyMatrix[i][j] == 1){
                    return false;
                }
            }
        }

        return true;
    }


    public static void main(String args[]) {
	/* Read the size of the graph - number of vertices
	   and then read the adjacency matrix for the graph
	*/
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        adjacencyMatrix = new int [n][n];
        for(int i = 0;i < n;i++) {
            for(int j = 0;j < n;j++) {
                adjacencyMatrix[i][j] = scanner.nextInt();
            }
        }
        // Print the adjacency matrix
	/*
	for(int i = 0;i < n;i++) {
	    for(int j = 0;j < n;j++) {
		System.out.print(adjacencyMatrix[i][j]);
	    }
	    System.out.println();
	}
	*/
        long twoToN = 1;
        for(int i = 0;i < n;i++) {
            twoToN = twoToN * 2;
        }
        // System.out.println("2^n is: "+twoToN);

        ConcurrentHashMap <Integer,Long> hm =
                new ConcurrentHashMap <Integer,Long> ();
        LongStream.range(1,twoToN).parallel().
                filter(e ->{ return(isDominatingSet(e,n));}).
                forEach(e -> hm.put(countBits(e,n),e));
        System.out.println(hm);


    }
}
