package commands;

import exceptions.NoSuchCommandException;
import managers.*;

/**
 * The type Execute script.
 */
public class ExecuteScript extends Command {
    /**
     * Instantiates a new Execute script.
     */
    public ExecuteScript(){
        super("execute_script", "вызывает и исполняет скрипт пользователя. Необходимо ввести путь к скрипту через пробел.", "YES");
    }
    /**
     * Command to execute script given user.
     */
    @Override
    public void run() {
        ConsoleManager consoleManager = AllManagers.getManagers().getConsoleManager();
        ScriptExecuter scriptExecuter = new ScriptExecuter();
        CommandManager commandManager = AllManagers.getManagers().getCommandManager();
        try{
            scriptExecuter.exec((consoleManager.getTokens())[1], commandManager);
        }catch (NoSuchCommandException ex){
            System.out.println(ex.getMessage());
        }catch (ArrayIndexOutOfBoundsException ex){
            System.out.println("Введите корректный путь");
        }
    }
}
