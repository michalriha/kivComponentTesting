package cz.zcu.kiv.bp.unimocker;

import java.util.Arrays;

public class ArrayMatcher implements IMatcher<Object[]>
{
	@Override
	public boolean match(Object[] obj1, Object[] obj2)
	{
		return Arrays.deepEquals(obj1, obj2);
	}
}
