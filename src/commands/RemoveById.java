package commands;

import managers.AllManagers;
import managers.CollectionManager;
import managers.ConsoleManager;
import managers.DBWriter;

/**
 * The type Remove by id.
 */
public class RemoveById extends Command{
    /**
     * Instantiates a new Remove by id.
     */
    public RemoveById() {
        super("remove_by", "удалить элемент из коллекции по его id. Необходимо ввести id через пробел.", "YES");
    }
    /**
     * Command to delete element for it id.
     */
    @Override
    public void run() {
        CollectionManager collectionManager = AllManagers.getManagers().getCollectionManager();
        ConsoleManager consoleManager = AllManagers.getManagers().getConsoleManager();
        try{
            collectionManager.removeById(Long.parseLong(consoleManager.getTokens()[1]));
        }catch (NumberFormatException ex){
            System.out.println("ID должен быть long и >=0");
        }catch (ArrayIndexOutOfBoundsException ex){
            System.out.println("Можно хотя бы попытаться ввести id");
        }
        DBWriter dbWriter = AllManagers.createAllManagers().getDbWriter();
        dbWriter.removeDB(Long.parseLong(consoleManager.getTokens()[1]));
    }
}
