package baekjoon;

import java.io.*;
import java.util.*;

// 다익스트라 변형(최댓값)
public class WeightLimit_1939 {

    private static List<Island>[] adjacentList;
    private static int[] weights;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        adjacentList = new List[n + 1];
        weights = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            adjacentList[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adjacentList[a].add(new Island(b, c));
            adjacentList[b].add(new Island(a, c));
        }

        st = new StringTokenizer(br.readLine());
        int factory1 = Integer.parseInt(st.nextToken());
        int factory2 = Integer.parseInt(st.nextToken());

        dijkstra(factory1);
        System.out.println(weights[factory2]);
    }

    private static void dijkstra(int start) {
        // 중량 제한이 높은 순
        Queue<Island> pq = new PriorityQueue<>(Comparator.comparing(Island::getWeightLimit, Comparator.reverseOrder()));
        pq.offer(new Island(start, Integer.MAX_VALUE));

        while (!pq.isEmpty()) {
            Island current = pq.poll();
            // 방문 시 현재까지의 최고 중량 제한보다 작으면 스킵
            if (current.weightLimit < weights[current.index]) {
                continue;
            }
            for (Island next : adjacentList[current.index]) {
                int newWeightLimit = Math.min(current.weightLimit, next.weightLimit); // 경로의 중량 제한 중 가장 작은 값
                // 현재까지의 최고 중량 제한보다 크면 갱신
                if (newWeightLimit > weights[next.index]) {
                    weights[next.index] = newWeightLimit;
                    pq.offer(new Island(next.index, newWeightLimit));
                }
            }
        }
    }

    static class Island {
        int index;
        int weightLimit;

        public Island(int index, int weightLimit) {
            this.index = index;
            this.weightLimit = weightLimit;
        }

        public int getWeightLimit() {
            return weightLimit;
        }
    }
}
