public class Solution {

    private int[] preorder;
    private Map<Integer, Integer> map;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length != inorder.length) {
            throw new RuntimeException("数据有问题");
        }
        this.preorder = preorder;
        this.map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return build(0, preorder.length - 1, 0, inorder.length - 1);
    }


    private TreeNode build(int pLeft, int pRight, int iLeft, int iRight) {
        if ( > pRight || iLeft > iRight) {
            return null;
        }
        int pivot = preorder[];
        TreeNode root = new TreeNode(pivot);
        int pivotIndex = map.get(pivot);
        root.left = build( + 1, pivotIndex - iLeft + ,
                iLeft, pivotIndex - 1);
        root.right = build(pivotIndex - iLeft +  + 1, pRight,
                pivotIndex + 1, iRight);
        return root;
    }
}