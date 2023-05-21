package my_practise.p2022.basic.myClass01;


public class Code04_BSExist {
    public static boolean binarySearch(int[] arr, int i) {
        if ((arr == null) || arr.length == 0) {
            return false;
        }
        int L = 0;
        int R = arr.length - 1;
        while (L < R) {
            int mid = L + ((R - L) >> 1);
            if(arr[mid] == i){
                return true;
            }else if(arr[mid] > i){
                R = mid -1;
            }else{
                L = mid + 1;
            }
        }
        return arr[L] == i;
    }

    public static boolean comparator(int[] arr,int num){
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num){
                return true;
            }
        }
        return false;
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        // Math.random() ->  [0,1) 所有的小数，等概率返回一个
        // Math.random() * N -> [0,N) 所有小数，等概率返回一个
        // (int)(Math.random() * N) -> [0,N-1] 所有的整数，等概率返回一个
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())]; // 长度随机
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random())
                    - (int) (maxValue * Math.random());
        }
        return arr;
    }

    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 0~0 有序的
        // 0~i 想有序
        for (int i = 1; i < arr.length; i++) { // 0 ~ i 做到有序
            // arr[i]往前看，一直交换到合适的位置停止
            // ...(<=)  ?       <- i
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }
    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100; // 随机数组的长度0～100
        int maxValue = 100;// 值：-100～100
        int randomNum = (int) ((maxValue+1)*Math.random());
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            insertionSort(arr1);
            if (binarySearch(arr1,randomNum) != comparator(arr1,randomNum)){
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

    }
}