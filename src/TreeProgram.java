import util.FileGenerator;

import java.util.ArrayList;

public class TreeProgram {

    public static void main(String[] args) {
//        config
        final int CSV_RAND_COUNT= 50;
        final int MAIN_RAND_COUNT= 8;

        FileGenerator.generateCSV(CSV_RAND_COUNT);

//        미할당 시 객체 초깃값이 null 이 아니더라. https://softwareengineering.stackexchange.com/a/257865
        TreeNode node = null;

//        node= insertNode(node, makeNode(9)); // for treeSearch test


        int count= MAIN_RAND_COUNT;
        while(count > 0){
            int tempKey= (int)(Math.random()*100);
            System.out.print(tempKey);
            System.out.print("\t");

            node= insertNode(node, makeNode(tempKey));
            count--;
        }

        System.out.println();
        inorderTreeWork(node);

//        beautify java object. https://codebeautify.org/javaviewer
//        System.out.println(String.valueOf(node.getLeft().getKey()));
        System.out.println();
        System.out.println(String.valueOf(node));
        System.out.println("treeMinimum: "+String.valueOf(treeMinimum(node).getKey()));
        System.out.println("treeMaximum: "+String.valueOf(treeMaximum(node).getKey()));


//        못 찾으면 nullPointerException
//        System.out.println(String.valueOf(treeSearch(node,9).getKey()));
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
        TreeNode<Integer> head= treeRoot;    // 서브트리의 머리. 처음에는 root. while 끝나면 null

        while (head != null){
            lastHead= head;         // 마지막으로 보던 서브트리의 머리
            if (inputNode.getKey() < head.getKey()){
                head= head.getLeft();
            } else{
                head= head.getRight();
            }
        }

//        parent 멤버 불필요. 이거 없이도 treeRoot 밑에 lastHead 가 연결이 된다!!
//        inputNode.parent= lastHead;

//        처음에 빈 트리였을 때. treeRoot== null.
        if (lastHead==null) {
            treeRoot= inputNode;
        }
        else if (inputNode.getKey() < lastHead.getKey()){
            lastHead.setLeft(inputNode);
        } else {
            lastHead.setRight(inputNode);
        }

        return treeRoot;
    }

//    중위순회. root 호출 시 전체. 작은 값부터 출력
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


}




