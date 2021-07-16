package Program;

class TreeNode<T extends Number>{
    private int height;
    private TreeNode<T> parent;
    private T key;
    private TreeNode<T> left;
    private TreeNode<T> right;

    public TreeNode(T key) {
        this.key = key;
    }

//    public Program.TreeNode() { }


    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public TreeNode<T> getParent() {
        return parent;
    }

    public void setParent(TreeNode<T> parent) {
        this.parent = parent;
    }

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

//    이게 있어야 Program.TreeNode 만 뽑아도 sout 에서 값이 나온다. 아니면 뽑을 때 node.getKey() 하거나.
//   parent 를 그냥 넣으면 안 된다. parent 가 다시 하위 toString() 을 불러서 StackOverflowError 발생. null check 도 해야 함

    @Override
    public String toString() {
        String strParent;
        if(null == parent){
            strParent="";
        } else {
            strParent= String.valueOf(parent.getKey());
        }
        return "Program.TreeNode{" +
                "height=" + height +
                ", parent=" + strParent +
                ", key=" + key +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
