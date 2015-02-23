package Class;

public class RegNbFormatException extends Exception {
	/** Serial */
	private static final long serialVersionUID = 5538631979342018432L;

	public RegNbFormatException(String RegNb){
		super("Registration number not conform: " + RegNb);
	}
}
