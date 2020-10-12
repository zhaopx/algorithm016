class Solution {
    public int findContentChildren(int[] g, int[] s) {
        if(g.length==0||s.length==0) return 0;
        Arrays.sort(g);
        Arrays.sort(s);
        int gl=0;
        int sl=0;
        int cnt=0;
        while(gl<g.length && sl<s.length){
            if(s[sl]>=g[gl]){
                sl++;
                gl++;
                cnt++;
            }else{
                sl++;
            }
        }
        return cnt;
    }
}