import java.io.*;

public class Main {
    public static void main(String[] args){
        ReadFile readFile = new ReadFile();

        readFile.readFromDefaultFile();
        readFile.sortedMap(readFile.getDict());
        readFile.outFreq(readFile.getDict());
        readFile.outMaxFreq(readFile.getDict());

        readFile.readUsersAbsPath("C:\\Users\\User\\IdeaProjects\\FileStat\\src\\text");
        readFile.sortedMap(readFile.getDict());
        readFile.outFreq(readFile.getDict());
        readFile.outMaxFreq(readFile.getDict());

        readFile.readUsersFilename("text");
        readFile.sortedMap(readFile.getDict());
        readFile.outFreq(readFile.getDict());
        readFile.outMaxFreq(readFile.getDict());

    }
}