package commands;

import managers.AllManagers;
import managers.HistoryCommand;
import java.util.Queue;

/**
 * The type History.
 */
public class History extends Command{
    /**
     * Instantiates a new History.
     */
    public History() {
        super("history", "вывести последние 11 команд без аргументов", "NO");
    }
    /**
     * Command to get history of last 11 command without parametres.
     */
    @Override
    public void run() {
        HistoryCommand historyCommand = AllManagers.createAllManagers().getHistoryCommand();
        Queue<String> commandQueue = historyCommand.getHistoryCommands();
        System.out.println(commandQueue);
    }
}
