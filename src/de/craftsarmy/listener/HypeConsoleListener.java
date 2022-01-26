package de.craftsarmy.listener;

import de.craftsarmy.hyperapi.threading.HypeRunnable;
import de.craftsarmy.hyperapi.threading.HypeThread;
import de.craftsarmy.hyperapi.threading.IHypeTask;
import de.craftsarmy.modul.HypeModul;
import de.craftsarmy.modul.HypeModulManager;
import de.craftsarmy.modul.moduls.HypeConsoleModul;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HypeConsoleListener {

    private static HypeThread hypeThread;
    private static IHypeTask hypeTask;

    public static void start() {
        HypeRunnable runnable = new HypeRunnable();
        hypeThread = new HypeThread(runnable);
        hypeTask = hypeThread -> {
            String temp = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            if (HypeConsoleModul.getCommandManager() == null) {
                System.out.println("ERROR - HypeConsoleModul was not loaded correctly!");
                System.out.println("ERROR - Critical system error... Disabling HypeConsoleModule to avoid a System crash...");
                HypeModulManager.disable(HypeModul.CONSOLE);
                return;
            }
            while ((temp = reader.readLine()) != null) {
                if (HypeConsoleModul.getCommandManager() == null) {
                    System.out.println("ERROR - HypeConsoleModul was not loaded correctly!");
                    System.out.println("ERROR - Critical system error... Disabling HypeConsoleModule to avoid a System crash...");
                    HypeModulManager.disable(HypeModul.CONSOLE);
                    stop();
                    return;
                }
                if (!temp.isEmpty())
                    HypeConsoleModul.getCommandManager().perform(temp.split(" ")[0], temp.split(" "));
                else
                    System.out.println("Please enter a command!");
            }
        };
        runnable.register(hypeThread, hypeTask);
        hypeThread.start();
    }

    public static void stop() {
        hypeThread.stop();
        hypeThread.getHypeRunnable().unregister(hypeTask);
        hypeThread = null;
        hypeTask = null;
    }

    public static void main(String[] args) {
        //HypeModulManager.enable(HypeModul.CONSOLE);
        HypeConsoleListener.start();
    }

}
