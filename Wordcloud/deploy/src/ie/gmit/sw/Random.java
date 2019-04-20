/*
 * Random class that can be used in future projects
 * 
 */

package ie.gmit.sw;
import java.util.concurrent.ThreadLocalRandom;

public class Random {
	
	public int getRandom(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max+1);	
	}
	
}
