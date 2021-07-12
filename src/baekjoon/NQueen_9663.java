package baekjoon;

import java.io.*;

public class NQueen_9663 {

    private static int n;
    private static int[][] board;
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        dfs(0);
        System.out.println(answer);
    }

    // 백트래킹
    public static void dfs(int line) {
        // 종료 조건
        if (line == n) {
            answer++;
            return;
        }
        for (int j = 0; j < n; j++) {
            if (check(line, j)) {
                board[line][j] = 1;
                dfs(line + 1);
                board[line][j] = 0;
            }
        }
    }

    public static boolean check(int row, int col) {
        // 좌우
        for (int j = 0; j < n; j++) {
            if (board[row][j] != 0) {
                return false;
            }
        }
        // 상
        for (int i = 0; i < row; i++) {
            if (board[i][col] != 0) {
                return false;
            }
        }
        // 대각
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] != 0) {
                return false;
            }
        }
        // 대각
        for (int i = row, j = col; i >= 0 && j < n; i--, j++) {
            if (board[i][j] != 0) {
                return false;
            }
        }
        return true;
    }
}
