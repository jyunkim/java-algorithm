package baekjoon;

import java.io.*;
import java.util.*;

public class PhoneNumberList_5052 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            List<String> numbers = new ArrayList<>();

            for (int j = 0; j < n; j++) {
                numbers.add(br.readLine());
            }
            Collections.sort(numbers);

            boolean done = false;
            // 현재 인덱스의 번호가 다음 인덱스의 번호의 접두사가 아니면 일관성이 없는 목록
            // 다음 인덱스의 번호의 접두사가 아니면 그 뒤 인덱스의 번호들의 접두사가 될 수 없음
            for (int j = 0; j < n - 1; j++) {
                if (numbers.get(j + 1).startsWith(numbers.get(j))) {
                    done = true;
                    break;
                }
            }

            if (done) {
                System.out.println("NO");
            } else {
                System.out.println("YES");
            }
        }
    }
}
