package baekjoon;

import java.io.*;
import java.util.*;

public class Slope_14890 {

    static int n, l;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            // 행 검사
            if (check(i, 0, 0)) answer++;
            // 열 검사
            if (check(0, i, 1)) answer++;
        }

        System.out.println(answer);
    }

    /**
     * @param x,y: 탐색 시작 좌표
     * @param flag: 0 = 행, 1 = 열
     * @return 길을 건널 수 있으면 true, 없으면 false
     */
    public static boolean check(int x, int y, int flag) {
        // 탐색할 길의 높이 저장
        int[] height = new int[n];
        for (int i = 0; i < n; i++) {
            if (flag == 0) {
                height[i] = map[x][i];
            } else {
                height[i] = map[i][y];
            }
        }

        int startIndex = 0;
        boolean goDown = false;
        for (int i = 0; i < n - 1; i++) {
            // 높이가 같으면 다음 index 탐색
            if (height[i] == height[i + 1]) continue;
            // 높이 차이가 1보다 크면 건널 수 없음
            if (Math.abs(height[i] - height[i + 1]) > 1) return false;

            // 이전에 내려왔던 경우, 지금까지의 낮은 지점이 l개 이상이어야 함
            if (goDown && i - startIndex + 1 < l) return false;

            if (height[i] < height[i + 1]) { // 올라가는 경우
                // 지금까지의 낮은 지점이 l개 이상이어야 함
                if (i - startIndex + 1 < l) return false;
                // 이전에 내려왔는데 올라가는 경우, 지금까지의 낮은 지점이 2l개 이상이어야 함
                if (goDown && i - startIndex + 1 < 2 * l) return false;
                goDown = false;
            } else { // 내려가는 경우
                goDown = true;
            }
            // 높이가 변하는 시작 index 저장
            startIndex = i + 1;
        }

        // 마지막에 내려왔을 경우, 지금까지의 낮은 지점이 l개 이상이어야 함
        if (goDown && n - startIndex < l) return false;

        return true;
    }
}
