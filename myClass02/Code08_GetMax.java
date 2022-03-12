package myClass02;


public class Code08_GetMax {
    public static int process(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        int mid = L + ((R - L) >> 1);
        int maxL = process(arr, L, mid);
        int maxR = process(arr, mid + 1, R);
        return Math.max(maxL, maxR);
    }

    public static int getMax(int[] arr) {
        int max = arr[0];
        for (int j : arr) {
            max = Math.max(max, j);
        }
        return max;
    }

    public static int getRandomInt(int max) {
        return (int) (Math.random() * max - Math.random() * max);
    }

    public static int[] getRandomIntArr(int size, int maxValue) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * maxValue - Math.random() * maxValue);
        }
        return arr;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 500000; i++) {
            int[] randomIntArr = getRandomIntArr(20, 100);
            int max1 = getMax(randomIntArr);
            int max2 = process(randomIntArr, 0, randomIntArr.length - 1);
            if (max1 != max2) {
                System.out.println("Fuck!!!");
                return;
            }
        }
        System.out.println("Succeed!!!");
    }
}