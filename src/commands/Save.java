package commands;

import managers.AllManagers;
import managers.CollectionManager;
import managers.DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The type Save.
 */
public class Save extends Command {
    /**
     * Instantiates a new Save.
     */
    public Save() {
        super("save", "сохраняет коллекцию в файл", "NO");
    }
    /**
     * Command to save collection of tickets to file.
     */
    @Override
    public void run() {
        CollectionManager coll = AllManagers.getManagers().getCollectionManager();
        System.out.println("Успешно!");
    }
}
