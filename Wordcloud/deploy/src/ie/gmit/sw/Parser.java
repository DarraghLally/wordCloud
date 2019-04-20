/*
 * This class has a method (parse) that takes in:
 * (1)User defined file/URL - inRead
 * (2)Ignore words file - inIgnore
 * (3)User defined max words for the image - numWords
 * (4)User defined image file save - fileName
 * 
 * Big O notation for this class:
 * (1) Loop to read in the ignore words text file - O(n), where n is number of words in ignore file
 * (2) Nested Loop:
 * 		(a)While - O(n), where n is the number of words in the user defined file/URL
 * 			(a1)For - O(log n), where n is the number of words placed in the array
 * 
 * Total: O(n) + (O(n) * O(log n))
 * 
 * Removing the constant we get O(n log n)
 * 
 */

package ie.gmit.sw;

//Import Libraries 
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Parser {
	
	PNGGenerator png = new PNGGenerator(); //creating instance of PNG class 
	private String[] readArr; //array to hold read words
	
	private Map<String, Integer> readMap = new HashMap<>();
	private Map<String, Integer> ignoreMap = new HashMap<>();

	public void parse(InputStream inRead, InputStream inIgnore, int numWords, String fileName ) throws Exception { //passing in readFile and ignorewords
		
		//Start stop watch
		double runTime = System.currentTimeMillis(); 
			
		BufferedReader brIgnore = new BufferedReader(new InputStreamReader(inIgnore)); //Instance of buff reader to read ignorewords
		String nextIgnore = null; //string to hold word
		
		//Loop: O(n)
		//Read in each word
		//Remove non alphabetical characters 
		//change to lower case
		//Place formatted word in map.: key - word / value - 1
		while((nextIgnore = brIgnore.readLine())  != null) {
			nextIgnore = nextIgnore.replaceAll("[^a-zA-Z]+" , "").toLowerCase();
					ignoreMap.put(nextIgnore, 1);
		}//while
		
		
		BufferedReader brRead = new BufferedReader(new InputStreamReader(inRead)); //Instance of buff reader to read user choice(text file or URL)
		String nextWord = null; //variable to hold word
		
		//While - O(n), n is the number of words in what we read
		//For - O(n), n is the number of elements in the array
		//Total: O(numWhile * numFor) - not n^2
		while ((nextWord = brRead.readLine()) != null) { //read file until nothing left
			if(!nextWord.isEmpty()) { 
				readArr = nextWord.split(" "); //Filling array is O(n) where n is the number of words in the readFile
				for(String word : readArr) { //Looping through the array is O(n)
					nextWord = word.replaceAll("[^a-zA-Z]+" , "").toLowerCase(); //Filter out characters that are not in the alphabet. change all to lower case	
					if(!ignoreMap.containsKey(nextWord) && (!nextWord.equals(""))) { //Filter out the ignore words and empty spaces
						if(readMap.containsKey(nextWord)) { //+1 to word frequency if word exists already
							 readMap.put(nextWord, readMap.get(nextWord)+1); 
						}
						else { //Add word(Key) if it doesn't exist, giving the frequency(value) of 1. This happens the first time the word is found only
							readMap.put(nextWord, 1);
						}
					}
				}//for	
			}//if
		}//while
		
		//Create set of words from word map
		Set<String> keys = readMap.keySet();

		//Create priority queue
		Queue<WordFrequency> q = new PriorityQueue<>();
		
		for(String key: keys) {
			q.offer(new WordFrequency(key, readMap.get(key)));//Add words from set to priority queue
		}
		
		//create image, passing queue, the number of words as input by user and save file name as input from user
		png.imgGen(q, numWords, fileName);
		
		//Close readers
		brRead.close();
		brIgnore.close();		
		
		//Stop timer and print to console
		double totalTime = ((System.currentTimeMillis()-runTime)/1000);
		System.out.println("RUN TIME: " +totalTime+" seconds\n");
	}//parse
}//Parser	
	
	

