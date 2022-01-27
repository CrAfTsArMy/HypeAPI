package command;

import de.craftsarmy.command.IHypeCommand;

import java.util.Arrays;

public class CommandExample implements IHypeCommand {

    @Override
    public void onCommand(String command, String[] args) {
        System.out.println("Used: " + command);
        System.out.println("Args: " + Arrays.toString(args));
        System.out.println();
    }

}
