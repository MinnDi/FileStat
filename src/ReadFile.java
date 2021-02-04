import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ReadFile {
    private Map<String, Integer> dict;

    public Map<String, Integer> getDict() {
        return dict;
    }

    private void readFile(String path) throws IOException {
        Path filePath = Paths.get(path);
        if (!Files.exists(filePath)) throw new FileNotFoundException();
        BufferedReader br = new BufferedReader(new FileReader(path));
        Map<String, Integer> dict = new HashMap<>();

        while (br.ready()){
            String str = br.readLine().toLowerCase().replaceAll("[^А-Яа-яA-Za-z0-9Ёё ]", "").replaceAll("\\s+", " ");
            String[] strArr = str.split(" ");

            for (String s : strArr) {
                if (dict.containsKey(s)){
                    dict.put(s, dict.get(s)+1);
                }
                else dict.put(s,1);
            }
        }

        br.close();
        if (dict.isEmpty()) throw new FileIsEmptyException();

        this.dict = dict;
    }

    void readUsersFilename(String path){
        String p = System.getProperty("user.dir") + File.separator +path;
        try {
            readFile(p);
        } catch (FileNotFoundException fe){
            System.out.println("File not found. Check your path");
            fe.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    void readUsersAbsPath(String path){
        try {
            readFile(path);
        }catch (FileNotFoundException fe){
            System.out.println("File not found. Check your path");
            fe.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void outMaxFreq(Map<String, Integer> map){
        int max = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue()>max) max = entry.getValue();
        }

        System.out.println("Слова с максимальной частотой: " + max);
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == max)
                System.out.println("Слово: " + entry.getKey());
        }
    }

    void outFreq(Map<String,Integer> map){
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("Слово: " + entry.getKey());
            System.out.println("Частота: " + entry.getValue());
        }
    }

    void readFromDefaultFile(){
        try {
            readFile("C:\\Users\\User\\IdeaProjects\\FileStat\\src\\text");
        }catch (FileNotFoundException fe){
            System.out.println("File not found. Check your path.");
            fe.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    <K, V> void sortedMap(Map<K,V> map){
        TreeMap<K, V> sorted = new TreeMap<>(map);
        System.out.println("Все слова в алфавитном порядке:");
        for (Map.Entry<K, V> entry : sorted.entrySet()) {
            System.out.println(entry.getKey());
        }
    }
}


class FileIsEmptyException extends FileNotFoundException{
}

