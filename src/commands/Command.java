package commands;

/**
 * The type Command.
 */
public abstract class Command{
    private final String nameInConsole;
    private final String description;
    private final String isComposite;

    /**
     * Instantiates a new Command.
     *
     * @param nameInConsole the name in console
     * @param description   the description
     * @param typeCommand   the type command
     */
    public Command(String nameInConsole, String description, String typeCommand) {
        this.nameInConsole = nameInConsole;
        this.description = description;
        this.isComposite = typeCommand;
    }

    /**
     * Run command.
     */
    public abstract void run();

    /**
     * Gets name in console of command.
     *
     * @return the name in console
     */
    public String getNameInConsole() {
        return nameInConsole;
    }

    /**
     * Get description of command.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Make sure that the program is waiting for parameters.
     *
     * @return the string
     */
    public String isComposite() {
        return isComposite;
    }
}