package McNallyVisiCalc;
import java.util.Scanner;

public class FormulaCell extends Cell {
	
//What does this method do?	
public static String formatEquation(String value) {
		
		char columnLetter;
		int columnNumber;
		int rowNumber;
		String caseChange;
		
		//for all formulas
		String partOne = "";
		String operator;
		String partTwo = "";
		
		//used for avg and sum
		String temp2;
		String temp3;
		//still use columnNumebr and rowNumber for first indicated cell, these for the second
		int columnNumber2;
		int rowNumber2;
		double sum = 0;
		double numCells = 0;
		double avgValue;
		
			value = value.substring(value.indexOf("(") + 2);
			value = value.substring(0, value.indexOf(")") - 1);
		
			Scanner valueReader = new Scanner(value);
			
			//get first part then continue if there's more with part one as the first part always
			String temp1 = valueReader.next();
			if (temp1.equalsIgnoreCase("AVG") || temp1.equalsIgnoreCase("SUM")) {
			
				temp2 = valueReader.next();
				caseChange = temp2.toUpperCase();
				
				columnLetter = caseChange.charAt(0);
				rowNumber = Integer.parseInt(caseChange.substring(1)) - 1;
				
				//columnLetter to corresponding number
				columnNumber = Character.getNumericValue(columnLetter) - 10;
				
				//operator isn't actually used, will always be (-) but gotta set it to something anyway
				operator = valueReader.next();
				
				temp3 = valueReader.next();
				
				caseChange = temp3.toUpperCase();
				
				columnLetter = caseChange.charAt(0);
				rowNumber2 = Integer.parseInt(caseChange.substring(1)) - 1;
				
				//columnLetter to corresponding number
				columnNumber2 = Character.getNumericValue(columnLetter) - 10;
				
				//if both cells are in the same row, then go from first to second and add values going down the row
				if (rowNumber == rowNumber2) {
					
					for(int i = columnNumber; i <= columnNumber2; i++) {
						numCells++;
						sum += Double.parseDouble(VisiCalc.gridSheet.spreadsheet[rowNumber][i].toString());
					}
					
				} else if (columnNumber == columnNumber2) {
					
					for(int i = rowNumber; i <= rowNumber2; i++) {
						numCells++;
						sum += Double.parseDouble(VisiCalc.gridSheet.spreadsheet[i][columnNumber].toString());
					}
				} else {
					//if neither are equal (rectangular!)
					
				}
				
				if (temp1.equalsIgnoreCase("AVG")) {
					
					avgValue = sum / numCells;
					value = Double.toString(avgValue);
					
				} else if (temp1.equalsIgnoreCase("SUM")) {
					
					value = Double.toString(sum);
				}
				
			} else if (Character.isLetter(temp1.charAt(0))) {
				caseChange = temp1.toUpperCase();
				
				columnLetter = caseChange.charAt(0);
				rowNumber = Integer.parseInt(caseChange.substring(1)) - 1;
				
				//columnLetter to corresponding number
				columnNumber = Character.getNumericValue(columnLetter) - 10;
				
				//gets value of cell at location just parsed
				//check below for part two also
				partOne = VisiCalc.gridSheet.spreadsheet[rowNumber][columnNumber].toString();
				
			} else if (Character.isDigit(temp1.charAt(0))) {
				partOne = temp1;
			}
			
			//would making this a do/while reduce redundancy?
			while (valueReader.hasNext()) {
				
				operator = valueReader.next();
				
				//check if this is a cell!!!
				temp2 = valueReader.next();
				
				//yeah I know it's redundant but I would rather have this than a method with like a million parameters
				if (Character.isLetter(temp2.charAt(0))) {
					caseChange = temp2.toUpperCase();
					
					columnLetter = caseChange.charAt(0);
					rowNumber = Integer.parseInt(caseChange.substring(1)) - 1;
					
					//columnLetter to corresponding number
					columnNumber = Character.getNumericValue(columnLetter) - 10;
					
					//gets value of cell at location just parsed
					partTwo = VisiCalc.gridSheet.spreadsheet[rowNumber][columnNumber].toString();
					
				} else if (Character.isDigit(temp2.charAt(0)) || temp2.contains("-")) {
					partTwo = temp2;
				}
				
				partOne = operations(partOne, operator, partTwo);
				value = partOne;
			}
			
			
		valueReader.close();	
		return value;
	}
	
	public static String operations(String partOne, String operator, String partTwo) {
		double output = 0;
		double partOneN = Double.parseDouble(partOne);
		double partTwoN = Double.parseDouble(partTwo);
		
		if (operator.equals("+")) {
			
			output = partOneN + partTwoN;
			
		} else if (operator.equals("-")) {
			
			output = partOneN - partTwoN;
			
		} else if (operator.equals("*")) {
			
			output = partOneN * partTwoN;
			
		} else if(operator.equals("/")) {
			
			output = partOneN / partTwoN;
		}
		
		String out = Double.toString(output);
		
		return out;
	}
	
}
