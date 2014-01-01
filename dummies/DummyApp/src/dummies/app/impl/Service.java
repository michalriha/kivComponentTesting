package dummies.app.impl;

import java.util.Random;

import dummies.app.IService;

public class Service implements IService
{
	protected Service _ = this;
	
	protected Random rnd = new Random();
	
	public static final short MAX_RANDOM_STRING_LENGTH = 20;
	
	@Override
	public String getRandomString()
	{
		return _.getRandomString(_.rnd.nextInt(MAX_RANDOM_STRING_LENGTH) + 1);
	}

	@Override
	public String getRandomString(int length)
	{
		StringBuilder sb = new StringBuilder();
		
		int tmpCharCode;
		for (int i = 0; i < length; i++)
		{
			tmpCharCode = _.rnd.nextInt(50);
			sb.append(String.format("%c", tmpCharCode <= 25 ? tmpCharCode + 65 : tmpCharCode + 71));
		}
		
		return sb.toString();
	}

	@Override
	public int getRandomInt() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRandomInt(int maximum) {
		return _.rnd.nextInt(maximum + 1);
	}

	@Override
	public int getRandomInt(int minimum, int maximum) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getRandomFloat() {
		// TODO Auto-generated method stub
		return 0;
	}

}
