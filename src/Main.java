import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws IOException {
        Map<String, Integer> dict = readFromDefaultFile();
        sortedMap(dict);
        outFreq(dict);
        outMaxFreq(dict);

        dict = readUsersAbsPath("C:\\Users\\User\\IdeaProjects\\FileStat\\src\\text");
        sortedMap(dict);
        outFreq(dict);
        outMaxFreq(dict);

        dict = readUsersFilename("text");
        sortedMap(dict);
        outFreq(dict);
        outMaxFreq(dict);

    }

    static Map<String,Integer> readUsersFilename(String path) throws IOException {
        String p = "C:\\Users\\User\\IdeaProjects\\FileStat\\src\\"+path;
        Path filePath = Paths.get(p);
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

        return dict;
    }

    static Map<String,Integer> readUsersAbsPath(String path) throws IOException {
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

        return dict;
    }

    static void outMaxFreq(Map<String, Integer> map){
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

    static void outFreq(Map<String,Integer> map){
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("Слово: " + entry.getKey());
            System.out.println("Частота: " + entry.getValue());
        }
    }

    static Map<String,Integer> readFromDefaultFile() throws IOException {
        if (!Files.exists(Path.of("C:\\Users\\User\\IdeaProjects\\FileStat\\src\\text"))) throw new FileNotFoundException();
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\User\\IdeaProjects\\FileStat\\src\\text"));
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

        return dict;
    }

    static <K, V> void sortedMap(Map<K,V> map){
        TreeMap<K, V> sorted = new TreeMap<>(map);
        System.out.println("Все слова в алфавитном порядке:");
        for (Map.Entry<K, V> entry : sorted.entrySet()) {
            System.out.println(entry.getKey());
        }
    }
}

class FileIsEmptyException extends FileNotFoundException{
}
