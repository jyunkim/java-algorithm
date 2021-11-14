package baekjoon;

import java.io.*;
import java.util.*;

public class ChangYeongAndOffWork_22116 {

    static int answer = 0;
    static int n;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] grid;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        grid = new int[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs(0, 0);
        System.out.println(answer);
    }

    // 경사가 제일 작은 경로의 최대 경사를 구함
    public static void bfs(int x, int y) {
        Queue<Point> pq = new PriorityQueue<>();
        pq.offer(new Point(x, y, 0));
        visited[x][y] = true;

        while (!pq.isEmpty()) {
            Point point = pq.poll();
            visited[point.x][point.y] = true;
            answer = Math.max(answer, point.slope);

            if (point.x == n - 1 && point.y == n - 1) return;

            for (int i = 0; i < 4; i++) {
                int nx = point.x + dx[i];
                int ny = point.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

                if (!visited[nx][ny]) {
                    pq.offer(new Point(nx, ny, Math.abs(grid[point.x][point.y] - grid[nx][ny])));
                }
            }
        }
    }

    static class Point implements Comparable<Point> {
        int x;
        int y;
        int slope;

        public Point(int x, int y, int slope) {
            this.x = x;
            this.y = y;
            this.slope = slope;
        }

        @Override
        public int compareTo(Point o) {
            return this.slope - o.slope;
        }
    }
}
