package de.craftsarmy.command;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class HypeCommandManager {

    private final ConcurrentHashMap<String, IHypeCommand> commands = new ConcurrentHashMap<>();
    private final ConcurrentLinkedQueue<String> added = new ConcurrentLinkedQueue<>();

    public HypeCommandManager() {

    }

    public void register(String name, IHypeCommand hypeCommand) {
        commands.put(name, hypeCommand);
        added.add(name);
    }

    public void unregister(String name) {
        if (added.contains(name)) {
            added.remove(name);
            commands.remove(name);
        } else if (commands.containsKey(name))
            System.out.println("The command \"" + name + "\" was registered by the System! You can not remove it!");
        else
            System.out.println("The command \"" + name + "\" does not exists!");
    }

    public void perform(String name, String[] args) {
        if (commands.containsKey(name))
            commands.get(name).onCommand(name, args);
        else
            System.out.println("The command \"" + name + "\" does not exists!");
    }

    public ConcurrentHashMap<String, IHypeCommand> getCommands() {
        return commands;
    }

}
