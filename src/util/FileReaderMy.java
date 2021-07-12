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


/**
 * java read csv https://stackabuse.com/reading-and-writing-csvs-in-java
 * java read csv https://jeong-pro.tistory.com/70
 * intellij line rearrange(ctrl alt L) https://www.jetbrains.com/help/idea/reformat-and-rearrange-code.html
 * java list string to int https://stackoverflow.com/a/37073454
 */
public class FileReaderMy {
    public List<Integer> readCSV(String fileName) {
        BufferedReader br = null;
        List<String> rows = new ArrayList<>();
        List<Integer> ret = new ArrayList<>();

        try {
            br = Files.newBufferedReader(Paths.get(fileName));
            String line;
//            1줄 이라고 생각해서 이렇게 짬
            while (((line = br.readLine()) != null)) {

                rows = Arrays.asList(line.split(","));
            }

            ret = rows.stream().map(Integer::parseInt).collect(Collectors.toList());
        } catch (FileNotFoundException e) {
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

    /**
     * 요구사항: 결과물 csv 끼리 비교한다
     * 둘 중 하나는 sort 가 안 되었는데? 검증용이니 sort ㄱㄱ
     * java sort list https://manorgass.tistory.com/60
     * java foreach 2 list https://stackoverflow.com/a/36662900
     *java iterator compare elements int https://www.javatpoint.com/how-to-compare-two-arraylist-in-java
     * @return true: same. false: different
     */
    public Boolean compareCSV(String fileName1, String fileName2) {
        List<Integer> comparing1= readCSV(fileName1);
        List<Integer> comparing2= readCSV(fileName2);

        comparing1.sort(null);
        comparing2.sort(null);

        return comparing1.equals(comparing2);
    }
}
