class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> rst=new ArrayList();
        inorder(root,rst);
        return rst;
    }

    public void inorder(TreeNode r, List<Integer> rst){
        if(r==null) return;
        if(r.left!=null) inorder(r.left,rst);
        rst.add(r.val);
        if(r.right!=null) inorder(r.right,rst);
    }
}