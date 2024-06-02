package managers;
import java.util.Scanner;

/**
 * The type All managers.
 */
public class AllManagers {
    /**
     * The ability to get Managers from every class in app.
     */
    static AllManagers managers;
    /**
     * The Command manager.
     */
    CommandManager commandManager;
    /**
     * The Collection manager.
     */
    CollectionManager collectionManager;
    /**
     * The Scanner.
     */
    Scanner scanner;
    /**
     * The Console manager.
     */
    ConsoleManager consoleManager;
    /**
     * The File writer.
     */
    /**
     * The History command.
     */
    HistoryCommand historyCommand;
    /**
     * The Path.
     */
    String path;
    /**
     * Instantiates a new All managers.
     */
    DBWriter dbWriter;
    AllManagers() {
    }

    /**
     * Create this class.
     *
     * @return the all managers
     */
    public static AllManagers createAllManagers() {
        if (managers == null) {
            managers = new AllManagers();
        }
        return managers;
    }

    /**
     * Gets command manager.
     *
     * @return the command manager
     */
    public CommandManager getCommandManager() {
        return commandManager;
    }

    /**
     * Sets command manager.
     *
     * @param commandManager the command manager
     */
    public void setCommandManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    /**
     * Gets collection manager.
     *
     * @return the collection manager
     */
    public CollectionManager getCollectionManager() {
        return collectionManager;
    }

    /**
     * Sets collection manager.
     *
     * @param collectionManager the collection manager
     */
    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Gets scanner.
     *
     * @return the scanner
     */
    public Scanner getScanner() {
        return scanner;
    }

    /**
     * Sets scanner.
     *
     * @param scanner the scanner
     */
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Gets console manager.
     *
     * @return the console manager
     */
    public ConsoleManager getConsoleManager() {
        return consoleManager;
    }

    /**
     * Sets console manager.
     *
     * @param consoleManager the console manager
     */
    public void setConsoleManager(ConsoleManager consoleManager) {
        this.consoleManager = consoleManager;
    }

    /**
     * Gets managers.
     *
     * @return the managers
     */
    public static AllManagers getManagers() {
        return managers;
    }

    /**
     * Gets history command.
     *
     * @return the history command
     */
    public HistoryCommand getHistoryCommand() {
        return historyCommand;
    }

    /**
     * Sets history command.
     *
     * @param historyCommand the history command
     */
    public void setHistoryCommand(HistoryCommand historyCommand) {
        this.historyCommand = historyCommand;
    }

    /**
     * Gets path.
     *
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets path.
     *
     * @param path the path
     */
    public void setPath(String path) {
        this.path = path;
    }

    public DBWriter getDbWriter() {
        return dbWriter;
    }

    public void setDbWriter(DBWriter dbWriter) {
        this.dbWriter = dbWriter;
    }
}
