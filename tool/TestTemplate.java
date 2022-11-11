package tool;

public class TestTemplate {
    public static void test(){
        int testTime = 100000;
        int maxSize = 100;
        int maxValue = 100;
        boolean success = true;
        System.out.println("test begin");
        for (int i = 0;success && i < testTime; i++) {
            int[] arr = Tools.generateRandomArray(maxSize, maxValue);
            int w = (int) (Math.random() * (arr.length + 1));
//            System.out.println("w = " + w);
//            Tools.printArray(arr);
            int[] ans1 = null;
            int[] ans2 = null;
            if (!tool.Tools.isEqual(ans1, ans2)) {
                success = false;
                Tools.printArray(arr);
                Tools.printArray(ans1);
                Tools.printArray(ans2);
            }
        }
        System.out.println(success ? "Nice" : "F");
    }
}
