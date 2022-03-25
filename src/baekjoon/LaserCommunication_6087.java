package baekjoon;

import java.util.*;
import java.io.*;

public class LaserCommunication_6087 {

    static int w, h;
    static int answer = Integer.MAX_VALUE;
    static char[][] map; // 지도
    static int[][][] count; // 해당 지점까지 설치해야 하는 거울 개수. 들어온 방향까지 고려
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static List<Space> endpoint = new ArrayList<>(); // 출발점과 끝점

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        map = new char[h][w];
        count = new int[h][w][4];

        for (int i = 0; i < h; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < w; j++) {
                if (map[i][j] == 'C') {
                    endpoint.add(new Space(i, j, -1));
                }
            }
        }

        bfs();
        System.out.println(answer);
    }

    public static void bfs() {
        Queue<Space> queue = new ArrayDeque<>();
        Space start = endpoint.get(0);
        Space end = endpoint.get(1);

        queue.offer(start);

        while (!queue.isEmpty()) {
            Space space = queue.poll();
            int x = space.x;
            int y = space.y;
            int direction = space.direction;

            // 도착점에 다다르면 거울 개수 검사 후 갱신
            if (x == end.x && y == end.y) {
                answer = Math.min(answer, count[x][y][direction]);
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 범위를 벗어나거나 벽일 경우 방문 x
                if (nx < 0 || nx >= h || ny < 0 || ny >= w || map[nx][ny] == '*') continue;

                int temp;
                if (direction == -1) { // 시작 지점일 경우
                    temp = 0;
                } else if (direction != i) { // 레이저 방향이 꺾여야 되면 거울 개수 추가
                    temp = count[x][y][direction];
                    temp++;
                } else {
                    temp = count[x][y][direction];
                }

                // 해당 방향으로 처음 방문하거나 이전과 같은 방향에서 방문했을 때 설치해야 되는 거울의 개수가 더 적으면 방문
                if (count[nx][ny][i] == 0 || temp < count[nx][ny][i]) {
                    count[nx][ny][i] = temp;
                    queue.offer(new Space(nx, ny, i));
                }
            }
        }
    }

    // 맵의 (x, y) 좌표의 공간
    static class Space {
        int x;
        int y;
        int direction; // 이전에 들어온 방향 (0: 상, 1: 하, 2: 좌, 3: 우)

        public Space(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }
}
