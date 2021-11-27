package baekjoon;

import java.io.*;
import java.util.*;

public class RectangleEscape_16973 {

    static int n, m;
    static int h, w;
    static int fx, fy;

    static int[][] grid;
    static int[][] count;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        grid = new int[n + 1][m + 1];
        count = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        int sx = Integer.parseInt(st.nextToken());
        int sy = Integer.parseInt(st.nextToken());
        fx = Integer.parseInt(st.nextToken());
        fy = Integer.parseInt(st.nextToken());

        bfs(sx, sy);
        System.out.println((count[fx][fy] == 0) ? -1 : count[fx][fy]); // 방문하지 못했을 경우 -1 출력
    }

    private static void bfs(int sx, int sy) {
        Queue<Point> queue = new ArrayDeque<>();
        queue.offer(new Point(sx, sy));

        while (!queue.isEmpty()) {
            Point point = queue.poll();
            int x = point.x;
            int y = point.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 1 || ny < 1 || nx + h - 1 > n || ny + w - 1 > m) continue; // 범위 바깥인 경우
                if (!isWall(nx, ny, i)) continue; // 벽인 경우

                if (count[nx][ny] == 0) { // 처음 방문하는 경우
                    count[nx][ny] = count[x][y] + 1;

                    if (nx == fx && ny == fy) return; // 목적지 도달

                    queue.offer(new Point(nx, ny));
                }
            }
        }
    }

    /**
     * @param x,y 이동했을 때 직사각형의 가장 왼쪽 위칸 좌표
     * @param direction 이동 방향
     */
    private static boolean isWall(int x, int y, int direction) {
        switch (direction) {
            case 0: // 상 - 윗변 검사
                for (int i = 0; i < w; i++) {
                    if (grid[x][y + i] == 1) return false;
                }
                break;
            case 1: // 하 - 아랫변 검사
                for (int i = 0; i < w; i++) {
                    if (grid[x + h - 1][y + i] == 1) return false;
                }
                break;
            case 2: // 좌 - 왼쪽변 검사
                for (int i = 0; i < h; i++) {
                    if (grid[x + i][y] == 1) return false;
                }
                break;
            case 3: // 우 - 오른쪽변 검사
                for (int i = 0; i < h; i++) {
                    if (grid[x + i][y + w - 1] == 1) return false;
                }
                break;
        }
        return true;
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
