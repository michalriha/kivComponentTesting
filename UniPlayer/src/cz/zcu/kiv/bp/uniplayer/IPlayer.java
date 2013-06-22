package cz.zcu.kiv.bp.uniplayer;

import cz.zcu.kiv.bp.uniplayer.bindings.IScenario;


public interface IPlayer
{
    public abstract void play(IScenario scenario) throws Exception;

    public abstract void stop();
}
