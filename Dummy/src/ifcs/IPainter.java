package ifcs;

public interface IPainter
{
	boolean draw(String shape);
	
	boolean fill(int x, int y);
	
	boolean line(int startX, int startY, int endX, int ednY);
	
	String render(String text);
	
	void test(String prd);
}
