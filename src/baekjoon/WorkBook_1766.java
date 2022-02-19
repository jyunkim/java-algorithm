package baekjoon;

import java.util.*;
import java.io.*;

// 위상정렬 + 우선순위 큐
// indegree가 0인 문제 중, 번호가 가장 작은 문제를 먼저 푼다
public class WorkBook_1766 {

//    private static boolean[] solved;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Map<Integer, List<Integer>> nextProblems = new HashMap<>(); // 후행 문제들
        int[] indegree = new int[n + 1]; // 선행 문제 수
//        solved = new boolean[n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            nextProblems.putIfAbsent(a, new ArrayList<>());
            nextProblems.get(a).add(b);
            indegree[b]++;
        }

        Queue<Integer> pq = new PriorityQueue<>();

        for (int i = 1; i <= n; i++) {
//            dfs(i);
            if (indegree[i] == 0) {
                pq.offer(i);
            }
        }

        StringBuilder sb = new StringBuilder();

        while (!pq.isEmpty()) {
            int problem = pq.poll();
            sb.append(problem).append(" ");

            if (nextProblems.containsKey(problem)) {
                for (int next : nextProblems.get(problem)) {
                    indegree[next]--;
                    if (indegree[next] == 0) {
                        pq.offer(next);
                    }
                }
            }
        }

        System.out.println(sb);
    }

    // dfs로 하게되면 풀 수 있는 문제들 중 가장 쉬운 문제를 먼저 풀 수 없음
    // 1:[2, 6], 2:[5], 3:[1, 4], 4:[2], 6:[5]
//    private static void dfs(int problem) {
//        if (solved[problem]) {
//            return;
//        }
//
//        if (!prerequisites.containsKey(problem)) {
//            sb.append(problem).append(" ");
//            solved[problem] = true;
//            return;
//        }
//
//        while (!prerequisites.get(problem).isEmpty()) {
//            dfs(prerequisites.get(problem).poll());
//        }
//        sb.append(problem).append(" ");
//        solved[problem] = true;
//    }
}
