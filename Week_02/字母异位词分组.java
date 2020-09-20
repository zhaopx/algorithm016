class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> m = new HashMap<>();
        for (String str : strs) {
            int[] iList=new int[26];
            for (char c : str.toCharArray()) {
                iList[c-'a']++;
            }
            StringBuilder sb = new StringBuilder("");
            for (int i : iList) {
                sb.append("_").append(i);
            }
            String tmp=sb.toString();
            if (!m.containsKey(tmp)) {
                m.put(tmp, new ArrayList());
            }
            m.get(tmp).add(str);
        }
        return new ArrayList<>(m.values());
    }
//    public List<List<String>> groupAnagrams(String[] strs) {
//        HashMap<String, List<String>> m = new HashMap<>();
//        for (String str : strs) {
//            char[] c = str.toCharArray();
//            Arrays.sort(c);
//            String t = String.valueOf(c);
//            if (!m.containsKey(t)) {
//                m.put(t, new ArrayList());
//            }
//            m.get(t).add(str);
//        }
//        return new ArrayList<>(m.values());
//    }
}
