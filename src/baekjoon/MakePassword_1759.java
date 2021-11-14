package baekjoon;

import java.io.*;
import java.util.*;

public class MakePassword_1759 {

    static int l;
    static String[] alphabets;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        l = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        alphabets = new String[c];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < c; i++) {
            alphabets[i] = st.nextToken();
        }
        Arrays.sort(alphabets);

        dfs(0, -1, "");
    }

    public static void dfs(int count, int index, String password) {
        if (count == l) {
            if (checkPassword(password)) {
                System.out.println(password);
            }
            return;
        }

        for (int i = index + 1; i < alphabets.length; i++) {
            dfs(count + 1, i, password + alphabets[i]);
        }
    }

    /**
     * 1. 최소 한 개의 모음
     * 2. 최소 두 개의 자음
     */
    public static boolean checkPassword(String password) {
        String temp = password.replaceAll("[aeiou]", "");

        return temp.length() != password.length() && temp.length() >= 2;
    }
}
