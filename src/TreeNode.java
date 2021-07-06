import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class TreeNode<T>{//<T extends Integer>{// implements Comparable<Number>{
//    public T parent;
    int key;
    public TreeNode<T> left;
    public TreeNode<T> right;

    public TreeNode(int key) {
        this.key = key;
    }

    //    새 노드 생성
    public TreeNode<T> makeNode(int  key){
        return new TreeNode<>(key);
    }



//    기존 노드가 있다면. 거기에 추가하고 싶다면.
    public TreeNode<T> insertNode(int  key){
        TreeNode<T> temp;
        TreeNode<T> newNode= makeNode(key);

//          '왼쪽자식 < 부모_node <= 오른쪽자식' 되도록
        if (smallerThanParent(key)){
            if(null == left){
                left= newNode;
            }
            temp= left;
        } else { // biggerOrSame ThanParent
            if(null == right){
                right= newNode;
            }
            temp= right;
        }

        return this;    // Chaining - https://stackoverflow.com/a/21180314
    }

    // this.key: 현재 노드. parent
    // key: 넣을 값
    boolean smallerThanParent(int key){
        return this.key > key ? TRUE : FALSE;
    }



    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }

//    이게 있어야 TreeNode 만 뽑아도 sout 에서 값이 나온다. 아니면 뽑을 때 node.getKey() 하거나.
    @Override
    public String toString() {
        return "TreeNode{" +
                "key=" + key +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}



















