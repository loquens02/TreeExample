package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * java make csv https://heavenly-appear.tistory.com/109
 * java mk dir https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=since201109&logNo=150144653679
 * java string join comma https://seunggabi.tistory.com/entry/JAVA-join-comma-by-ListString
 * java file delete(FileWriter에는 덮어쓰기가 없다) https://javacpro.tistory.com/27
 */
public class FileGenerator {

   static public void generateCSV(int count){
       String FILE_PATH= "./export";
       String NAME="randInt";
       List<String> randStrings= new ArrayList<>();
       BufferedWriter fw= null;

       try{
           File dir= new File(FILE_PATH);
           if(!dir.exists()){
               dir.mkdir();
           }

           // 덮어쓰기
           File file= new File(FILE_PATH+"/"+NAME+".csv");
           if( file.exists() ){
               if(file.delete()){
                   System.out.println("파일삭제 성공");
               } else {
                   System.out.println("파일삭제 실패");
               }
           }else {
               System.out.println("파일이 존재하지 않습니다.");
           }

           fw= new BufferedWriter((new FileWriter(FILE_PATH+"/"+NAME+".csv", true)));

           while(count > 0){
               String temp= Integer.toString((int)(Math.random()*100));
               randStrings.add(temp);
               count--;
           }
           String temps= String.join(",",randStrings); //.toString() => [1,2,3] form
           fw.write(temps);

           fw.flush(); // to file

       } catch(FileNotFoundException e){
           System.out.println("no File");
           e.printStackTrace();
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
