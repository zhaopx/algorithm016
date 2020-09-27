/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    int min=0;
    public boolean isValidBST(TreeNode root) {
        if(root==null) return true;
        if(!isValidBST(root.left)) return false;
        if(root.val<=min) return false;
        min=root.val;
        return isValidBST(root.right);
    }
}