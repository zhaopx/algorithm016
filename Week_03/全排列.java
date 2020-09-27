static class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> rst = new ArrayList<>();
        Deque<Integer> path = new LinkedList<>();
        boolean[] used = new boolean[nums.length];
        t1(nums, used, path, rst);
        return rst;
    }

    private void t1(int[] nums, boolean[] used, Deque<Integer> path, List<List<Integer>> rst) {
        if (path.size() == nums.length) {
            rst.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                used[i] = true;
                path.addLast(nums[i]);
                t1(nums, used, path, rst);
                used[i] = false;
                path.removeLast();
            }
        }
    }
}