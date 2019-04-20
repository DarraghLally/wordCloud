/*
 * This class has a method (imgGen) that is used to generate the png image,
 * passing in three parameters:
 * (1) queue created in parse - q
 * (2) users max words for image (asked for in menu) - maxWords
 * (3) users save file name (asked for in menu) - fileName
 * 
 * Big O notation for this class:
 * (1) Loop to set min and max - O(n), where n is set by max words from user
 * (2) Loop to poll from queue - O(n), where n is set by max words from user
 * 
 * Total: O(n) + O(n) = O(2n)
 * 
 * If we disregard the constant we get: O(n)
 */

package ie.gmit.sw;

//Library Imports
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Queue;
import javax.imageio.ImageIO;

//Code from Dr. John Healy
public class PNGGenerator {
	
	final double UPPER = 80; //upper font size
	final double LOWER = 10; //lower font size
	private static int WIDTH = 800; //canvas width
	private static int HEIGHT = 600; //canvas height
	private static int MAX_WIDTH = 600; //image width
	private static int MAX_HEIGHT = 400; //image height
	
	public void imgGen(Queue<WordFrequency> q, int maxWords, String fileName) throws IOException {
		
		int count = 0; //loop control
		int max = Integer.MIN_VALUE; //max set low
		int min = Integer.MAX_VALUE; //min set high
		
		int[] style = {Font.PLAIN, Font.BOLD, Font.ITALIC}; //Array of font styles, used to get random style
		String[] type = {Font.SANS_SERIF, Font.SERIF, Font.MONOSPACED}; //as above for font type
		Color[] colours = {Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW, Color.CYAN, Color.PINK}; //as above for colors
		
		Iterator<WordFrequency> it = q.iterator(); //instance of an iterator from queue
		Random rand = new Random(); //instance of random class
		
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_4BYTE_ABGR); //instance of image class
		Graphics graphics = image.getGraphics(); //instance of graphics class
			
		//Big 'O': O(n) where n is the max words passed in to the method
		//Setting min and max from queue frequency
		while(it.hasNext() && count<maxWords) { 
			int j = it.next().getFrequency();
			if(j> max ) {
				max=j;
			}
			if(j< min) {
				min=j;
			}	
			count++;
		}
		count = 0;
		
		//Print max and min word count to console
		System.out.println("Max Word appears: " + max + " times.");
		System.out.println("Min Word appears: " + min + " times.");
		
		//Big 'O': O(n) where n is the max words passed in to the method
		//Poll from the queue and set words size, color and type depending on its frequency
		while(!q.isEmpty()&&count<maxWords) {
			WordFrequency wf = q.poll();
			graphics.setFont(new Font(type[rand.getRandom(0,type.length - 1)], style[rand.getRandom(0, style.length -1)], 
					(int)scale(wf.getFrequency(), min, max)));
			graphics.setColor(colours[rand.getRandom(0, colours.length-1)]);
			graphics.drawString(wf.getWord(), rand.getRandom(50, MAX_WIDTH-50), rand.getRandom(50, MAX_HEIGHT-50));
			count++;
		}

		graphics.dispose();
		
		//Create image, creating a new file each time
		ImageIO.write(image, "png", new File(fileName)); //save as per user input
		
	}//imgGen
	
	//Scale method, passing in the words frequency and the min and max values
	private int scale(double in, double inMin, double inMax) {
		double val = Double.valueOf(in);
		double max = Double.valueOf(inMax);
		double min = Double.valueOf(inMin);
		int sendThis = (int) ((UPPER - LOWER)*(val-min)/(max-min)+LOWER);
		return sendThis;
	}
}
