import java.util.*;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class TreeNode<T>{//<T extends Integer>{// implements Comparable<Number>{
//    public T left;
//    public T right;
//    public T parent;
    int key;
    ArrayList<TreeNode<T>> children;


    TreeNode(int key) {
        this.key = key;
        this.children = new ArrayList<>();
    }

    //    새 노드 생성
    public TreeNode<T> makeNode(int  key){
        return new TreeNode<>(key);
    }



//    기존 노드가 있다면. 거기에 추가하고 싶다면.
    TreeNode<T> addNode(TreeNode<T> node, int  key){
        if(null == node){
            System.out.println("기존 node가 null입니다");
            return null;
        }
        int temp;

        TreeNode<T> newNode= makeNode(key);

//          '왼쪽자식_children.get(0) < 부모_node <= 오른쪽자식_children.get(1)' 되도록
/*
    case A. 왼쪽 자식이 없다면
    case B. 오른쪽 자식이 없다면
 */
        if (smallerThanParent(key)){
            if(null == getLeftChild()){         // null 인 이유가 children이 null 일수도 있음
                children.set(0,newNode);        // TODO Error- IndexOutOfBoundsException
            }
//            temp= children.get(0).getKey();
        } else { // biggerThanParent
//            temp= children.get(1).getKey();
        }


        if(2> node.children.size()) {
            children.add(newNode);
        } else {

        }
        return this;    // Chaining - https://stackoverflow.com/a/21180314
    }

    boolean smallerThanParent(int key){
        return this.key < key ? TRUE : FALSE;
    }

//        TODO left child <= parent <= right child 순서로 재배열해야


    public int getKey() {
        return key;
    }

    public void setKey(int  key) {
        this.key = key;
    }

    public List<TreeNode<T>> getChildren() {
        return children;
    }

    // list의 첫 요소
    public TreeNode<T> getLeftChild() {
        if (null == children || 0==children.size()) {
            return null;
        }
        return children.get(0);
    }
    // list의 둘째 요소
    public TreeNode<T> getRightChild() {
        if (null == children || 2> children.size()) {
            return null;
        }
        return children.get(1);
    }


    public void setChildren(ArrayList<TreeNode<T>> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "key=" + key +
                ", children=" + children +
                '}';
    }


}



















