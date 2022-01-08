package baekjoon;

import java.io.*;
import java.util.*;

public class Iceberg_2573 {

    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};

    private static int[][] graph;
    private static int m;
    private static int n;
    

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        graph = new int[m][n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;

        while (!isDivided()) {
            if (isAllMelted()) {
                answer = 0;
                break;
            }
            afterOneYear();
            answer++;
        }

        System.out.println(answer);
    }

    private static boolean isAllMelted() {
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (graph[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void afterOneYear() {
        int[][] temp = new int[m][n];

        for (int i = 1; i < m - 1; i++) {
            temp[i] = graph[i].clone();
        }

        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                int count = 0;

                for (int k = 0; k < 4; k++) {
                    if (graph[i + dx[k]][j + dy[k]] == 0) {
                        count++;
                    }
                }
                temp[i][j] -= count;

                if (temp[i][j] < 0) {
                    temp[i][j] = 0;
                }
            }
        }
        graph = temp;
    }

    private static boolean isDivided() {
        int count = 0;
        boolean[][] visited = new boolean[m][n];

        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (!visited[i][j] && graph[i][j] > 0) {
                    dfs(visited, i, j);
                    count++;
                }
            }
        }
        return count >= 2;
    }

    private static void dfs(boolean[][] visited, int x, int y) {
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (!visited[nx][ny] && graph[nx][ny] > 0) {
                dfs(visited, nx, ny);
            }
        }
    }
}
