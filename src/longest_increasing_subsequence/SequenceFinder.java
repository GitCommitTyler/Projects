package longest_increasing_subsequence;

import java.util.ArrayList;
//import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class SequenceFinder {
	public static List<Integer> randoms = new ArrayList<Integer>();
	//public static List<Integer> randoms = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
	// Generates a list of random values from 0 to 100 
	public static void genList() {
		new Random().ints(30, 0, 100)
		.distinct()
		.forEach(x -> randoms.add(x)); 
		System.out.println(randoms);
	}
	
	// Prints the length of the longest continuous subsequence of increasing values
	// Commented lines make it also print the actual sequence.
	public static void getLIS() {
		int longestSeqLength = 1;  
		int currentSeqLength = 1;
		/*
		 * List<Integer> currentSeq = new ArrayList<Integer>(); List<Integer> longestSeq
		 * = new ArrayList<Integer>();
		 */
		for(int i = 0; i < randoms.size()-1; i++) {
			
			if(randoms.get(i)<randoms.get(i+1)) {
				/*
				 * if(currentSeq.size()==0) currentSeq.add(i);
				 */
				currentSeqLength += 1;
				//currentSeq.add(randoms.get(i+1));
			}
			if(currentSeqLength > longestSeqLength) {
				longestSeqLength = currentSeqLength;
				/*
				 * longestSeq.clear(); longestSeq.addAll(currentSeq);
				 */
				
			}
			if(randoms.get(i)>=randoms.get(i+1)) {
				currentSeqLength = 1;
				//currentSeq.clear();
				if(i+longestSeqLength+1 > randoms.size()-1)
					break;
				if(randoms.get(i+longestSeqLength+1)>randoms.get(i+longestSeqLength)) {
					continue;
				}
				else
				{
					i += longestSeqLength;
					
				}
			}
			
		}
		System.out.println(longestSeqLength);
		//System.out.println(longestSeq);
	}
	
	
}



