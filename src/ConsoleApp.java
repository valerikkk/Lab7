import commands.*;
import managers.*;
import java.util.Scanner;

public class ConsoleApp {
    public static void main(String[] args) {
        AllManagers all = AllManagers.createAllManagers();
        all.setCollectionManager(new CollectionManager());
        all.setScanner(new Scanner(System.in));
        HistoryCommand historyCommand = new HistoryCommand();
        DBWriter dbWriter = new DBWriter();
        DBParser dbParser= new DBParser();
        all.setDbWriter(dbWriter);
        all.setHistoryCommand(historyCommand);
        all.setCommandManager(new CommandManager(
                new Exit(),
                new Help(),
                new Show(),
                new Add(),
                new Info(),
                new UpdateById(),
                new RemoveLast(),
                new RemoveById(),
                new Clear(),
                new CountType(),
                new FilterType(),
                new MaxByVenue(),
                new ExecuteScript(),
                new Save(),
                new Reorder(),
                new History()));
        all.setConsoleManager(new ConsoleManager(all.getScanner(), all.getCommandManager()));
        dbParser.read();
        all.getConsoleManager().runFromConsole();
    }
}
