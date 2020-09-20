class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        int[] rst = new int[k];
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<Map.Entry<Integer, Integer>>(new Comparator<Map.Entry<Integer, Integer>>() {

            @Override
            public int compare(Map.Entry<Integer, Integer> m, Map.Entry<Integer, Integer> n) {
                return n.getValue() - m.getValue();
            }
        });

        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) map.put(num, map.get(num) + 1);
            else map.put(num, 1);
        }
        for (Map.Entry entry : map.entrySet()) {
            queue.offer(entry);
        }

        for (int i = 0; i < k; i++) {
            rst[i] = queue.poll().getKey();
        }
        return rst;
    }
}