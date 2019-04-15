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
	private static int WIDTH = 800; //canvas size width
	private static int HEIGHT = 600; //canvas size height
	private static int MAX_WIDTH = 600;
	private static int MAX_HEIGHT = 400;
	
	public void imgGen(Queue<WordFrequency> q, int numOfWords, String fileName) throws IOException {
		int count = 0;
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		int maxWords = numOfWords; 
		
		int[] style = {Font.PLAIN, Font.BOLD, Font.ITALIC};
		String[] type = {Font.SANS_SERIF, Font.SERIF, Font.MONOSPACED};
		Color[] colours = {Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW, Color.CYAN, Color.PINK};
		
		Iterator<WordFrequency> it = q.iterator();
		Random rand = new Random();
		
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 62);
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics graphics = image.getGraphics();
		
		graphics.setFont(font); //remove when randFont is working
		
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
			//System.out.println("DEBUG max: "+max);
			//System.out.println("DEBUG min: "+min);
			while(!q.isEmpty()&&count<maxWords) {
				WordFrequency wf = q.poll();
				//Font randFont = new Font(type[rand.getRandom(0,type.length - 1)], style[rand.getRandom(0, style.length -1)], (int)scale(wf.getFrequency(), min, max));
				graphics.setFont(new Font(type[rand.getRandom(0,type.length - 1)], style[rand.getRandom(0, style.length -1)], 
						(int)scale(wf.getFrequency(), min, max)));
				graphics.setColor(colours[rand.getRandom(0, colours.length-1)]);
				graphics.drawString(wf.getWord(), rand.getRandom(50, MAX_WIDTH-50), rand.getRandom(50, MAX_HEIGHT-50));
				count++;
			}


		graphics.dispose();
		ImageIO.write(image, "png", new File(fileName)); //save as per user input

	}//imgGen
	
	private int scale(double in, double inMin, double inMax) {
		double val = Double.valueOf(in);
		double max = Double.valueOf(inMax);
		double min = Double.valueOf(inMin);
		int sendThis = (int) ((UPPER - LOWER)*(val-min)/(max-min)+LOWER);
		return sendThis;
	}
	

}
