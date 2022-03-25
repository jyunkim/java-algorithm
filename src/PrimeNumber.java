import java.util.Arrays;

public class PrimeNumber {

    public static void main(String[] args) {
        System.out.println(checkPrime(5));
        System.out.println(Arrays.toString(getPrimes(10)));
    }

    // n이 소수인지 판별
    private static boolean checkPrime(int n) {
        if (n < 2) return false;

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    // n 이하의 정수 중 소수면 해당 index가 true인 배열 반환
    private static boolean[] getPrimes(int n) {
         boolean[] isPrime = new boolean[n + 1];

         // 소수 후보들을 true로 초기화
         for (int i = 2; i <= n; i++) {
             isPrime[i] = true;
         }

         // 배수들을 false로 변경
         for (int i = 2; i <= n; i++) {
             if (isPrime[i]) {
                 for (int j = 2; i * j <= n; j++) {
                     isPrime[i * j] = false;
                 }
             }
         }
         return isPrime;
    }
}
