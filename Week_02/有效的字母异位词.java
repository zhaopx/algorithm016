class Solution {
    //hash表
    public boolean isAnagram(String s, String t) {
        if(s.length()!=t.length())
            return false;
        int[] tmp=new int[26];
        for(int i=0;i<s.length();i++)
        {
            tmp[s.charAt(i)-'a']++;
            tmp[t.charAt(i)-'a']--;
        }
        for(int i=0; i<26;i++){
            if(tmp[i]!=0)
                return false;
        }
        return true;
    }
    //暴力法
    // public boolean isAnagram(String s, String t) {
    //     if(s.length()!=t.length())
    //         return false;
    //     char[] s_c=s.toCharArray();
    //     char[] t_c=t.toCharArray();
    //     Arrays.sort(s_c);
    //     Arrays.sort(t_c);
    //     return Arrays.equals(t_c,s_c);
    // }
}