package baekjoon;

import java.io.*;
import java.util.*;

// dfs, bfs로는 T자 모양으로 탐색할 수 없음
// 모든 조합을 구해놓고 검사
public class SevenPrincess_1941 {

    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};
    private static final char[][] graph = new char[5][5];
    private static final boolean[][] inCombination = new boolean[5][5];

    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 5; i++) {
            graph[i] = br.readLine().toCharArray();
        }

        combination(0, 7, 0, 0);
        System.out.println(answer);
    }

    // 25개 중 7개를 뽑는다
    private static void combination(int start, int left, int lastX, int lastY) {
        if (left == 0) {
            if (check(lastX, lastY)) { // 조건을 만족하는지 검사
                answer++;
            }
            return;
        }

        for (int i = start; i < 25; i++) {
            int x = i / 5;
            int y = i % 5;

            inCombination[x][y] = true;
            combination(i + 1, left - 1, x, y);
            inCombination[x][y] = false;
        }
    }

    // 위에서 구한 조합(방문 처리된 위치)을 탐색하여 조건을 만족하는지 검사
    private static boolean check(int x, int y) {
        Queue<Point> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[5][5];
        int total = 1;
        int count = 0;

        queue.offer(new Point(x, y));
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            Point point = queue.poll();

            // 이다솜파일 경우 +1
            if (graph[point.x][point.y] == 'S') {
                count++;
            }

            // 인접한 자리만 탐색
            for (int i = 0; i < 4; i++) {
                int nx = point.x + dx[i];
                int ny = point.y + dy[i];

                if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5 || visited[nx][ny]) {
                    continue;
                }

                // 조합에 포함된 위치만 탐색
                if (inCombination[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.offer(new Point(nx, ny));
                    total++;
                }
            }
        }
        // 7명의 학생들로 구성되어 있고, 이다솜파가 4명 이상이면 true
        return total == 7 && count >= 4;
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
