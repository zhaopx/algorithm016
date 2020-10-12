class Solution {
    public boolean lemonadeChange(int[] bills) {
        int five = 0;
        int ten = 0;
        for (int i : bills) {
            switch (i) {
                case 5:
                    five++;
                    break;
                case 10:
                    five--;
                    ten++;
                    break;
                case 20:
                    if (ten > 0) {
                        ten--;
                        five--;
                    } else {
                        five = five - 3;
                    }
                    break;
            }
            if(five<0) return false;
            System.out.println("five:"+five);
            System.out.println("ten:"+ten);
        }
        return true;
    }
}