package baekjoon;

import java.util.*;
import java.io.*;

// TODO: 2021-09-24 투포인터, 이분탐색으로도 시도
/**
 * 1. 부분합 개수 최대 1,000 * 1,000 -> 답 계산 시 1,000,000 * 1,000,000 => int 범위 초과
 * 2. 부분합 최대 1,000 * 1,000,000 -> t와 연산 시 최대 20억 => int 범위 초과
 */
public class SumOfTwoArray_2143 {

    static int t, n, m;
    static int[] a;
    static int[] b;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        n = Integer.parseInt(br.readLine());
        a = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        m = Integer.parseInt(br.readLine());
        b = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        Map<Long, Long> countSumA = new HashMap<>();
        Map<Long, Long> countSumB = new HashMap<>();

        countPartialSum(a, countSumA);
        countPartialSum(b, countSumB);

        long answer = 0;
        for (Map.Entry<Long, Long> entry : countSumA.entrySet()) {
            long temp = t - entry.getKey();
            if (countSumB.containsKey(temp)) {
                answer += entry.getValue() * countSumB.get(temp);
            }
        }
        System.out.println(answer);
    }

    // 부분합 계산 후 map에 저장
    public static void countPartialSum(int[] arr, Map<Long, Long> map) {
        long sum;
        for (int i = 0; i < arr.length; i++) {
            sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                map.putIfAbsent(sum, 0L);
                map.replace(sum, map.get(sum) + 1);
            }
        }
    }
}
