static class Solution {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> rst = new ArrayList<>();
        Deque<Integer> path = new LinkedList<>();
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        permute(nums, used, path, rst);
        return rst;
    }

    private void permute(int[] nums, boolean[] used, Deque<Integer> path, List<List<Integer>> rst) {
        if (path.size() == nums.length) {
            rst.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
            used[i] = true;
            path.addLast(nums[i]);
            permute(nums, used, path, rst);
            used[i] = false;
            path.removeLast();

        }
    }
}
//方法2
//public List<List<Integer>> permuteUnique(int[] nums) {
//    List<List<Integer>> rst = new ArrayList<>();
//    Deque<Integer> path = new LinkedList<>();
//    boolean[] used = new boolean[nums.length];
//    permute(nums, used, path, rst);
//    return rst;
//}
//
//    private void permute(int[] nums, boolean[] used, Deque<Integer> path, List<List<Integer>> rst) {
//        if (path.size() == nums.length) {
//            rst.add(new ArrayList<>(path));
//            return;
//        }
//        List<Integer> tmp = new ArrayList<>();
//        for (int i = 0; i < nums.length; i++) {
//            if (!used[i] && !tmp.contains(nums[i])) {
//                tmp.add(nums[i]);
//                used[i] = true;
//                path.addLast(nums[i]);
//                permute(nums, used, path, rst);
//                used[i] = false;
//                path.removeLast();
//            }
//        }
//    }