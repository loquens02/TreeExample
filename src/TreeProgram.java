import util.FileGenerator;

public class TreeProgram {

    public static void main(String[] args) {

        FileGenerator.generateCSV(50);

//        미할당 시 객체 초깃값이 null 이 아니더라. https://softwareengineering.stackexchange.com/a/257865
        TreeNode node = null;

        node= insertNode(node, makeNode(11));
        node= insertNode(node, makeNode(15));
        node= insertNode(node, makeNode(9));

        int count= 5;
        while(count > 0){
            int tempKey= (int)(Math.random()*100);
            System.out.println(tempKey);

            node= insertNode(node, makeNode(tempKey));
            count--;
        }

//        beautify java object. https://codebeautify.org/javaviewer
//        System.out.println(String.valueOf(node.getLeft().getKey()));
        System.out.println(String.valueOf(node));


        System.out.println(String.valueOf(treeSearch(node,9).getKey()));
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
                head= head.left;
            } else{
                head= head.right;
            }
        }

//        parent 멤버 불필요. 이거 없이도 treeRoot 밑에 lastHead 가 연결이 된다!!
//        inputNode.parent= lastHead;

//        처음에 빈 트리였을 때. treeRoot== null.
        if (lastHead==null) {
            treeRoot= inputNode;
        }
        else if (inputNode.getKey() < lastHead.getKey()){
            lastHead.left= inputNode;
        } else {
            lastHead.right= inputNode;
        }

        return treeRoot;
    }
}




