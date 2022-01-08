package baekjoon;

import java.io.*;
import java.util.*;

// 이분 탐색
// 조절 대상 - 가장 인접한 공유기 사이 최대 거리
public class InstallRouter_2110 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] houses = new int[n];

        for (int i = 0; i < n; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(houses);

        int low = 1;
        int high = houses[n - 1];
        int answer = 0;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (countAvailableRouters(mid, houses) < c) { // 거리 기준을 줄여야 함
                high = mid - 1;
            } else { // 거리 기준을 늘려야 함(개수가 일치하더라도 최적의 답이 아닐 수 있으므로)
                answer = mid;
                low = mid + 1;
            }
        }

        System.out.println(answer);
    }

    private static int countAvailableRouters(int distance, int[] array) {
        int index = 0;
        int count = 1;

        for (int i = 1; i < array.length; i++) {
            if (array[i] - array[index] >= distance) {
                index = i;
                count++;
            }
        }
        return count;
    }
}
