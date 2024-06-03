package managers;

import exceptions.NoSuchCommandException;
import exceptions.NullValueException;
import exceptions.ScriptRecursionException;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * The type Script executer.
 */
public class ScriptExecuter {
    /**
     * Instantiates a new Script executer.
     */
    public ScriptExecuter() {
    }
    ConsoleManager consoleManager = AllManagers.createAllManagers().getConsoleManager();
    /**
     * Execute script from given file.
     *
     * @param path           the path
     * @param commandManager the command manager
     * @throws NoSuchCommandException the no such command exception
     */
    public void exec(String path, CommandManager commandManager) throws NoSuchCommandException{
        String[] result;
        try {
            FileInputStream file = new FileInputStream(path);
            InputStreamReader input = new InputStreamReader(file);
            char[] arr = new char[1000];
            char[] arr2  = new char[input.read(arr)];
            System.arraycopy(arr, 0, arr2, 0, arr2.length);
            String res = String.valueOf(arr2);
            result = res.split("\r\n");
            for (int i=0; i<result.length; i++) {
                try {
                    consoleManager.setTokens(result[i].split(" "));
                    System.out.println(Arrays.toString(consoleManager.getTokens()));
                    System.out.println();
                    try {
                        if(result[i].equalsIgnoreCase("add")){
                            String[] addData = new String[8];
                            addData[0] = result[i+1];
                            addData[1] = result[i+2];
                            addData[2] = result[i+3];
                            addData[3] = result[i+4];
                            addData[4] = result[i+5];
                            addData[5] = result[i+6];
                            addData[6] = result[i+7];
                            addData[7] = result[i+8];
                            commandManager.callAddFromScript(addData);
                            i=i+8;
                        } else if (consoleManager.getTokens()[0].equalsIgnoreCase("update")) {
                            String[] addData = new String[8];
                            addData[0] = result[i+1];
                            addData[1] = result[i+2];
                            addData[2] = result[i+3];
                            addData[3] = result[i+4];
                            addData[4] = result[i+5];
                            addData[5] = result[i+6];
                            addData[6] = result[i+7];
                            addData[7] = result[i+8];
                            commandManager.callUpdateFromScript(addData, Long.parseLong(consoleManager.getTokens()[1]));
                            i=i+8;
                        } else{
                            commandManager.callCommand(consoleManager.getTokens()[0]);
                        }
                    }catch (IndexOutOfBoundsException e){
                        System.out.println("Требуется больше параметров в команду add");
                    }
                    if (consoleManager.getTokens().length > 1 && consoleManager.tokens[1].equals(path)) {
                        throw new ScriptRecursionException();
                    }
                } catch (NullPointerException e) {
                    throw new NoSuchCommandException();
                }catch (ScriptRecursionException e){
                    System.out.println("Скрипт не должен вызывать рекурсию");
                }
            }

        } catch (IOException e) {
            System.out.println("Не найден файл");
        }
    }

}
