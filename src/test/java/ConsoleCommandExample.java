import command.CommandExample;
import de.craftsarmy.modul.HypeModul;
import de.craftsarmy.modul.HypeModulManager;
import de.craftsarmy.modul.moduls.HypeConsoleModul;

public class ConsoleCommandExample {

    public static void main(String[] args) {
        HypeModulManager.enable(HypeModul.CONSOLE);
        HypeConsoleModul.getCommandManager().register("example", new CommandExample());
    }

}
