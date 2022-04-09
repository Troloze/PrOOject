package misc;

public class NotFoundException extends Exception {

	private static final long serialVersionUID = -8708743452119612274L;

	public String string;
	
	public NotFoundException(String str) {
		string = str;
	}
}
