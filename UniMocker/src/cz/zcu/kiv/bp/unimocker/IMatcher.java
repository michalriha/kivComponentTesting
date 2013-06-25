package cz.zcu.kiv.bp.unimocker;

public interface IMatcher<T>
{
	public boolean match(T obj1, T obj2);
}
