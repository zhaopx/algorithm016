class Solution {
    public int nthUglyNumber(int n) {
        int a = 0, b = 0, c = 0;
        int[] rst = new int[n];
        rst[0] = 1;
        for (int i = 1; i < n; i++) {
            int a1 = rst[a] * 2, b1 = rst[b] * 3, c1 = rst[c] * 5;
            int tmp = Math.min(Math.min(a1, b1), c1);
            if (tmp == a1) a++;
            if (tmp == b1) b++;
            if (tmp == c1) c++;
            rst[i] = tmp;
            System.out.println(String.format("tmp:%s;i:%s", tmp, i));
        }
        return rst[n - 1];
    }

}