package cz.zcu.kiv.bp.unimocker;

/**
 * For throwing when the method has been mentioned in scenario,
 * but scenario does not contain possibility matching actual argument of real invocation. 
 * @author Michal
 */
public class UndefinedPossibilityException extends Exception {

	public UndefinedPossibilityException(String msg)
	{
		super(msg);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
