package baekjoon;

import java.util.*;
import java.io.*;

public class PaperPiece_14391 {

    private static int answer = 0;
    private static int n;
    private static int m;
    private static int[][] paper;
    private static char[][] direction;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        paper = new int[n][m];
        direction = new char[n][m];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                paper[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
            }
        }

        dfs(0);
        System.out.println(answer);
    }

    private static void dfs(int index) {
        if (index == n * m) {
            answer = Math.max(answer, sum());
            return;
        }

        int r = index / m;
        int c = index % m;

        direction[r][c] = 'h'; // 가로 조각
        dfs(index + 1);
        direction[r][c] = 'v'; // 세로 조각
        dfs(index + 1);
    }

    // 자른 종이 조각의 합을 구한다
    private static int sum() {
        int sum = 0;
        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < n * m; i++) {
            int r = i / m;
            int c = i % m;

            if (visited[r][c]) {
                continue;
            }

            StringBuilder sb = new StringBuilder();
            int j = 0;
            while (c + j < m && direction[r][c + j] == 'h') { // 가로 조각
                sb.append(paper[r][c + j]);
                visited[r][c + j] = true;
                j++;
            }
            j = 0;
            while (r + j < n && direction[r + j][c] == 'v') { // 세로 조각
                sb.append(paper[r + j][c]);
                visited[r + j][c] = true;
                j++;
            }

            sum += Integer.parseInt(sb.toString());
        }
        return sum;
    }
}
