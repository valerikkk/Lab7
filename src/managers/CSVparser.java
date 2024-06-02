
package managers;

import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStreamReader;
public class CSVparser {

    public CSVparser() {
    }

    public String[] parse(String path) {
        String[] result = new String[0];
        try {
            FileInputStream file = new FileInputStream(path);
            InputStreamReader input = new InputStreamReader(file);
            char[] arr = new char[2000];
            char[] arr2 = new char[input.read(arr)];
            System.arraycopy(arr, 0, arr2, 0, arr2.length);
            String res = String.valueOf(arr2);
            result = res.split("\n");
            input.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return result;
    }
}

