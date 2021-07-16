package Program;

/**
 * 3곳에서 int to String 이유? 나중에 CSV에 넣을 때 String.join() 하려고 [왔다갔다]
 */
public class BinarySearch extends BinaryTree {

    @Override
    public TreeNode<Integer> insertNode(TreeNode<Integer> treeRoot, TreeNode<Integer> inputNode) {
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
     * @param treeRoot
     * @param deleteNode 지울 노드를 달랑 하나 따로 만들어서 넣으면 안 된다. 그럼 자식들이 다 null 됨. 꼭 treeSearch 로 넣자
     * @return
     */
    @Override
    public TreeNode<Integer> deleteNode(TreeNode<Integer> treeRoot, TreeNode<Integer> deleteNode) {

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
    public TreeNode<Integer> transplant(TreeNode<Integer> treeRoot, TreeNode<Integer> subNode1, TreeNode<Integer> subNode2) {
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
