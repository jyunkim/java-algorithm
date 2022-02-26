package baekjoon;

import java.io.*;
import java.util.*;

// 투 포인터 - 연속된 숫자로 이루어진 범위를 조절
public class SumPrimeNumber_1644 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<Integer> primeNumbers = getPrimeNumbers(n);

        int answer = 0;
        int i = 0;
        int sum = 0;
        for (Integer primeNumber : primeNumbers) {
            sum += primeNumber;
            while (sum > n) {
                sum -= primeNumbers.get(i++);
            }
            if (sum == n) {
                answer++;
            }
        }

        System.out.println(answer);
    }

    private static List<Integer> getPrimeNumbers(int n) {
        Boolean[] isPrimeNumber = new Boolean[n + 1];
        List<Integer> primeNumbers = new ArrayList<>();

        for (int i = 2; i <= n; i++) {
            isPrimeNumber[i] = true;
        }

        for (int i = 2; i <= n; i++) {
            if (isPrimeNumber[i]) {
                primeNumbers.add(i);
                for (int j = 2 * i; j <= n; j += i) {
                    isPrimeNumber[j] = false;
                }
            }
        }
        return primeNumbers;
    }
}
