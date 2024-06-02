package exceptions;

/**
 * The type No such command exception.
 */
public class NoSuchCommandException extends Exception{
    @Override
    public String getMessage() {
        return "Какая-то из команд в скрипте не валидная";
    }
}
