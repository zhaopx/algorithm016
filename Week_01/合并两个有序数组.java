class Solution {
    //运用系统方法排序
    // public void merge(int[] nums1, int m, int[] nums2, int n) {
    //     System.arraycopy(nums2, 0, nums1, m, n);
    //     Arrays.sort(nums1);
    // }
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p=m+n-1;
        m--;
        n--;
        while(n>=0){
            if(m>=0){
                nums1[p--]=nums1[m]>nums2[n]?nums1[m--]:nums2[n--];
            }
            else{
                nums1[p--]=nums2[n--];
            }
        }
    }
}
