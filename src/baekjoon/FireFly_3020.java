package baekjoon;

import java.io.*;
import java.util.*;

// 누적합
public class FireFly_3020 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        int[] section = new int[h];

        // 배열에 길이 끝 부분 표시
        // 길이 시작점 index +1, 길이 끝점 다음 index -1
        for (int i = 0; i < n; i++) {
            int length = Integer.parseInt(br.readLine());
            if (i % 2 == 1) {
                // 종유석
                section[0]++;
                section[length]--;
            } else {
                // 석순
                section[h - length]++;
            }
        }

        // 누적합 계산
        for (int i = 1; i < h; i++) {
            section[i] += section[i - 1];
        }

        int answer = 200000;
        int count = 0;
        for (int i = 0; i < h; i++) {
            if (section[i] < answer) {
                answer = section[i];
                count = 1;
            } else if (section[i] == answer) {
                count++;
            }
        }
        System.out.println(answer + " " + count);
    }
}

// 완전 탐색 - 시간 초과
/*
public class FireFly_3020 {
    private static int n, h;
    private static int[] seok, jong;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        seok = new int[100000];
        jong = new int[100000];
        for (int i = 0; i < n / 2; i++) {
            seok[i] = Integer.parseInt(br.readLine());
            jong[i] = Integer.parseInt(br.readLine());
        }

        int answer = 200000;
        int count = 0;
        for (int i = 0; i < h; i++) {
            int destroy = 0;
            for (int j = 0; j < n / 2; j++) {
                if (h - seok[j] <= i) {
                    destroy++;
                }
                if (jong[j] > i) {
                    destroy++;
                }
            }
            if (destroy < answer) {
                answer = destroy;
                count = 1;
            } else if (destroy == answer) {
                count++;
            }
        }
        System.out.println(answer + " " + count);
    }
}
*/
