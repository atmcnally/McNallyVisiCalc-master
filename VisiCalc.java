package McNallyVisiCalc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class VisiCalc {
	//what is safe to make static?? everything
	public static String optionResult = "";
	public static Grid gridSheet = new Grid();
	public static File temporary = new File("temp.txt");
	public static Scanner console = new Scanner(System.in);
	
	public static void main(String[] args) throws FileNotFoundException {
		PrintStream saver = new PrintStream(temporary);
		
		gridSheet.fillGrid();
		//sets .value of spreadsheet at E3 to 5
		//gridSheet.spreadsheet[2][4].value = "5"; 
		
        String input = "";
        
	    //while loop for input
        while (!(input.equals("quit"))) {
        	//takes a line of input
        	input = console.nextLine();
        
        	processCommand(input);
		
        	//save input to temp file
        	saver.println(input);
        	
        	//start each input on a new line
        	System.out.println();
        
        }
	}
        
	//input goes to cell and then to grid??
	
		//only has console to handle closing it for quit
        public static void processCommand(String input) throws FileNotFoundException {
        	
        	input = input.toLowerCase();
            //creates scanner for input after first input received
        	Scanner inputScanner = new Scanner(input);
        	String fileName;
        	String firstFour;
        	
        	
        	//parse out strings in quotes first?
        	
        	if (input.length() > 4 && (input.contains("print") || input.contains("load") || input.contains("save") || input.equalsIgnoreCase("clear"))) {
        		
        		//excludes space since substring is not inclusive
        		firstFour = input.substring(0, 4);
        		fileName = input.substring(5);
        		
        		if (input.equalsIgnoreCase("print")) {
             	   
           			gridSheet.printGrid();
           		
           	   } else if (firstFour.equalsIgnoreCase("load")) {
        			
           		   //cant quit from load file- FIX THIS
        			processFile(fileName);
              	   
                } else if (firstFour.equalsIgnoreCase("save")) {
                	
                	System.out.println(fileName);
                	File saveFile = new File(fileName);
                	
                	//beautiful code that does not work
                		//transfer from temp file to file of specified name
                		//System.out.println(temporary.renameTo(saveFile));
                	
                	//stream to that file
                	PrintStream saveWriter = new PrintStream(saveFile);
                	
                	//scanner reads from temp to new file
                	Scanner tempReader = new Scanner(temporary);
                			
                	while(tempReader.hasNextLine()) {
                		saveWriter.println(tempReader.nextLine());
                	}
                	
                	System.out.println("Saved as " + fileName);
                } else if(input.equalsIgnoreCase("clear")) {
             	   
             	   for(int i = 0; i < 10; i++) {
            			
             		   for(int j = 0; j < 7; j++) {
            				gridSheet.spreadsheet[i][j].value = "          ";

            				}
            			}
             	   
                }
        	} else if (input.equalsIgnoreCase("help")) {
               //help menu contents
               System.out.println("please write the help menu i am so lost");
               
           } else if (input.equalsIgnoreCase("quit")) {
                 //quit option
                
                   System.out.println("Thanks for using VisiCalc!");
                   console.close();
                   
                   //need to get rid of last else or otherwise figure out how to get around closed scanner- do while scanner is open?
           } else {
        	   
        	   //feed input to cell, store as equations and parse there
        	   Cell.parse(input, gridSheet);
        	   
        	   //store as what was typed in, when printing do the equations etc.
           }
           //else {
        	 //  System.out.println(input);
           //}
        	
       }

	public static void processFile(String fileName) throws FileNotFoundException {
		
		File loadFile = new File(fileName);
		
		//file name is 5 to end, so includes the .txt
		//assuming same directory
		Scanner fileScan = new Scanner(loadFile);
		
		String input = "";
		
		//could use do while except might be nothing in file
		while (fileScan.hasNextLine() && !input.equals("quit")) {
			input = fileScan.nextLine();
			processCommand(input);
			
		}
		
	}
	
}