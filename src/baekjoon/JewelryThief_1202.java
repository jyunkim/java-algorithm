package baekjoon;

import java.io.*;
import java.util.*;

// 가격을 기준으로 하면 2개 넣을 수 있는데 1개밖에 넣지 못하는 상황 발생
public class JewelryThief_1202 {
    private static int n, k;
    private static Jewelry[] jews;
    private static int[] weights;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        jews = new Jewelry[n];
        weights = new int[k];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            jews[i] = new Jewelry(m, v);
        }
        for (int i = 0; i < k; i++) {
            weights[i] = Integer.parseInt(br.readLine());
        }

        // 가방 무게, 보석 무게 오름차순 정렬
        // Comparable 사용 시 정의한 기준 하나만 적용 가능하므로 Comparator 사용
        // 익명 클래스(Anonymous class) 사용 시 interface 객체 생성 가능
        Arrays.sort(jews, new Comparator<Jewelry>() {
            @Override
            public int compare(Jewelry o1, Jewelry o2) {
                return o1.m - o2.m;
            }
        });
        Arrays.sort(weights);
        // 보석 가치 최대 힙
        PriorityQueue<Jewelry> pq = new PriorityQueue<>(new Comparator<Jewelry>() {
            @Override
            public int compare(Jewelry o1, Jewelry o2) {
                return o2.v - o1.v;
            }
        });

        // 작은 가방부터 가능한 최대 가격의 보석을 넣음
        long answer = 0; // 1000000 * 300000 = 3천억 -> int 불가
        int j = 0;
        for (int i = 0; i < k; i++) {
            int weight = weights[i];
            // 한번 들어간 건 다시 넣지 않게 index 밖에 뺌
            while (j < n && jews[j].m <= weight) {
                pq.add(jews[j]);
                j++;
            }
            if (!pq.isEmpty()) {
                answer += pq.poll().v;
            }
        }
        System.out.println(answer);
    }

    static class Jewelry {
        private int m;
        private int v;

        public Jewelry(int m, int v) {
            this.m = m;
            this.v = v;
        }
    }
}
