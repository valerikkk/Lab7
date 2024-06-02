package managers;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * The type History command.
 */
public class HistoryCommand {
    /**
     * The deque of History commands.
     */
    Deque<String> historyCommands = new ArrayDeque<>(11);

    /**
     * Add command to history.
     *
     * @param command the command
     */
    public void addCommand(String command){
        historyCommands.add(command);
    }

    /**
     * Gets history commands.
     *
     * @return the history commands
     */
    public Deque<String> getHistoryCommands() {
        return historyCommands;
    }
}
