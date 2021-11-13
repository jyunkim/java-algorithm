package baekjoon;

import java.io.*;
import java.util.*;

public class MakeBig_2812 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        String number = br.readLine();

        List<Character> stack = new ArrayList<>();
        for (int i = 0; i < number.length(); i++) {
            char digit = number.charAt(i);
            // 맨 앞 숫자를 최대한 크게 만들어줌
            while (!stack.isEmpty() && k > 0 && stack.get(stack.size() - 1) < digit) {
                stack.remove(stack.size() - 1);
                k--;
            }
            stack.add(digit);
        }

        StringBuilder sb = new StringBuilder();
        // k가 남았을 경우 끝에서 빼줌
        for (int i = 0; i < stack.size() - k; i++) {
            sb.append(stack.get(i));
        }
        System.out.println(sb);
    }
}
