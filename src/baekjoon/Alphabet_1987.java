package baekjoon;

import java.util.*;
import java.io.*;

// 지금까지 지나온 -> dfs
public class Alphabet_1987 {

    static int r, c;
    static int answer = 0;
    static char[][] board;
    static boolean[][] visited; // 방문한 좌표인지 체크 0 = A, 1 = B
    // 상하좌우 이동
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[] alphabet; // 방문한 알파벳인지 체크

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        board = new char[r][c];
        visited = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            board[i] = br.readLine().toCharArray();
        }
        alphabet = new boolean[26];

        dfs(0, 0, 1);
        System.out.println(answer);
    }

    public static void dfs(int x, int y, int count) {
        // 이전보다 많이 움직였으면 갱신
        if (count > answer) answer = count;

        visited[x][y] = true;
        alphabet[board[x][y] - 'A'] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 범위를 벗어날 경우
            if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
            // 지금까지 방문하지 않은 위치, 알파벳일 경우 방문
            if (!visited[nx][ny] && !alphabet[board[nx][ny] - 'A']) {
                dfs(nx, ny, count + 1); // ++count를 할 경우 현재 count값이 증가하므로 사용 x
                visited[nx][ny] = false;
                alphabet[board[nx][ny] - 'A'] = false;
            }
        }
    }
}
