package baekjoon;

import java.io.*;
import java.util.*;

public class CrazyRobot_1405 {

    static int n;
    static double answer = 0;
    static double[] probs;
    // 동서남북
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static boolean[][] visited = new boolean[30][30];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        probs = new double[4];

        for (int i = 0; i < 4; i++) {
            probs[i] = Double.parseDouble(st.nextToken()) / 100;
        }

        dfs(15, 15, 1, 0);
        System.out.println(answer);
    }

    public static void dfs(int x, int y, double prob, int count) {
        if (count == n) {
            answer += prob;
            return;
        }

        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (!visited[nx][ny]) {
                dfs(nx, ny, prob * probs[i], count + 1);
                visited[nx][ny] = false;
            }
        }
    }
}
