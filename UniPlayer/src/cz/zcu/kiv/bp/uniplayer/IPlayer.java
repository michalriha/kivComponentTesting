package cz.zcu.kiv.bp.uniplayer;


public interface IPlayer
{
    public abstract void play() throws Exception;

    public abstract void stop();
    
    public void loadFile(String fileName) throws Throwable;

	public void diag();
}
