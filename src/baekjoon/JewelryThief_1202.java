package baekjoon;

import java.io.*;
import java.util.*;

// 가격을 기준으로 하면 2개 넣을 수 있는데 1개밖에 넣지 못하는 상황 발생
public class JewelryThief_1202 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Jewelry[] jews = new Jewelry[n];
        int[] bags = new int[k];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            jews[i] = new Jewelry(m, v);
        }
        for (int i = 0; i < k; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(jews, Comparator.comparingInt(o -> o.weight)); // 보석 무게 오름차순
        Arrays.sort(bags); // 가방 무게 오름차순
        PriorityQueue<Jewelry> inBag = new PriorityQueue<>((o1, o2) -> o2.value - o1.value); // 보석 가치 최대 힙

        // 작은 가방부터 가능한 무게의 보석 중 가격이 가장 높은 보석을 담음
        long answer = 0; // 1000000 * 300000 = 3천억 -> int 불가
        int j = 0;
        for (int i = 0; i < k; i++) {
            int weight = bags[i];
            // 한번 들어간 건 다시 넣지 않게 index 밖에 뺌
            while (j < n && jews[j].weight <= weight) {
                inBag.add(jews[j]);
                j++;
            }
            if (!inBag.isEmpty()) {
                answer += inBag.poll().value;
            }
        }
        System.out.println(answer);
    }

    static class Jewelry {
        int weight;
        int value;

        public Jewelry(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }
}
