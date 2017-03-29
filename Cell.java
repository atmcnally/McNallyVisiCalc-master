package McNallyVisiCalc;

public class Cell {
	//for standard cells- not date, formula, or text
	public String value;
	public String dispValue = "";

	public String toString() {
		
		//gotta write the methods for executing code here i think, input is stored as value of the cells so when printing value, return code that's been worked on
		//makes sure cells update, keeps from overwriting existing value
		
		if (value.contains("\"")) {
			dispValue = TextCell.formatString(value);
		} else if (value.contains("(") && value.contains(")")) {
			dispValue = FormulaCell.formatEquation(value);
		} else {
			dispValue = value;
		}
		
		//substring always last!!!!
		//if cell contents too long, substring for printing to less than 10 characters
		if(dispValue.length() > 10) {
			dispValue = dispValue.substring(0, 10);
		}
		
		return dispValue;
		
	}
	
	//turn into array of cell objects
	//string field
	//print grid method should get all cells and print that field
	
	public Cell() {
	
		//sets default value
		value = "          ";
		
	}
	
	//import input
	//is this ok to be static??
	public static void parse(String input, Grid gridSheet) {
		
		//these are declared for all, but are used differently in each input type
		//so parse differently to get these values but code for storing is the same
		
		char columnLetter;
		int columnNumber;
		int rowNumber;
		String caseChange;
		String indicatedCell;
		String contents;
		
		
		if(input.contains("=")) {
			indicatedCell = input.substring(0, input.indexOf("=") - 1);
			contents = input.substring(input.indexOf("=") + 2);
			
			//indicatedCell is what input is to input print
			caseChange = indicatedCell.substring(0, 1).toUpperCase();
			
			columnLetter = caseChange.charAt(0);
			rowNumber = Integer.parseInt(indicatedCell.substring(1)) - 1;
			
			//columnLetter to corresponding number
			columnNumber = Character.getNumericValue(columnLetter) - 10;
			
			if (rowNumber >= 0 && rowNumber <= 10 && columnNumber >= 0 && columnNumber <= 7) {
				
				gridSheet.spreadsheet[rowNumber][columnNumber].value = contents;
				
			}
			
		} else if (input.length() < 4 && input.length() >= 2) {
			
			//INPUT PRINT
			//what this part needs to do is print out the input from the specified cell
			//assumes wither two or three chars are entered
			
			caseChange = input.substring(0, 1).toUpperCase();
			
			columnLetter = caseChange.charAt(0);
			rowNumber = Integer.parseInt(input.substring(1)) - 1;
			
			//columnLetter to corresponding number
			columnNumber = Character.getNumericValue(columnLetter) - 10;
			
			System.out.println(gridSheet.spreadsheet[rowNumber][columnNumber].value);
			
		} else if (input.contains("clear")) {
			contents = input.substring(input.indexOf(" ") + 1);
			
			caseChange = contents.substring(0, 1).toUpperCase();
			
			columnLetter = caseChange.charAt(0);
			rowNumber = Integer.parseInt(contents.substring(1)) - 1;
			
			//columnLetter to corresponding number
			columnNumber = Character.getNumericValue(columnLetter) - 10;
			
			gridSheet.spreadsheet[rowNumber][columnNumber].value = "          ";
			
		}
			/*implement error checking here later
			 else {
				
				//error
			} */
			
			//store input as field of cell object, value that prints is different
	}
	
	//parses 2-3 character string into rowNumber and columnNumber to save input there
	
	/*
	public void splitLocation(String input) {
		
		String caseChange = input.substring(0, 1).toUpperCase();
		
		columnLetter = caseChange.charAt(0);
		rowNumber = Integer.parseInt(input.substring(1));
		
		//columnLetter to corresponding number
		columnNumber = Character.getNumericValue(columnLetter) - 65;
		
		if (rowNumber >= 0 && rowNumber <= 10 && columnNumber >= 0 && columnNumber <= 7) {
			
			gridSheet.spreadsheet[rowNumber - 1][columnNumber - 1].value = input;
			
		}
		
	}
	
	*/
	
}

//row then column

//spreadsheet[0][j] = 1 row
//spreadsheet[1][j] = 2 row
//spreadsheet[2][j] = 3 row
//spreadsheet[3][j] = 4 row
//spreadsheet[4][j] = 5 row
//spreadsheet[5][j] = 6 row
//spreadsheet[6][j] = 7 row
//spreadsheet[7][j] = 8 row
//spreadsheet[8][j] = 9 row
//spreadsheet[9][j] = 10 row

//spreadsheet[i][0] = A column
//spreadsheet[i][1] = B column
//spreadsheet[i][2] = C column
//spreadsheet[i][3] = D column
//spreadsheet[i][4] = E column
//spreadsheet[i][5] = F column
//spreadsheet[i][6] = G column