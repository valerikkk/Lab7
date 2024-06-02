package commands;

import managers.AllManagers;
import java.util.Map;

/**
 * The type Help.
 */
public class Help extends Command {

    /**
     * Instantiates a new Help.
     */
    public Help() {
        super("help", "вывести справку по доступным командам", "NO");
    }
    /**
     * Command to get help about commands.
     */
    @Override
    public void run() {
        Map<String, Command> map= AllManagers.createAllManagers().getCommandManager().getCommands();
        for (Command command: map.values()) {
            System.out.println(command.getNameInConsole() +": " + command.getDescription());
        }
    }
}