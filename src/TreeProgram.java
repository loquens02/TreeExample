import util.FileGenerator;
import util.FileReaderMy;

import java.util.List;

import static config.Config.*;

/**
 * java list int iterator https://www.daleseo.com/how-to-traverse-list-in-java/
 */
public class TreeProgram {

    public static void main(String[] args) {
//        config
//        final int CSV_RAND_COUNT= 10;
//        final int MAIN_RAND_COUNT= 100;



//        미할당 시 객체 초깃값이 null 이 아니더라. https://softwareengineering.stackexchange.com/a/257865
        TreeNode node = null;

        String mode= "MAIN";
        switch (mode){
            case "CSV":
//              csv file generate -> read -> insert to Tree
                System.out.println(mode+" mode");
                FileGenerator.generateCSV(CSV_RAND_COUNT);
                List<Integer> csvData= FileReaderMy.readCSV();

                for(int tempKey : csvData){
                    System.out.print(tempKey);
                    System.out.print("\t");
                    node= insertNode(node, makeNode(tempKey));
                }
                break;
            case "MAIN":
//              main rand Int -> insert to Tree
//                for treeSearch test, delete test, insert inner check
                System.out.println(mode+" mode");
                node= insertNode(makeNode(9), makeNode(11));

                int count= MAIN_RAND_COUNT -1;             // -1: for treeSearch test
                while(count > 0){
                    int tempKey= (int)(Math.random()*RAND_RANGE);
                    System.out.print(tempKey);
                    System.out.print("\t");

                    node= insertNode(node, makeNode(tempKey));
                    count--;
                }
                break;
        }

        System.out.println();
        inorderTreeWork(node);

//        beautify java object. https://codebeautify.org/javaviewer
//        System.out.println(String.valueOf(node.getLeft().getKey()));
        System.out.println();
        System.out.println(String.valueOf(node));
        System.out.println("treeMinimum: "+String.valueOf(treeMinimum(node).getKey()));
        System.out.println("treeMaximum: "+String.valueOf(treeMaximum(node).getKey()));

//        지울 노드를 달랑 하나 따로 만들어서 넣으면 안 된다. 그럼 자식들이 다 null 이겠지
        node= deleteNode(node, treeSearch(node,11));
        inorderTreeWork(node);

//        못 찾으면 nullPointerException
//        System.out.println(String.valueOf(treeSearch(node,11).getKey()));
    }

    static TreeNode<Integer> makeNode(int key){
        return new TreeNode(key);
    }

//    기본은 동작함( 왼쪽자식 < 부모 < 오른쪽자식 일 때)
    static TreeNode<Integer> treeSearch(TreeNode<Integer> node, int key){
        if (null == node || key == node.getKey()){
            return node;
        }

        if (key < node.getKey()){
            return treeSearch(node.getLeft(), key);
        } else {
            return treeSearch(node.getRight(), key);
        }
    }

    static public TreeNode<Integer> insertNode(TreeNode<Integer> treeRoot, TreeNode<Integer> inputNode){
        TreeNode<Integer> lastHead= null;
        TreeNode<Integer> head= treeRoot;    // 서브트리의 머리. 처음에는 root. while 끝나면 null. treeRoot -> head

        while (head != null){
            lastHead= head;         // 마지막으로 보던 서브트리의 머리. head -> lastHead
            if (inputNode.getKey() < head.getKey()){
                head= head.getLeft();
            } else{
                head= head.getRight();
            }
        }

//        delete 구현하려니 parent 없으면 안 되겠다. 자기 자신이 부모의 left 인지 right 인지 알려면
        inputNode.setParent(lastHead);

//        treeRoot -> head -> lastHead 이기에, lastHead .set( inputNode ) 하면 treeRoot 밑에도 들어가는 것
//        처음에 빈 트리였을 때. treeRoot== null.
        if (lastHead==null) {
            treeRoot= inputNode;
        } else if (inputNode.getKey() < lastHead.getKey()){
            lastHead.setLeft(inputNode);
        } else {
            lastHead.setRight(inputNode);
        }

        return treeRoot;
    }

    /**
     * 중위순회. root 호출 시 전체. 작은 값부터 출력
     * @param node
     */
    static public void inorderTreeWork(TreeNode<Integer> node){

        if (null != node){
            inorderTreeWork(node.getLeft());
            System.out.print(String.valueOf(node.getKey()));
            System.out.print("\t");
            inorderTreeWork(node.getRight());
        }
    }

    static public TreeNode<Integer> treeMinimum(TreeNode<Integer> node){
        while (null != node.getLeft()){
            node= node.getLeft();
        }
        return node;
    }

    static public TreeNode<Integer> treeMaximum(TreeNode<Integer> node){
        while (null != node.getRight()){
            node= node.getRight();
        }
        return node;
    }

    static public TreeNode<Integer> deleteNode(TreeNode<Integer> treeRoot, TreeNode<Integer> deleteNode){

        if(null == deleteNode.getLeft()){
            treeRoot= transplant(treeRoot, deleteNode, deleteNode.getRight());
        } else if(null == deleteNode.getRight()){
            treeRoot= transplant(treeRoot, deleteNode, deleteNode.getLeft());
        } else {
            TreeNode<Integer> deleteNodeSuccessor= treeMinimum(deleteNode.getRight());      // 직후원소

            if(deleteNodeSuccessor.getParent() != deleteNode){
                treeRoot= transplant(treeRoot, deleteNodeSuccessor, deleteNodeSuccessor.getRight());
                deleteNodeSuccessor.setRight(deleteNode.getRight());
                deleteNodeSuccessor.getRight().setParent(deleteNodeSuccessor);
            }

            transplant(treeRoot, deleteNode, deleteNodeSuccessor);
            deleteNodeSuccessor.setLeft(deleteNode.getLeft());
            deleteNodeSuccessor.getLeft().setParent(deleteNodeSuccessor);
        }
        return treeRoot;
    }

    /**
     * 사용방법?? 반환?
     * @param treeRoot
     * @param subNode1
     * @param subNode2
     * @return
     */
    static public TreeNode<Integer> transplant(TreeNode<Integer> treeRoot, TreeNode<Integer> subNode1, TreeNode<Integer> subNode2){
        if(null == subNode1.getParent()){
            treeRoot= subNode2;
        } else if(subNode1 == subNode1.getParent().getLeft()){ // 내가 부모의 왼쪽자식이면 내 자리에 subNode2 넣기
            subNode1.getParent().setLeft(subNode2);
        } else {
            subNode1.getParent().setRight(subNode2);            // 내가 부모의 오른쪽자식이면 내 자리에 subNode2 넣기
        }

        if(null != subNode2){
            subNode2.setParent(subNode1.getParent());           // 찜찜. 이걸로 되나?
        }
        return treeRoot;
    }

}




