/*
 * Menu class, where the user options are.
 * handle method deals with the main menu option
 * User choices passed into parse method from Parser class 
 */

package ie.gmit.sw;

//Import Libraries
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

public class Menu {
	
	//Variable to hold user options
	private String inputOption;
	private String readThis;
	private int maxWords;
	private String saveFile;
	
	private Scanner s = new Scanner(System.in); //Scanner, reading user menu choice	
	boolean keepGoing = true; //Exit clause boolean
	public Parser p = new Parser(); //Instance of Parser class

	//show method: display menu, prompt for user choice, pass choice into handle method
	public void show() throws Exception{				
		while (keepGoing) {
			showMenu();
			inputOption = s.next();
			handle(inputOption);		
		}
	}//show()

	//handle method: Taking users menu choice and using either file or URL
	private void handle(String option) throws Exception{ //IO for FileInputStream

		//Read File
		if(option.equals("1")) {
			
			//Prompt and read user input for a file with .txt extension
			System.out.println("(Option 1) Enter file name:");
			readThis = s.next();
			InputStream readFile = new FileInputStream(readThis); //Only difference between option 1 (file read) and option 2 (URL read)
			InputStream ignoreFile = new FileInputStream("ignorewords.txt"); //Hard coded from project
			
			//User input for max words, must be greater than 1
			do { 
				System.out.println("Enter MAX words for image:\ngreater than 1\n");
				maxWords = s.nextInt();
			}while(maxWords<2);
			
			//Prompt and read user save name. Must include .png extension
			do {
				System.out.println("Enter PNG save file name:\nMust include .png extension\n");
				saveFile = s.next();
			}while(!saveFile.contains(".png"));
			
			//Pass file/URL, ignore file, max words and save file name into my parse method
			p.parse(readFile, ignoreFile, maxWords, saveFile);			
		}//if
		
		//Read URL
		else if(option.equals("2")) {
			
			//Prompt and read user input for URL, full URL needed
			System.out.println("(Option 2) Enter URL:\n");
			readThis = s.next();
			InputStream readURL = new URL(readThis).openStream();
			InputStream ignoreFile = new FileInputStream("ignorewords.txt");	
			
			//User input for max words, must be greater than 1
			do { 
				System.out.println("Enter MAX words for image:");
				maxWords = s.nextInt();
			}while(maxWords<2);
			
			//Prompt and read user save name. Must include .png extension
			do {
				System.out.println("Enter PNG save file name:\nMust include .png extension\n");
				saveFile = s.next();
			}while(!saveFile.contains(".png"));
			
			//Pass file/URL, ignore file, max words and save file name into my parse method
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
