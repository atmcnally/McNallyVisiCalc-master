package McNallyVisiCalc;

public class TextCell extends Cell {

	public static String formatString(String value) {
		while (value.contains("\"")) {
		
			value = value.substring(value.indexOf("\"") + 1);
			value = value.substring(0, value.indexOf("\""));
		}
		
		return value;
	
	}
	
}
