package baekjoon;

import java.io.*;
import java.util.*;

public class PolygonArea_2166 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        List<Point> points = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            long x = Integer.parseInt(st.nextToken());
            long y = Integer.parseInt(st.nextToken());
            points.add(new Point(x, y));
        }
        points.add(points.get(0));

        // 합이 int 범위를 벗어나므로 long 선언
        long sum1 = 0;
        long sum2 = 0;

        // 신발끈 공식
        for (int i = 0; i < n; i++) {
            sum1 += points.get(i).x * points.get(i + 1).y;
            sum2 += points.get(i).y * points.get(i + 1).x;
        }

        // 소수점 둘째자리에서 반올림하여 첫째 자리까지 출력
        System.out.printf("%.1f", (Math.abs(sum1 - sum2) / 2.0));
    }

    static class Point {
        long x;
        long y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
}
