package baekjoon;

import java.util.*;
import java.io.*;

public class FindTreeParent_11725 {

    static int n;
    static int[] parents; // index(자식) -> element(부모)
    static boolean[] visited; // 방문 처리 배열
    static List<Integer>[] adjacents; // 인접 리스트

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        parents = new int[n + 1];
        visited = new boolean[n + 1];
        adjacents = new List[n + 1];
        for (int i = 1; i < n + 1; i++) {
            adjacents[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adjacents[a].add(b);
            adjacents[b].add(a);
        }

        bfs(1);

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < n + 1; i++) {
//            System.out.println(parent[i]);
            sb.append(parents[i]).append("\n");
        }
        System.out.println(sb);
    }

    // 루트 노드부터 bfs로 탐색하며 부모 설정
    public static void bfs(int root) {
//        Queue<Integer> queue = new ArrayDeque<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(root);
        visited[root] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (Integer node : adjacents[cur]) {
                if (!visited[node]) {
                    visited[node] = true;
                    parents[node] = cur;
                    queue.offer(node);
                }
            }
        }
    }

//    private static void dfs(int current, int parent) {
//        for (int next : adjacents[current]) {
//            if (next != parent) {
//                parents[next] = current;
//                dfs(next, current);
//            }
//        }
//    }
}
