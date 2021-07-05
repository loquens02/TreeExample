
public class TreeProgram {

    public static void main(String[] args) {
        TreeNode<Integer> node= treeBuilder(11);

//        simple test case
        node.addNode(node, 20)
                .addNode(node, 8);
//        node.addNode(node, 8).addNode(node, 20);

        System.out.println(String.valueOf(node.getLeftChild()));
        System.out.println(String.valueOf(node.getRightChild()));


        System.out.println(String.valueOf(treeSearch(node,8)));
    }

    static TreeNode<Integer> treeBuilder(int key){
        return new TreeNode<>(key);
    }

//    기본은 동작함( 왼쪽자식 < 부모 < 오른쪽자식 일 때)
    static TreeNode<Integer> treeSearch(TreeNode<Integer> node, int key){
        if (null == node || null == node.getChildren() || key == node.getKey()){
//            TODO node.getChildren null 조건 위치 생각해봐야
            return node;
        }
        if (key < node.getKey()){
            return treeSearch(node.getLeftChild(), key);
        } else {
            return treeSearch(node.getRightChild(), key);
        }
    }
}




