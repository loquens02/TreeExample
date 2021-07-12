import util.FileGenerator;
import util.FileReaderMy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static config.Config.*;

/**
 * java list int iterator https://www.daleseo.com/how-to-traverse-list-in-java/
 * beautify java object. https://codebeautify.org/javaviewer
 * maven 없이 log4j 가능? log4j example without maven https://stackoverflow.com/a/44701960
 * 동기: sout  일일이 찾아다니면서 끄기 싫음
 * https://stackoverflow.com/a/5081386 src/main/resources/ log4j.properties
 * 귀찮: 생각보다 너무 복잡한데;
 */
public class TreeProgram {

    public static void main(String[] args) {
        List<Integer> csvData = null; // mode MAIN 으로 두면 아래쪽 deleteNode 에서 Null Error 날 것
        FileGenerator fileGenerator = new FileGenerator();
        FileReaderMy fileReaderMy = new FileReaderMy();

        List<String> resultTreeWork = new ArrayList<>();
        List<String> deleteWorkResult = new ArrayList<>();


//        미할당 시 객체 초깃값이 null 이 아니더라. https://softwareengineering.stackexchange.com/a/257865
        TreeNode node = null;
        List<Integer> testInput= null;

        /**
         * TEST_CASE1 : deleteNode 가 null 인 경우가 없는데 compare 에서 false 난 경우
         * TEST_CASE2 : deleteNode 가 null 인 경우가 있고 compare 에서 false 난 경우
         * TEST_CASE3 : deleteNode 가 null 인 경우가 없고 compare 에서 true 난 경우. 즉, 멀쩡한 경우.
         */
        String mode = "CSV";
        switch (mode) {
            case "CSV":
//              csv file generate -> read -> insert to Tree
                System.out.println(mode + " mode < < < < ");
                fileGenerator.generateCSV(CSV_RAND_COUNT);
                csvData = fileReaderMy.readCSV(NAME_ALL);

                System.out.println("modeCSV csv to list: ");
                for (int input : csvData) {
                    //System.out.print(input+"\t");       // 값이 작을 때만 출력
                    node = insertNode(node, makeNode(input));
                }
                break;
            case "MAIN":
//              main rand Int -> insert to Tree. for test[treeSearch, delete, insert inner]
                System.out.println(mode + " mode < < < < ");
                node = insertNode(makeNode(9), makeNode(11));

                int count = MAIN_RAND_COUNT - 1;             // -1: for treeSearch test
                while (count > 0) {
                    int input = (int) (Math.random() * RAND_RANGE);
                    //System.out.print(input + "\t");       // 값이 작을 때만 출력

                    node = insertNode(node, makeNode(input));
                    count--;
                }
                break;
            case "TEST_CASE1":
                System.out.println(mode + " mode < < < <  이 것 은 테 스 트 입 니 다");

                testInput = Arrays.asList(26308, 91339, 22042, 74606, 71419, 7618, 12150, 41705, 71939, 34925);
                fileGenerator.generateCSV(testInput);
                for(int input: testInput){
                    System.out.print(input+"\t");
                    node = insertNode(node, makeNode(input));
                }
                break;
            case "TEST_CASE2":
                System.out.println(mode + " mode < < < <  이 것 은 테 스 트 입 니 다");
                testInput = Arrays.asList(19522, 50722, 89943, 16157, 19841, 99250, 74782, 4382, 44945, 92681);
                fileGenerator.generateCSV(testInput);
                for(int input: testInput){
                    System.out.print(input+"\t");
                    node = insertNode(node, makeNode(input));
                }
                break;
            case "TEST_CASE3":
                System.out.println(mode + " mode < < < <  이 것 은 테 스 트 입 니 다");
                testInput = Arrays.asList(86241, 32584, 25944, 16216, 81250, 81417, 83081, 75865, 21269, 64331);
                fileGenerator.generateCSV(testInput);
                for(int input: testInput){
                    System.out.print(input+"\t");
                    node = insertNode(node, makeNode(input));
                }
                break;

        }

        System.out.println();

        resultTreeWork = inorderTreeWork(node, resultTreeWork);

        System.out.println();
//        System.out.println("resultTreeWork total node: "+ resultTreeWork); //값이 작을 때만 출력


        System.out.println();
//        System.out.println(String.valueOf(node));     //값이 작을 때만 출력
        System.out.println("treeMinimum: " + String.valueOf(treeMinimum(node).getKey()));
        System.out.println("treeMaximum: " + String.valueOf(treeMaximum(node).getKey()));


//        tree 안에 저장된 data 를 파일입출력을 이용하여 input_result.csv 로 출력
        fileGenerator.listToCSV(NAME_INPUT_RESULT, resultTreeWork);


//        delete.csv 에 해당하는 데이터를 bst 에서 전부 삭제, 트리 안에 남아있는 데이터를 delete_result.csv 로 출력
        List<Integer> deleteList = fileReaderMy.readCSV(NAME_DELETE);
        for (int delElement : deleteList) {
            TreeNode<Integer> delNode = treeSearch(node, delElement);
//            System.out.println(delNode); //값이 작을 때만 출력

//            일단 nullPointer 거르려고 임시로 만들긴 했는데, 가끔 이런게 나옴. 왜일까??
            if (null != delNode) {
                node = deleteNode(node, delNode);
            } else {
                System.out.println("delElement: "+delElement);

            }
        }

//        재사용? ㄴㄴ- resultTreeWork = new ArrayList<>(); 메모리 재할당으로 초기화 원했는데, 컴파일러가 무시하는 듯.
        deleteWorkResult = inorderTreeWork(node, deleteWorkResult);
        fileGenerator.listToCSV(NAME_DELETE_RESULT, deleteWorkResult);


//        간이 test- compare list
//        csvData.sort(null);
//        System.out.println(NAME_ALL+" "+NAME_INPUT_RESULT+": "+csvData.equals(resultTreeWork));
//
//        deleteList.sort(null);
//        System.out.println(NAME_DELETE+" "+NAME_DELETE_RESULT+": "+deleteList.equals(deleteWorkResult));

//        compare csv

        System.out.println(NAME_ALL + " is same? with " + NAME_INPUT_RESULT + ": " + fileReaderMy.compareCSV(NAME_ALL, NAME_INPUT_RESULT));
//
        System.out.println(NAME_DELETE_RESULT+" is same? with "+NAME_DELETE_COMPARE+": "+ fileReaderMy.compareCSV(NAME_DELETE_RESULT,NAME_DELETE_COMPARE));


//      forTest- rand Int 라 자꾸 null 발생하길래
//        int oneKey= csvData.get((int)(CSV_RAND_COUNT/2));
//        System.out.println("deleteKey: "+oneKey);
//
//        node= deleteNode(node, treeSearch(node,oneKey));
//        inorderTreeWork(node, null);

//        못 찾으면 nullPointerException
//        System.out.println(String.valueOf(treeSearch(node,11).getKey()));
    }

