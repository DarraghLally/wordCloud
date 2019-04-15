package ie.gmit.sw;



//Import Libraries (not importing using '*' to save resources)
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

public class Menu {
	
	//Variable to hold user options
	private String inputOption;
	private String readThis;
	private int maxWords;
	private String saveFile;
	//Scanner, reading user menu choice
	private Scanner s = new Scanner(System.in);
	//Exit clause boolean
	boolean keepGoing = true;	
	//Instance of Parser class
	public Parser p = new Parser();


//////////////////////////////////////////////////////////////////////

	public void show() throws Exception{				
		while (keepGoing) {
			showMenu();
			inputOption = s.next();
			handle(inputOption);		
		}
	}//show()

//////////////////////////////////////////////////////////////////////
	
	private void handle(String option) throws Exception{ //IO for FileInputStream
		//Read in File
		if(option.equals("1")) {
			System.out.println("(Option 1) Enter file name:");
			readThis = s.next();
			File inFile = new File(readThis);
			InputStream readFile = new FileInputStream(inFile);
			InputStream ignoreFile = new FileInputStream("ignorewords.txt");	
						
			System.out.println("Enter MAX words for image:");
			maxWords = s.nextInt();
			System.out.println("Enter PNG save file name:");
			saveFile = s.next();
			//System.out.println("DEBUG: " + maxWords + " / " + saveFile);
			p.parse(readFile, ignoreFile, maxWords, saveFile); //Passing read files into Parse method
			
		}
		//Read URL
		else if(option.equals("2")) {
			System.out.println("(Option 2) Enter URL:\n");
			readThis = s.next();
			//File inFile = new File(readThis);
			InputStream readURL = new URL(readThis).openStream();
			InputStream ignoreFile = new FileInputStream("ignorewords.txt");	
						
			System.out.println("Enter MAX words for image:");
			maxWords = s.nextInt();
			System.out.println("Enter PNG save file name:");
			saveFile = s.next();
			p.parse(readURL, ignoreFile, maxWords, saveFile);
		}
		//Exit
		else if(option.equals("3")) {
			keepGoing = false;
			System.out.println("Exiting Program....\n");
		}
		//Wrong user input
		else {
			System.out.println("Wrong option choice, Please choose again...");
		}
	}//handle()

//////////////////////////////////////////////////////////////////////

	private void showMenu() {
		System.out.println("WORD CLOUD GENERATOR by Darragh Lally\nG00220290 - DSA Project\n\n");		
		System.out.println("(1) Choose for File Read");
		System.out.println("(2) Choose for URL Read");
		System.out.println("(3) Exit");
	}
}//Menu
