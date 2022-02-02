package baekjoon;

import java.io.*;
import java.util.*;

// 목적지까지 갈 수 있는 모든 경로 완전 탐색 -> 이분 탐색으로 시간 단축
// 최단 경로만 탐색하면 됨 -> bfs 사용
// 주어진 mid 값으로 목적지까지 갈 수 있는지 검사
// 경로의 최솟값과 최댓값을 어떻게 저장할 것인지? -> mid만큼의 범위를 가진 bound를 조절해가며 탐색
public class MoveInArray_1981 {

    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};

    private static int n;
    private static int[][] array;
    private static int min; // 배열의 최솟값
    private static int max; // 배열의 최댓값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        array = new int[n + 1][n + 1];
        min = 200;
        max = 0;

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                int number = Integer.parseInt(st.nextToken());
                array[i][j] = number;
                min = Math.min(min, number);
                max = Math.max(max, number);
            }
        }

        int answer = 0;
        int low = 0;
        int high = max - min;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (bfs(mid)) { // 목적지까지 갈 수 있으면 범위를 줄여봄
                answer = mid;
                high = mid - 1;
            } else { // 목적지까지 갈 수 없으면 범위를 늘림
                low = mid + 1;
            }
        }

        System.out.println(answer);
    }

    private static boolean bfs(int difference) {
        for (int lowerBound = min; lowerBound <= max - difference; lowerBound++) {
            int upperBound = lowerBound + difference;

            if (array[1][1] < lowerBound || array[1][1] > upperBound) {
                continue;
            }

            Queue<Point> queue = new ArrayDeque<>();
            boolean[][] visited = new boolean[n + 1][n + 1];

            queue.offer(new Point(1, 1));
            visited[1][1] = true;

            while (!queue.isEmpty()) {
                Point point = queue.poll();

                if (point.x == n && point.y == n) {
                    return true;
                }

                for (int i = 0; i < 4; i++) {
                    int nx = point.x + dx[i];
                    int ny = point.y + dy[i];

                    if (nx < 1 || nx > n || ny < 1 || ny > n) {
                        continue;
                    }

                    if (!visited[nx][ny] && array[nx][ny] >= lowerBound && array[nx][ny] <= upperBound) {
                        queue.offer(new Point(nx, ny));
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        return false;
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
