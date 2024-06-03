package commands;

import managers.AllManagers;
import managers.DBWriter;

import java.util.Vector;

/**
 * The type Clear.
 */
public class Clear extends Command {
    /**
     * Instantiates a new Clear.
     */
    public Clear() {
        super("clear", "очистить коллекцию", "NO");
    }
    /**
     * Command to delete all elements from collection.
     */
    @Override
    public void run() {
        DBWriter dbWriter = AllManagers.createAllManagers().getDbWriter();
        dbWriter.clearDB();
        AllManagers.getManagers().getCollectionManager().setCollection(new Vector<>());
    }
}
