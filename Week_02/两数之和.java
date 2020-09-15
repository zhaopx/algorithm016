class Solution {
    //一遍hash
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> m = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int t = target - nums[i];
            if(m.containsKey(t)) {
                return new int[]{i,m.get(t)};
            }
            m.put(nums[i],i);
        }
        throw new IllegalArgumentException("未找到");
    }
    //两遍hash
    // public int[] twoSum(int[] nums, int target) {
    //     HashMap<Integer, Integer> map = new HashMap<>();
    //     for (int i = 0; i < nums.length; i++) {
    //         map.put(nums[i], i);
    //     }
    //     for (int i = 0; i < nums.length; i++) {
    //         int t = target - nums[i];
    //         if(map.containsKey(t)&&i!=map.get(t)) {
    //             return new int[]{i,map.get(t)};
    //         }
    //     }
    //     throw new IllegalArgumentException("未找到");
    // }
    //暴力法
    // public int[] twoSum(int[] nums, int target) {
    //     for(int i=0;i<nums.length-1;i++){
    //         for(int j=i+1;j<nums.length;j++){
    //             if(nums[i]+nums[j]==target){
    //                 return new int[]{i,j};
    //             }
    //         }
    //     }
    //     throw new IllegalArgumentException("未找到");
    // }
}