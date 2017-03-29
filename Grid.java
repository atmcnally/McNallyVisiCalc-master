package McNallyVisiCalc;

public class Grid {
	//grid is an array of cell objects, right? or is this just defining an array of cell objects within grid, not tied to it
	Cell[][] spreadsheet = new Cell[10][7];

	//public String value = "";
	
	public void fillGrid() {
		
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 7; j++) {
				
				spreadsheet[i][j] = new Cell();
				
			}
		}
	}
	
	public void printGrid() {
		//nested for loops print headers
		int l = 1;
		
		System.out.print("     |");
		System.out.print("     A     |");
		System.out.print("     B     |");
		System.out.print("     C     |");
		System.out.print("     D     |");
		System.out.print("     E     |");
		System.out.print("     F     |");
		System.out.print("     G     |");
		
		System.out.println();
		
		System.out.print("-----+");
		for(int k = 0; k < 7; k++) {
			System.out.print("-----------+");
		}
		
		System.out.println();
		
		for(int i = 0; i < 10; i++) {
			
			System.out.printf("%4d |", l);
			
			//when it calls spreadsheet[i][j] to print, the cell calls the toString method which prints value
			for(int j = 0; j < 7; j++) {
				System.out.printf("%10s |", spreadsheet[i][j]);

			}
			
			System.out.println();
			
			System.out.print("-----+");
			for(int k = 0; k < 7; k++) {
				System.out.print("-----------+");
			}
			
			System.out.println();
			l++;
		}
	}
}