    static TreeNode<Integer> makeNode(int key) {
        return new TreeNode(key);
    }

    //    기본은 동작함( 왼쪽자식 < 부모 < 오른쪽자식 일 때)
    static TreeNode<Integer> treeSearch(TreeNode<Integer> node, int key) {
        if (null == node || key == node.getKey()) {
            return node;
        }

        if (key < node.getKey()) {
            return treeSearch(node.getLeft(), key);
        } else {
            return treeSearch(node.getRight(), key);
        }
    }

    static public TreeNode<Integer> insertNode(TreeNode<Integer> treeRoot, TreeNode<Integer> inputNode) {
        TreeNode<Integer> lastHead = null;
        TreeNode<Integer> head = treeRoot;    // 서브트리의 머리. 처음에는 root. while 끝나면 null. treeRoot -> head

        while (head != null) {
            lastHead = head;         // 마지막으로 보던 서브트리의 머리. head -> lastHead
            if (inputNode.getKey() < head.getKey()) {
                head = head.getLeft();
            } else {
                head = head.getRight();
            }
        }

//        delete 구현하려니 parent 없으면 안 되겠다. 자기 자신이 부모의 left 인지 right 인지 알려면
        inputNode.setParent(lastHead);

//        treeRoot -> head -> lastHead 이기에, lastHead .set( inputNode ) 하면 treeRoot 밑에도 들어가는 것
//        처음에 빈 트리였을 때. treeRoot== null.
        if (lastHead == null) {
            treeRoot = inputNode;
        } else if (inputNode.getKey() < lastHead.getKey()) {
            lastHead.setLeft(inputNode);
        } else {
            lastHead.setRight(inputNode);
        }

        return treeRoot;
    }

