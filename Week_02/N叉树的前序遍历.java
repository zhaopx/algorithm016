class Solution {
    public List<Integer> preorder(Node root) {
        List<Integer> rst=new ArrayList<>();
        order(root,rst);
        return rst;
    }
    private void order(Node root,List<Integer> list){
        if(root==null) return;
        list.add(root.val);
        for(Node node :root.children){
            order(node,list);
        }
    }