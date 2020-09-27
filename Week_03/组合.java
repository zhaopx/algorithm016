static class Solution {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> rst = new ArrayList<>();
        Deque<Integer> tmpList = new LinkedList<>();
        sreach(n, k, 1, tmpList, rst);
        return rst;
    }

    private void sreach(int n, int k, int i, Deque<Integer> tmpList, List<List<Integer>> rst) {
        if (tmpList.size() == k) {
            rst.add(new ArrayList(tmpList));
            return;
        }
        for (int j = i; j <= n; j++) {
            tmpList.addLast(j);
            sreach(n,k,j+1,tmpList,rst);
            tmpList.removeLast();
        }
    }
}