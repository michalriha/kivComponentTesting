package cz.zcu.kiv.bp.unimocker;

/**
 * For throwing when the method has not been even mentioned in mocking scneario.
 * @author Michal
 */
public class UndefinedMethodInvocationException extends Exception {

	private static final long serialVersionUID = 1L;

	public UndefinedMethodInvocationException(String msg)
	{
		super(msg);
	}
}
