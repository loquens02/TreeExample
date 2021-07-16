package Program;

import java.util.List;

public abstract class BinaryTree {
    TreeNode<Integer> createNode(int key) {
        return new TreeNode(key);
    }

    //    왼쪽자식 < 부모 < 오른쪽자식 일 때 작동
    TreeNode<Integer> treeSearch(TreeNode<Integer> node, int key) {
        if (null == node || key == node.getKey()) {
            return node;
        }

        if (key < node.getKey()) {
            return treeSearch(node.getLeft(), key);
        } else {
            return treeSearch(node.getRight(), key);
        }
    }

    /**
     * 중위순회. root 호출 시 전체. 작은 값부터 출력
     * 재귀 함수의 결과를 list 로 받고 싶다면, 인자를 계속 전달해줘야.
     * int to String
     *
     * @param node
     * @param phase- null: 결과를 받고 싶지 않을 때
     *               List<Integer>: 결과를 list로 받고 싶을 때
     */
    public List<String> inorderTreeWork(TreeNode<Integer> node, List<String> phase) {
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

    public TreeNode<Integer> treeMinimum(TreeNode<Integer> node) {
        while (null != node.getLeft()) {
            node = node.getLeft();
        }
        return node;
    }

    public TreeNode<Integer> treeMaximum(TreeNode<Integer> node) {
        while (null != node.getRight()) {
            node = node.getRight();
        }
        return node;
    }

    abstract public TreeNode<Integer> insertNode(TreeNode<Integer> treeRoot, TreeNode<Integer> inputNode);
    abstract public TreeNode<Integer> deleteNode(TreeNode<Integer> treeRoot, TreeNode<Integer> deleteNode);

}
