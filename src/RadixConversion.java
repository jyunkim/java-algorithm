public class RadixConversion {

    public static void main(String[] args) {
        System.out.println(convert(8, 2));
        System.out.println(convert(9, 2));
        System.out.println(Integer.toBinaryString(8));
    }

    // n을 k진수로 변환
    private static String convert(int n, int k) {
        StringBuilder convertedNum = new StringBuilder();

        while (n > 0) {
            convertedNum.insert(0, (n % k)); // int + String = String
            n /= k;
        }
        return convertedNum.toString();
    }
}
