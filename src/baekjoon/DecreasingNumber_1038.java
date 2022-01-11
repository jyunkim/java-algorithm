package baekjoon;

import java.io.*;
import java.util.*;

public class DecreasingNumber_1038 {

    private static final List<Long> decreasingNumbers = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long answer = -1;

        // 맨 앞자리(i)로 만들 수 있는 감소하는 수들을 저장
        for (int i = 0; i < 10; i++) {
            decreasingNumbers.add((long) i);
            dfs(i - 1, Integer.toString(i));
        }

        Collections.sort(decreasingNumbers);

        if (n < decreasingNumbers.size()) {
            answer = decreasingNumbers.get(n);
        }
        System.out.println(answer);

//        가장 큰 감소하는 수 = 9876534210
//        long max = 9876543210L;
//        long answer = -1;
//
//        for (long i = 0; i <= max; i++) {
//            if (isDecreasingNumber(Long.toString(i))) {
//                if (n == 0) {
//                    answer = i;
//                    break;
//                }
//                n--;
//            }
//        }
//        System.out.println(answer);
    }

    private static void dfs(int start, String number) {
        for (int i = start; i >= 0; i--) {
            decreasingNumbers.add(Long.valueOf(number + i));
            dfs(i - 1, number + i);
        }
    }

//    private static boolean isDecreasingNumber(String number) {
//        for (int i = 0; i < number.length() - 1; i++) {
//            if (number.charAt(i) <= number.charAt(i + 1)) {
//                return false;
//            }
//        }
//        return true;
//    }
}
