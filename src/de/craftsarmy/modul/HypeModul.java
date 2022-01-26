package de.craftsarmy.modul;

import de.craftsarmy.modul.moduls.HypeConsoleModul;
import de.craftsarmy.modul.moduls.HypeNetworkingModul;

public enum HypeModul {

    CONSOLE("console", new HypeConsoleModul()),
    NETWORKING("networking", new HypeNetworkingModul()),
    WEBSERVICE("webservice", null),
    MINECRAFT("minecraft", null);

    private final IHypeModul hypeModul;

    HypeModul(String name, IHypeModul hypeModul) {
        this.hypeModul = hypeModul;
    }

    public IHypeModul getHypeModul() {
        if (hypeModul == null)
            throw new NullPointerException("404 - HypeModul was not found!");
        else
            return hypeModul;
    }
}
