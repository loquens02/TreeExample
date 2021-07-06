
public class TreeProgram {

    public static void main(String[] args) {
        TreeNode node= treeBuilder(11);

//        simple test case
        node.insertNode(20).insertNode(8);
//        node.insertNode(8).insertNode(20);

        System.out.println(String.valueOf(node.getLeft().getKey()));
        System.out.println(String.valueOf(node.getRight().getKey()));


        System.out.println(String.valueOf(treeSearch(node,8).getKey()));
    }

    static TreeNode treeBuilder(int key){
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
}




