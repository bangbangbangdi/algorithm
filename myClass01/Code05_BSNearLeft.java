package myClass01;


public class Code05_BSNearLeft {
    // 在arr上，找满足>=value的最左位置
    public static int binarySearchNearLeft(int[] arr, int num) {
        if ((arr == null) || arr.length == 0) {
            return -1;
        }
        int L = 0;
        int R = arr.length - 1;
        int index = -1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] >= num){
                index = mid;
                R = mid - 1;
            }else{
                L = mid + 1;
            }
        }
        return index;
    }

    public static int comparator(int[] arr,int num){
        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= num){
                return i;
            }
        }
        return index;
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
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
            if (binarySearchNearLeft(arr1,randomNum) != comparator(arr1,randomNum)){
                System.out.println("=============");
                printArray(arr1);
                System.out.println(randomNum);
                System.out.println(binarySearchNearLeft(arr1,randomNum));
                System.out.println(comparator(arr1,randomNum));
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
//        int[] arr = {-78,-70,-52,-21,14,58};
//        int num = 52;
//        System.out.println(binarySearchNearLeft(arr,num));
    }
}