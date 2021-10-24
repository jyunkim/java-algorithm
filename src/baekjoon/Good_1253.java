package baekjoon;

import java.io.*;
import java.util.*;

public class Good_1253 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] numbers = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numbers);

        // 투 포인터
        int answer = 0;
        for (int i = 0; i < n; i++) {
            int target = numbers[i]; // 좋은 수인지 검사할 숫자
            int l = 0; // 합할 두 수 중 왼쪽 숫자
            int r = n - 1; // 합할 두 수 중 오른쪽 숫자

            while (l < r) {
                int sum = numbers[l] + numbers[r];
                if (sum == target) {
                    // 자신은 합에 포함되면 안되므로 넘어감
                    if (l == i) {
                        l++;
                    } else if (r == i) {
                        r--;
                    } else {
                        answer++;
                        break;
                    }
                } else if (sum < target) {
                    l++;
                } else {
                    r--;
                }
            }
        }

        System.out.println(answer);
    }
}
