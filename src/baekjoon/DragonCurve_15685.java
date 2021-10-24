package baekjoon;

import java.io.*;
import java.util.*;

public class DragonCurve_15685 {

    static boolean[][] grid = new boolean[101][101]; // 해당 꼭짓점이 드래곤커브의 일부인지 아닌지
    // 방향 - 0, 1, 2, 3
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            // x: 열, y: 행
            // 시작 지점
            grid[y][x] = true;

            // 시작 지점부터 드래곤커브의 방향들을 저장
            List<Integer> directions = new ArrayList<>();
            directions.add(d);
            for (int j = 0; j < g; j++) {
                // 각 세대의 마지막 지점부터 시계방향으로 90도 회전한 방향을 저장
                for (int k = directions.size() - 1; k >= 0; k--) {
                    directions.add((directions.get(k) + 1) % 4);
                }
            }

            // 시작 지점부터 드래곤 커브를 찍음
            for (Integer direction : directions) {
                x += dx[direction];
                y += dy[direction];
                grid[y][x] = true;
            }
        }

        int answer = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                // 정사각형의 네 꼭짓점이 모두 드래곤 커브의 일부면 개수 추가
                if (grid[i][j] && grid[i + 1][j] && grid[i][j + 1] && grid[i + 1][j + 1]) answer++;
            }
        }
        System.out.println(answer);
    }
}
