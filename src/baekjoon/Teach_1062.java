package baekjoon;

import java.io.*;
import java.util.*;

public class Teach_1062 {
    private static int n, k;
    private static List<String> words; // 다형성 이용 -> 매개변수 같은 타입 정의 시 더 유연
    private static int answer = 0;
    private static boolean[] learned;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        String str;
        words = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            str = br.readLine();
            words.add(str.substring(4, str.length() - 4));
        }

        // 학습한 단어 체크
        // index 계산
        learned = new boolean[26];
        learned['a' - 'a'] = true;
        learned['c' - 'a'] = true;
        learned['i' - 'a'] = true;
        learned['n' - 'a'] = true;
        learned['t' - 'a'] = true;

        if (k < 5) {
            System.out.println(0);
        } else if (k == 26) {
            System.out.println(n);
        } else {
            check(0, 0);
            System.out.println(answer);
        }
    }

    // 백트래킹
    public static void check(int num, int index) {
        // 종료 조건
        // 다 배웠으면 개수 확인
        if (num == k - 5) {
            int count = 0;
            for (String word : words) {
                boolean ok = true;
                for (int i = 0; i < word.length(); i++) {
                    if (!learned[word.charAt(i) - 'a']) {
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    count++;
                }
            }
            answer = Math.max(answer, count);
            return;
        }
        // 학습
        for (int i = index; i < learned.length; i++) {
            if (!learned[i]) {
                learned[i] = true;
                check(num + 1, i + 1);
                learned[i] = false;
            }
        }
    }
}
