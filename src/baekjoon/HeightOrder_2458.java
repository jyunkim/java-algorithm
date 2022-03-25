package baekjoon;

import java.util.*;
import java.io.*;

// i에서 j로 도달 가능한지 모두 저장해야되기 때문에 인접 행렬 사용
public class HeightOrder_2458 {

    static int n, m;
    static boolean[][] graph; // 인접 행렬

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // graph[i][j] = i에서 j로 이동이 가능한지 -> i는 j보다 키가 작다
        graph = new boolean[n + 1][n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a][b] = true;
        }

        // i에서 j로 k를 거쳐서 갈 수 있는지 검사
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (graph[i][k] && graph[k][j]) graph[i][j] = true;
                }
            }
        }

        // i에서 갈 수 있는 노드(자신보다 큰 학생)와 i로 갈 수 있는 노드(자신보다 작은 학생)의 개수가 n-1이면 i번 학생은 자신이 몇 번째인지 알 수 있음
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            int count = 0;
            for (int j = 1; j <= n; j++) {
                if (graph[i][j]) count++;
                if (graph[j][i]) count++;
            }
            if (count == n - 1) answer++;
        }

        System.out.println(answer);
    }
}
