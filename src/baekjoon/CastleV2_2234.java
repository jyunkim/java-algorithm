package baekjoon;

import java.util.*;
import java.io.*;

// 비트 연산 이용
public class CastleV2_2234 {

    static int n, m;
    static int[][] map;
    static boolean[][] visited;
    // 서북동남
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[m][n];
        visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                // 벽의 정보 - 4자리 이진수로 표현 가능
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int totalRoom = 0; // 성에 있는 방의 개수
        int maxSize = 0; // 가장 넓은 방 넓이

        // 각각의 방을 탐색하며 개수와 넓이를 계산
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    int roomSize = bfs(i, j);
                    totalRoom++;
                    maxSize = Math.max(maxSize, roomSize);
                }
            }
        }

        int maxSizeAfterBreak = 0; // 하나의 벽을 제거하여 얻을 수 있는 가장 넓은 방의 크기

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int temp = map[i][j];
                for (int k = 1; k <= 8; k *= 2) {
                    // 벽이 있는 경우
                    if ((temp & k) == k) {
                        visited = new boolean[m][n];
                        // 벽 제거 후 넓이 계산
                        map[i][j] = temp & ~k;
                        int roomSize = bfs(i, j);
                        maxSizeAfterBreak = Math.max(maxSizeAfterBreak, roomSize);
                        // 벽 원상복구
                        map[i][j] = temp;
                    }
                }
            }
        }

        System.out.println(totalRoom);
        System.out.println(maxSize);
        System.out.println(maxSizeAfterBreak);
    }

    public static int bfs(int x, int y) {
        Queue<Space> queue = new ArrayDeque<>();
        queue.offer(new Space(x, y));
        visited[x][y] = true;
        int count = 1; // 방의 넓이 세기

        while (!queue.isEmpty()) {
            Space space = queue.poll();
            x = space.x;
            y = space.y;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                // 이전에 방문하지 않았고 해당 방향에 벽이 없으면 탐색
                int digit = 1 << i;
//                int digit = (int) Math.pow(2, i);
                if (!visited[nx][ny] && (map[x][y] & digit) == 0) {
                    queue.offer(new Space(nx, ny));
                    visited[nx][ny] = true;
                    count++;
                }
            }
        }
        return count;
    }

    static class Space {
        int x;
        int y;

        public Space(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
