package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static config.Config.NAME_ALL;

/**
 * java read csv https://stackabuse.com/reading-and-writing-csvs-in-java
 * java read csv https://jeong-pro.tistory.com/70
 * intellij line rearrange(ctrl alt L) https://www.jetbrains.com/help/idea/reformat-and-rearrange-code.html
 * java list string to int https://stackoverflow.com/a/37073454
 */

public class FileReaderMy {
    static public List<Integer> readCSV() {
        BufferedReader br = null;
        List<String> rows= new ArrayList<>();
        List<Integer> ret= new ArrayList<>();

        try {
            br = Files.newBufferedReader(Paths.get(NAME_ALL));
            String line;
//            1줄 이라고 생각해서 이렇게 짬
            while (((line = br.readLine()) != null)) {

                rows = Arrays.asList(line.split(","));
            }

            ret = rows.stream().map(Integer::parseInt).collect(Collectors.toList());
        } catch (FileNotFoundException e){
            System.out.println("read- no File");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return ret;
    }

}
