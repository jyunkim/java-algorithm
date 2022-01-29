package baekjoon;

import java.io.*;
import java.util.*;

// 특정 노드에서 다른 노드로의 최단 거리 -> 다익스트라
// 여러 노드에서 특정 노드로의 최단 거리 -> 다익스트라 역순(간선 방향을 뒤집고 다익스트라 수행)
public class Party_1238 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        List<Village>[] graph = new List[n + 1];
        List<Village>[] reverseGraph = new List[n + 1];
        int[] go = new int[n + 1];
        int[] come = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            reverseGraph[i] = new ArrayList<>();
            go[i] = Integer.MAX_VALUE;
            come[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            graph[from].add(new Village(to, time));
            reverseGraph[to].add(new Village(from, time));
        }

        dijkstra(x, reverseGraph, go); // 갈 때의 최단 거리
        dijkstra(x, graph, come); // 돌아올 때의 최단 거리

        // 가장 오래 걸리는 이동 시간
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (i != x) {
                answer = Math.max(answer, go[i] + come[i]);
            }
        }

        System.out.println(answer);
    }

    private static void dijkstra(int start, List<Village>[] graph, int[] times) {
        Queue<Village> pq = new PriorityQueue<>(Comparator.comparingInt(village -> village.time));
        pq.offer(new Village(start, 0));
        times[start] = 0;

        while (!pq.isEmpty()) {
            Village current = pq.poll();

            // 특정 노드로 가는 여러 경로 중 최단 경로만 탐색(큐 뒤쪽에 있는 나머지 노드들 스킵)
            if (current.time > times[current.index]) {
                continue;
            }

            for (Village next : graph[current.index]) {
                int timeSpent = current.time + next.time;
                if (timeSpent < times[next.index]) {
                    times[next.index] = timeSpent;
                    pq.offer(new Village(next.index, timeSpent));
                }
            }
        }
    }

    static class Village {
        int index;
        int time;

        public Village(int index, int time) {
            this.index = index;
            this.time = time;
        }
    }
}
