package de.craftsarmy.modul.moduls;

import de.craftsarmy.modul.IHypeModul;
import de.craftsarmy.modul.moduls.networking.HypeNetworkManager;

public final class HypeNetworkingModul implements IHypeModul {

    private HypeNetworkManager networkManager;

    @Override
    public void onEnable() {
        this.networkManager = new HypeNetworkManager();
    }

    @Override
    public void onDisable() {

    }

    public HypeNetworkManager getNetworkManager() {
        return networkManager;
    }

}
