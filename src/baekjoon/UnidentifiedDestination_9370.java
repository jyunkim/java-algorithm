package baekjoon;

import java.util.*;
import java.io.*;

// 다익스트라
// 특정 노드에서 다른 노드로의 최단 경로 중 특정 간선을 지나는 목적지
// -> 목적지로의 최단 거리 == 목적지까지 특정 간선을 거쳐 가는 최단 거리
public class UnidentifiedDestination_9370 {

    private static List<Node>[] graph;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        for (int i = 0; i < tc; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            graph = new List[n + 1];
            for (int j = 1; j <= n; j++) {
                graph[j] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            int distanceBetweenGH = 0;

            for (int j = 0; j < m; j++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                graph[a].add(new Node(b, d));
                graph[b].add(new Node(a, d));

                if ((a == g && b == h) || (a == h && b == g)) {
                    distanceBetweenGH = d;
                }
            }

            List<Integer> candidates = new ArrayList<>();
            for (int j = 0; j < t; j++) {
                candidates.add(Integer.parseInt(br.readLine()));
            }
            Collections.sort(candidates);

            int[] distanceFromS = dijkstra(s);
            int[] distanceFromG = dijkstra(g);
            int[] distanceFromH = dijkstra(h);

            StringBuilder sb = new StringBuilder();

            // s -> x가 s -> g -> h -> x 또는 s -> h -> g -> x의 거리와 같을 경우 정답
            for (Integer destination : candidates) {
                int shortestDistance = distanceFromS[destination];
                if ((shortestDistance == (distanceFromS[g] + distanceBetweenGH + distanceFromH[destination]))
                        || (shortestDistance == (distanceFromS[h] + distanceBetweenGH + distanceFromG[destination]))) {
                    sb.append(destination).append(" ");
                }
            }
            System.out.println(sb);
        }
    }

    private static int[] dijkstra(int start) {
        Queue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));
        pq.offer(new Node(start, 0));

        int[] distances = new int[n + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[start] = 0;

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (current.distance > distances[current.index]) {
                continue;
            }

            for (Node next : graph[current.index]) {
                int totalDistance = current.distance + next.distance;
                if (totalDistance < distances[next.index]) {
                    distances[next.index] = totalDistance;
                    pq.offer(new Node(next.index, totalDistance));
                }
            }
        }
        return distances;
    }

    static class Node {
        int index;
        int distance;

        public Node(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }
    }
}
