class Solution {
    public int numIslands(char[][] grid) {
        if (grid.length <= 0) return 0;
        int cnt = 0;
        int n = grid.length;
        int m = grid[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    dfsSreach(grid, n, m, i, j);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private void dfsSreach(char[][] grid, int n, int m, int i, int j) {
        if (i < 0 || j < 0 || i >= n || j >= m || grid[i][j] == '0') return;
        grid[i][j]='0';
        dfsSreach(grid,n,m,i+1,j);
        dfsSreach(grid,n,m,i-1,j);
        dfsSreach(grid,n,m,i,j+1);
        dfsSreach(grid,n,m,i,j-1);
    }
}