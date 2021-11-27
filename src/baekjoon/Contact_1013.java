package baekjoon;

import java.io.*;

public class Contact_1013 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        String pattern = "(100+1+|01)+";

        for (int i = 0; i < n; i++) {
            String record = br.readLine();

            // Full match로 동작
            if (record.matches(pattern)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
