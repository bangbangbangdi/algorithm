package basicAlgorithm.bitOperation;
// 请打印一个int的二进制格式
public class PrintBinaryCode {

    public static void main(String[] args) {
        // 由此可见整型是以补码的形式进行存储的,原因在于采用补码后我们就可以对正数负数都用同一套逻辑进行处理
        printBinaryCode(-1);
    }

    public static void  printBinaryCode(int num){
        for (int i = 31; i >= 0; i--) {
            int tem = 1 << i;
            System.out.print((tem&num)==0 ? "0":"1");
        }
    }
}
