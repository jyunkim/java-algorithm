package programmers.kakao;

import java.util.*;
import java.util.stream.Collectors;

public class Problem2 {

    public int solution(int n, int k) {
        int answer = 0;
        String num = "";

        // k진수로 변환
        while (n > 0) {
            num = (n % k) + num;
            n /= k;
        }

        // 0을 기준으로 분리
        List<Long> nums = Arrays.stream(num.split("0"))
                .filter(a -> a.length() > 0)
                .map(Long::parseLong) // int로 할 시 범위 초과
                .collect(Collectors.toList());

        // boolean[] primes = checkPrime(Collections.max(nums));
        // for (int a : nums) {
        //     if (primes[a]) answer++;
        // }

        for (long a : nums) {
            if (a == 1) continue;

            boolean isPrime = true;
            for (long i = 2; i < (long)Math.sqrt(a) + 1; i++) {
                if (a % i == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) answer++;
        }

        return answer;
    }

    // 에라토스테네스의 체
//     public static boolean[] checkPrime(int n) {
//         boolean[] nums = new boolean[n + 1];

//         // 소수 후보들을 true로 초기화
//         for (int i = 2; i < n + 1; i++) {
//             nums[i] = true;
//         }

//         // 배수들을 false로 변경
//         for (int i = 2; i < n + 1; i++) {
//             if (nums[i]) {
//                 for (int j = 2; i * j < n + 1 && i * j > 3; j++) {
//                     nums[i * j] = false;
//                 }
//             }
//         }

//         return nums;
//     }
}