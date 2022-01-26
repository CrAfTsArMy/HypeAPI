package de.craftsarmy.modul.moduls;

import de.craftsarmy.command.HypeCommandManager;
import de.craftsarmy.listener.HypeConsoleListener;
import de.craftsarmy.modul.IHypeModul;

public final class HypeConsoleModul implements IHypeModul {

    private static HypeCommandManager commandManager;

    @Override
    public void onEnable() {
        commandManager = new HypeCommandManager();
        HypeConsoleListener.start();
    }

    @Override
    public void onDisable() {
        HypeConsoleListener.stop();
        commandManager = null;
    }

    public static HypeCommandManager getCommandManager() {
        return commandManager;
    }

}
