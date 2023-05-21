package my_practise.p2022.basic.myClass01;



public class Code07_EvenTimesOddTimes {
   // arr中只有一个数出现了奇数次，找出这个数
    public static int printOddTimesNum1(int[] arr){
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        return eor;
    }

    // arr中有两个数出现了偶数次，找出这两个数
    public static void printOddTimesNum2(int[] arr){
        int eor = 0;

        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        // eor = a^b，a不等于b
        // eor 不等于0
        // 把最右的1提取出来
        int rightOne = eor & (~eor + 1);
        int onlyOne = 0;
        for (int i = 0; i < arr.length; i++) {
            if((arr[i] & rightOne) !=0){
                onlyOne ^= arr[i];
            }
        }
        System.out.println(onlyOne+"__"+(onlyOne^eor));
    }

    public static void main(String[] args) {
        int[] arr1 = {1,2,3,4,5,6,7,1,2,3,4,5,6};
        int[] arr2 = {1,2,3,4,5,6,7,1,2,3,4,5,6,8};
        int eor = printOddTimesNum1(arr1);
        System.out.println(eor);

        printOddTimesNum2(arr2);
    }
}