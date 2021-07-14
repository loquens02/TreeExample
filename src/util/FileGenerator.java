package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static config.Config.*;

/**
 * java make csv https://heavenly-appear.tistory.com/109
 * java mk dir https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=since201109&logNo=150144653679
 * java string join comma https://seunggabi.tistory.com/entry/JAVA-join-comma-by-ListString
 * java file delete(FileWriter 에는 덮어쓰기가 없다) https://javacpro.tistory.com/27
 */
public class FileGenerator {

    public void generateCSV(int count){

        List<String> allDataList= new ArrayList<>();
        List<String> evenDataList= new ArrayList<>();
        List<String> oddDataList= new ArrayList<>();
        BufferedWriter fw= null;

        try{
            File dir= new File(FILE_PATH);
            if(!dir.exists()){
                dir.mkdir();
            }

            // 덮어쓰기
            fileDelete(NAME_ALL);
            fileDelete(NAME_DELETE);
            fileDelete(NAME_DELETE_COMPARE);
            fileDelete(NAME_DELETE_RESULT);

            fileDelete(NAME_INPUT_RESULT);



            while(count > 0){
                String randStr= Integer.toString((int)(Math.random()*RAND_RANGE));
                if(0 == count % 2){
                    evenDataList.add(randStr);
                } else {
                    oddDataList.add(randStr);
                }
                allDataList.add(randStr);
                count--;
            }


//           파일 생성
            listToCSV(NAME_ALL,allDataList);
            listToCSV(NAME_DELETE,evenDataList);
            listToCSV(NAME_DELETE_COMPARE,oddDataList);


        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try{
                if(fw != null){
                    fw.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }



    public void listToCSV(String fileName, List<String> dataList) {
       BufferedWriter fw= null;
       try {
           fw = new BufferedWriter((new FileWriter(fileName, true)));
           String genStrings = String.join(",", dataList); //.toString() => [1,2,3] form

           fw.write(genStrings);
           fw.flush(); // to file
       } catch(IOException e) {
           System.out.println("generate- no File");
           e.printStackTrace();
       } finally {
           try{
               if(fw != null){
                   fw.close();
               }
           }catch(IOException e){
               e.printStackTrace();
           }
       }

   }


   void fileDelete(String fileName){
       File file= new File(fileName);
       if( file.exists() ){
           if(file.delete()){
               System.out.println("Existed file Delete Success !");
           } else {
               System.out.println("Existed file Delete Fail..");
           }
       }else {
           System.out.println("file not exist");
       }
   }

    /**
     * foreach index java (index++ 이 저렴하다고 함) https://twpower.github.io/54-get-index-in-java-iterator
     * @param inputList
     */
    public void generateCSV(List<Integer> inputList){
        if(null == inputList){
            return;
        }

        List<String> allDataList= new ArrayList<>();
        List<String> evenDataList= new ArrayList<>();
        List<String> oddDataList= new ArrayList<>();

        BufferedWriter fw= null;

        try{
            File dir= new File(FILE_PATH);
            if(!dir.exists()){
                dir.mkdir();
            }

            // 덮어쓰기
            fileDelete(NAME_ALL);
            fileDelete(NAME_DELETE);
            fileDelete(NAME_DELETE_COMPARE);
            fileDelete(NAME_DELETE_RESULT);

            fileDelete(NAME_INPUT_RESULT);


            int index= 0;
            for(int input: inputList){
                String inputStr= Integer.toString(input);
                if(0 == index % 2){
                    evenDataList.add(inputStr);
                } else {
                    oddDataList.add(inputStr);
                }
                allDataList.add(inputStr);
                index++;
            }


//           파일 생성
            listToCSV(NAME_ALL,allDataList);
            listToCSV(NAME_DELETE,evenDataList);
            listToCSV(NAME_DELETE_COMPARE,oddDataList);


        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try{
                if(fw != null){
                    fw.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }




}
