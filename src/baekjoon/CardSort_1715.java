package baekjoon;

import java.io.*;
import java.util.*;

// 카드 묶음 중 크기가 가장 작은 두 묶음을 우선 합친다. -> 우선 순위 큐
public class CardSort_1715 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Queue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            pq.offer(Integer.parseInt(br.readLine()));
        }

        int answer = 0;

        // 마지막 두 묶음이 합쳐지면 종료
        while (pq.size() > 1) {
            int min = pq.poll();
            int nextMin = pq.poll();
            int sum = min + nextMin;

            answer += sum;
            pq.offer(sum);
        }

        System.out.println(answer);
    }
}
