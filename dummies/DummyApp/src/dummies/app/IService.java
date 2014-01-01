package dummies.app;

public interface IService
{
	public String getRandomString();
	
	public String getRandomString(int length);
	
	public int getRandomInt();
	
	public int getRandomInt(int maximum);
	
	public int getRandomInt(int minimum, int maximum);
	
	public float getRandomFloat();
}
