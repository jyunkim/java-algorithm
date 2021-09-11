public class RadixConversion {

    public static void main(String[] args) {
        System.out.println(convert(8, 2));
    }

    // n을 k진수로 변환
    public static String convert(int n, int k) {
        String num = "";

        while (n > 0) {
            num = (n % k) + num; // int + String = String
            n /= k; // int / int = int
        }
        return num;
    }
}
