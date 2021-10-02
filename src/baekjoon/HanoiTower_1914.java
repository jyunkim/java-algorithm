package baekjoon;

import java.io.*;
import java.math.BigInteger;

// 분할 정복
public class HanoiTower_1914 {

    static int n;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        // 답 = 2^n - 1
        // 최대 2^100 - 1 이므로 long 범위 초과
        System.out.println(BigInteger.TWO.pow(n).subtract(BigInteger.ONE));

        // 과정을 출력해야 하는 경우만 재귀 호출
        if (n <= 20) {
            recur(n, 1, 3, 2);
            System.out.println(sb);
        }
    }

    /**
     * 1. n개의 원판 중 n-1개를 중간 장대에 옮긴다.
     * 2. 첫번째 장대에 있는 원판을 목적지 장대로 옮긴다.
     * 3. 중간 장대에 있는 n-1개의 원판을 목적지 장대로 옮긴다.
     */
    public static void recur(int remain, int from, int to, int via) {
        // 한개가 남았으면 목적지 장대로 옮김
        if (remain == 1) {
            sb.append(from).append(" ").append(to).append('\n');
            return;
        }

        recur(remain - 1, from, via, to);
        sb.append(from).append(" ").append(to).append('\n');
        recur(remain - 1, via, to, from);
    }
}
