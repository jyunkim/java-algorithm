package baekjoon;

import java.util.*;
import java.io.*;

public class Recruitment_11578 {

    private static List<List<Integer>> combinations = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        List<Integer> memberList = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int problemNumbers = 0;

            for (int j = 0; j < p; j++) {
                int o = Integer.parseInt(st.nextToken());
                problemNumbers |= (1 << (o - 1));
            }
            memberList.add(problemNumbers);
        }

    }
}
