package hw2;

/**
 *  
 * @author Cade Bradford
 *
 */

/**
 * 
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */

import java.io.FileNotFoundException;
import java.util.Scanner; 
import java.util.Random; 


public class CompareSorters 
{
	/**
	 * Repeatedly take integer sequences either randomly generated or read from files. 
	 * Use them as coordinates to construct points.  Scan these points with respect to their 
	 * median coordinate point four times, each time using a different sorting algorithm.  
	 * 
	 * @param args
	 **/
	public static void main(String[] args) throws FileNotFoundException
	{		
		boolean running = true;
		PointScanner[] scanners = new PointScanner[4];
		Random rand = new Random();
		Scanner scnr = new Scanner(System.in);
		System.out.println("Performances of Four Sorting Algorithms in Point Scanning");
		int choice = 0, trail = 1;
		while(running)
		{
			System.out.println("keys:  1 (random integers)  2 (file input)  3 (exit)");

			choice = scnr.nextInt();

			if(choice == 1)
			{
				System.out.println("Trial " + trail + ": " + choice);
				System.out.println("Enter number of random points: ");
				int randNumPoints = scnr.nextInt();
				scanners[0] = new PointScanner(generateRandomPoints(randNumPoints,rand), Algorithm.SelectionSort);
				scanners[1] = new PointScanner(generateRandomPoints(randNumPoints,rand), Algorithm.InsertionSort);
				scanners[2] = new PointScanner(generateRandomPoints(randNumPoints,rand), Algorithm.MergeSort);
				scanners[3] = new PointScanner(generateRandomPoints(randNumPoints,rand), Algorithm.QuickSort);

				System.out.println("algorithm   size     time   (ns)");
				System.out.println("----------------------------------");
				for(int i = 0; i < scanners.length; i++)
				{
					scanners[i].scan();
					System.out.println(scanners[i].stats());
				}

				trail++;
			}
			if(choice == 2)
			{

				System.out.println("Trial " + trail + ": " + choice);
				System.out.println("Points From a File");
				System.out.println("File Name: ");
				String fileString = scnr.next();
				scanners[0] = new PointScanner(fileString, Algorithm.SelectionSort);
				scanners[1] = new PointScanner(fileString, Algorithm.InsertionSort);
				scanners[2] = new PointScanner(fileString, Algorithm.MergeSort);
				scanners[3] = new PointScanner(fileString, Algorithm.QuickSort);

				System.out.println("");
				System.out.printf("%-17s %-10s %-10s \n", "algorithm", "size", "time (ns)");
				System.out.println("--------------------------------------");
				for (int i = 0; i < scanners.length; i++) 
				{
					scanners[i].scan();
					System.out.println(scanners[i].stats());
				}
				System.out.println("--------------------------------------");
				System.out.println();
				trail++;
			}
			if(choice == 3) {
				running = false;
				scnr.close();
			}
			

		}
		
		// For each input of points, do the following. 
		// 
		//     a) Initialize the array scanners[].  
		//
		//     b) Iterate through the array scanners[], and have every scanner call the scan() 
		//        method in the PointScanner class.  
		//
		//     c) After all four scans are done for the input, print out the statistics table from
		//		  section 2.
		//
		// A sample scenario is given in Section 2 of the project description. 
		
	}
	
	
	/**
	 * This method generates a given number of random points.
	 * The coordinates of these points are pseudo-random numbers within the range 
	 * [-50,50] � [-50,50]. Please refer to Section 3 on how such points can be generated.
	 * 
	 * Ought to be private. Made public for testing. 
	 * 
	 * @param numPts  	number of points
	 * @param rand      Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 */
	public static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException
	{ 
		if (numPts < 1) {
			throw new IllegalArgumentException("Number of points is less than 1");
		} else {
			Point[] points = new Point[numPts];
			int x;
			int y;
			
			for(int i = 0; i < numPts; i++) {
				x = rand.nextInt(101) - 50;
				y = rand.nextInt(101) - 50;
				
				Point p = new Point(x, y);
				points[i] = p;
			}
			
			return(points);
		}
	}
	
}
