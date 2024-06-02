package exceptions;

public class ScriptRecursionException extends Exception{
    @Override
    public String getMessage() {
        return "Какая-то из команд в скрипте не валидная";
    }
}
