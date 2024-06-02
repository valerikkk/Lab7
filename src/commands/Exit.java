package commands;

/**
 * The type Exit.
 */
public class Exit extends Command {
    /**
     * Instantiates a new Exit.
     */
    public Exit() {
        super("exit", "завершить программу (без сохранения в файл)", "NO");
    }
    /**
     * Command to leave from app.
     */
    @Override
    public void run(){
        System.exit(0);
    }
}
