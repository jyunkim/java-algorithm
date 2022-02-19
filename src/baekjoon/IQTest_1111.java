package baekjoon;

import java.io.*;
import java.util.*;

public class IQTest_1111 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] numbers = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        if (n == 1) {
            System.out.println('A');
        } else if (n == 2) {
            if (numbers[0] == numbers[1]) {
                System.out.println(numbers[0]);
            } else {
                System.out.println('A');
            }
        } else {
            int a;
            int b;

            if (numbers[1] - numbers[0] == 0) {
                a = 1;
                b = 0;
            } else {
                a = (numbers[2] - numbers[1]) / (numbers[1] - numbers[0]); // 정수가 아니면 답이 될 수 없음
                b = numbers[1] - numbers[0] * a;
            }

            for (int i = 1; i < n - 1; i++) {
                if (numbers[i] * a + b != numbers[i + 1]) {
                    System.out.println('B');
                    return;
                }
            }
            System.out.println(numbers[n - 1] * a + b);
        }
    }
}
