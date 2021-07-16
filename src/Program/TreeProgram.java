package Program;

import util.FileGenerator;
import util.FileReaderMy;

import java.util.ArrayList;
import java.util.List;

import static config.Config.*;

public class TreeProgram {
    BinarySearch binarySearch= new BinarySearch();

    /**
     * @param mode [CSV_MODE: csv file 생성해서 tree 생성. MAIN_MODE: JAVA 에서 random 으로 돌려서 tree 생성_test 용]
     */
    public void bstProgram(String mode){
        List<Integer> csvData = null; // mode MAIN 으로 두면 아래쪽 binarySearch.deleteNode 에서 Null Error 날 것
        FileGenerator fileGenerator = new FileGenerator();
        FileReaderMy fileReaderMy = new FileReaderMy();

        List<String> resultTreeWork = new ArrayList<>();
        List<String> deleteWorkResult = new ArrayList<>();


//        미할당 시 객체 초깃값이 null 이 아니더라. https://softwareengineering.stackexchange.com/a/257865
        TreeNode node = null;

        switch (mode) {
            case CSV_MODE:
//              csv file generate -> read -> insert to Tree
                System.out.println(mode + " mode < < < < ");
                fileGenerator.generateCSV(CSV_RAND_COUNT);
                csvData = fileReaderMy.readCSV(NAME_ALL);

                System.out.println("modeCSV csv to list: ");
                for (int input : csvData) {
                    //System.out.print(input+"\t");       // 값이 작을 때만 출력
                    node = binarySearch.insertNode(node, binarySearch.createNode(input));
                }
                break;
            case MAIN_MODE:
//              main rand Int -> insert to Tree
                System.out.println(mode + " mode < < < < ");
                node = binarySearch.insertNode(binarySearch.createNode(9), binarySearch.createNode(11));

                int count = MAIN_RAND_COUNT - 1;             // -1: for binarySearch.treeSearch test
                while (count > 0) {
                    int input = (int) (Math.random() * RAND_RANGE);
                    //System.out.print(input + "\t");       // 값이 작을 때만 출력

                    node = binarySearch.insertNode(node, binarySearch.createNode(input));
                    count--;
                }
                break;

        }

        System.out.println();

        resultTreeWork = binarySearch.inorderTreeWork(node, resultTreeWork);

        System.out.println();
//        System.out.println("resultTreeWork total node: "+ resultTreeWork); //값이 작을 때만 출력


        System.out.println();
        System.out.println("Tree: "+String.valueOf(node));     //값이 작을 때만 출력
        System.out.println("treeMinimum: " + String.valueOf(binarySearch.treeMinimum(node).getKey()));
        System.out.println("treeMaximum: " + String.valueOf(binarySearch.treeMaximum(node).getKey()));


//        tree 안에 저장된 data 를 파일입출력을 이용하여 input_result.csv 로 출력
        fileGenerator.listToCSV(NAME_INPUT_RESULT, resultTreeWork);


//        delete.csv 에 해당하는 데이터를 bst 에서 전부 삭제, 트리 안에 남아있는 데이터를 delete_result.csv 로 출력
        List<Integer> deleteList = fileReaderMy.readCSV(NAME_DELETE);
        for (int delElement : deleteList) {
            TreeNode<Integer> delNode = binarySearch.treeSearch(node, delElement);
//            System.out.println(delNode); //값이 작을 때만 출력

//            일단 nullPointer 거르려고 임시로 만들긴 했는데, 가끔 이런게 나옴. 왜일까??
            if (null != delNode) {
                node = binarySearch.deleteNode(node, delNode);
            } else {
                System.err.println("delElement: "+delElement);
            }
        }

//        ArrayList 재사용 금지
        deleteWorkResult = binarySearch.inorderTreeWork(node, deleteWorkResult);
        fileGenerator.listToCSV(NAME_DELETE_RESULT, deleteWorkResult);


        System.out.println(NAME_ALL + " is same? with " + NAME_INPUT_RESULT + ": " + fileReaderMy.compareCSV(NAME_ALL, NAME_INPUT_RESULT));
//
        System.out.println(NAME_DELETE_RESULT+" is same? with "+NAME_DELETE_COMPARE+": "+ fileReaderMy.compareCSV(NAME_DELETE_RESULT,NAME_DELETE_COMPARE));
    }

    public void avlProgram(){

    }
}
