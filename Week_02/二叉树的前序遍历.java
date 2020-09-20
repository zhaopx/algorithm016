class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> rst=new ArrayList<>();
        preorder(root,rst);
        return rst;
    }
    public void preorder(TreeNode root, List<Integer> rst){
        if(root==null) return;
        rst.add(root.val);
        if(root.left!=null) preorder( root.left,  rst);
        if(root.right!=null) preorder( root.right,  rst);
    }
}