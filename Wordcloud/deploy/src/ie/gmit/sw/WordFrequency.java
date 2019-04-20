/*
 * Class and methods auto generated implementing Comparable interface,
 * 
 * Overridden compareTo method:
 * returns an integer value 
 * 
 */

package ie.gmit.sw;

public class WordFrequency implements Comparable<WordFrequency>{

	private String word;
	private int frequency;	
	
	public WordFrequency(String key, Integer value) {
		word = key;
		frequency = value;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	
	@Override
	public int compareTo(WordFrequency word) {
		return word.frequency - this.frequency;
	}
}
