import Program.TreeProgram;

import static config.Config.CSV_MODE;

/**
 * java list int iterator https://www.daleseo.com/how-to-traverse-list-in-java/
 * beautify java object. https://codebeautify.org/javaviewer
 * maven 없이 log4j 가능? log4j example without maven https://stackoverflow.com/a/44701960
 * 동기: sout  일일이 찾아다니면서 끄기 싫음
 * https://stackoverflow.com/a/5081386 src/main/resources/ log4j.properties
 * 귀찮: 생각보다 너무 복잡한데;
 */
public class Main {

    public static void main(String[] args) {
        TreeProgram treeProgram= new TreeProgram();
        treeProgram.bstProgram(CSV_MODE);

    }



}




