package baekjoon;

import java.util.*;
import java.io.*;

public class Bakery_3109 {

    private static final int[] dx = {1, 0, -1};

    private static char[][] graph;
    private static boolean[][] visited;
    private static int r;
    private static int c;
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        graph = new char[r][c];
        visited = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            graph[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < r; i++) {
            dfs(i);
        }

        System.out.println(answer);
    }

    // 한번 끝까지 가면 끝내고 다시 탐색해야하므로 스택으로 구현
    // 스택은 가장 마지막에 넣은걸 먼저 꺼내므로 순서 반대로
    private static void dfs(int x) {
        Stack<Point> stack = new Stack<>();
        stack.push(new Point(x, 0));

        while (!stack.isEmpty()) {
            Point point = stack.pop();
            visited[point.x][point.y] = true;

            if (point.y == c - 1) {
                answer++;
                return;
            }

            // 오른쪽 위 - 오른쪽 - 오른쪽 아래
            for (int i = 0; i < 3; i++) {
                int nx = point.x + dx[i];
                int ny = point.y + 1;

                if (nx < 0 || nx >= r || ny >= c || visited[nx][ny] || graph[nx][ny] == 'x') {
                    continue;
                }
                stack.push(new Point(nx, ny));
            }
        }
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
