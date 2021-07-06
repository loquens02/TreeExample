public class TreeNode<T extends Number>{
//    public T parent;
    T key;
    TreeNode<T> left;
    TreeNode<T> right;

    public TreeNode(T key) {
        this.key = key;
    }

//    public TreeNode() { }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
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



