    /**
     * 중위순회. root 호출 시 전체. 작은 값부터 출력
     * 재귀 함수의 결과를 list 로 받고 싶다면, 인자를 계속 전달해줘야.
     *
     * @param node
     * @param phase- null: 결과를 받고 싶지 않을 때
     *               List<Integer>: 결과를 list로 받고 싶을 때
     */
    static public List<String> inorderTreeWork(TreeNode<Integer> node, List<String> phase) {
        if (null != node) {
            inorderTreeWork(node.getLeft(), phase);
            if (null != node.getKey()) {
//                System.out.print("inorderTreeWork: "+String.valueOf(node.getKey())+ "\t");  //값이 작을 때만 출력
                if (null != phase) {
                    phase.add(Integer.toString(node.getKey()));
                }
            }
            inorderTreeWork(node.getRight(), phase);
        }
        return phase;
    }

    static public TreeNode<Integer> treeMinimum(TreeNode<Integer> node) {
        while (null != node.getLeft()) {
            node = node.getLeft();
        }
        return node;
    }

    static public TreeNode<Integer> treeMaximum(TreeNode<Integer> node) {
        while (null != node.getRight()) {
            node = node.getRight();
        }
        return node;
    }

    /**
     * @param treeRoot
     * @param deleteNode 지울 노드를 달랑 하나 따로 만들어서 넣으면 안 된다. 그럼 자식들이 다 null 됨. 꼭 treeSearch 로 넣자
     * @return
     */
    static public TreeNode<Integer> deleteNode(TreeNode<Integer> treeRoot, TreeNode<Integer> deleteNode) {

        if (null == deleteNode.getLeft()) {
            treeRoot = transplant(treeRoot, deleteNode, deleteNode.getRight());
        } else if (null == deleteNode.getRight()) {
            treeRoot = transplant(treeRoot, deleteNode, deleteNode.getLeft());
        } else {
            TreeNode<Integer> deleteNodeSuccessor = treeMinimum(deleteNode.getRight());      // 직후원소

            if (deleteNodeSuccessor.getParent() != deleteNode) {
                treeRoot = transplant(treeRoot, deleteNodeSuccessor, deleteNodeSuccessor.getRight());
                deleteNodeSuccessor.setRight(deleteNode.getRight());
                deleteNodeSuccessor.getRight().setParent(deleteNodeSuccessor);
            }

            treeRoot = transplant(treeRoot, deleteNode, deleteNodeSuccessor); //응? 반환값 안 받고 있었음;;
            deleteNodeSuccessor.setLeft(deleteNode.getLeft());
            deleteNodeSuccessor.getLeft().setParent(deleteNodeSuccessor);
        }
        return treeRoot;
    }

    /**
     * 사용방법?? 반환?
     *
     * @param treeRoot
     * @param subNode1
     * @param subNode2
     * @return
     */
    static public TreeNode<Integer> transplant(TreeNode<Integer> treeRoot, TreeNode<Integer> subNode1, TreeNode<Integer> subNode2) {
        if (null == subNode1.getParent()) {
            treeRoot = subNode2;
        } else if (subNode1 == subNode1.getParent().getLeft()) { // 내가 부모의 왼쪽자식이면 내 자리에 subNode2 넣기
            subNode1.getParent().setLeft(subNode2);
        } else {
            subNode1.getParent().setRight(subNode2);            // 내가 부모의 오른쪽자식이면 내 자리에 subNode2 넣기
        }

        if (null != subNode2) {
            subNode2.setParent(subNode1.getParent());           // 찜찜. 이걸로 되나?
        }
        return treeRoot;
    }

}




