import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.*;

public class ConfigParser implements Map<String, String> {
    private final Map<String, String> configParserMap = new HashMap<>();
    private final String fileName;

    /**
     * Constructor to create new object of class ConfigParser
     * */
    public ConfigParser(String fileName) {
        this.fileName = fileName;
        String fileName1 = "src/main/java/" + this.fileName; //add relative path
        File configFile = new File(fileName1);
        String path;
        if (configFile.exists()) path = fileName1; // check file existence and handling file path type for command line
        else path = this.fileName;
        Path filePath = FileSystems.getDefault().getPath(path);

        parser(filePath);
    }

    /**
     * using the TRY RESOURCES:
     * Lines will be read while file still has content
     * Read file line by line using bufferedReader, split element to strings at every occurence of "="
     * and puts elements to a map of keys and values.
     * While reading, it checks for the heading "application", reads new line, splits elements
     * and appends application to all elements tagged "key" until an empty line is reached.
     * @param path
     * */

    private void parser(Path path) {
        try(BufferedReader configBuffer = new BufferedReader(new FileReader(path.toFile()))) {
            String[] data;
            while(configBuffer.ready()) {
                String input = configBuffer.readLine();
                if(input.equals("")) { //skip current iteration when empty line is reached
                    continue;
                }
                if (input.contains("application")) {
                    input = input.replace(input, "application.");
                    String nextInput;
                    while((nextInput = configBuffer.readLine()) != null && !nextInput.equals("")) {
                        data = nextInput.split("=");
                        String key = input + data[0];
                        //System.out.println(key);
                        String value = data[1];
                        if (!configParserMap.containsKey(key))
                            configParserMap.put(key, value);
                    }
                } else {
                    data = input.split("=");
                    String key = data[0];
                    String value = data[1];
                    configParserMap.put(key, value);
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Implement methods of Map abstract class
     * */
    public int size() {
        return configParserMap.size();
    }

    public boolean isEmpty() {
        return configParserMap.isEmpty();
    }

    public boolean containsKey(Object key) {
        return configParserMap.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return configParserMap.containsValue(value);
    }

    public String get(Object key) {
        return configParserMap.get(key);
    }

    public String put(String key, String value) {
        return configParserMap.put(key, value);
    }

    public String remove(Object key) {
        return configParserMap.remove(key);
    }

    public void putAll(Map<? extends String, ? extends String> m) {

    }

    public void clear() {
        configParserMap.clear();
    }

    public Set<String> keySet() {
        return configParserMap.keySet();
    }

    public Collection<String> values() {
        return configParserMap.values();
    }

    public Set<Entry<String, String>> entrySet() {
        return configParserMap.entrySet();
    }
}

