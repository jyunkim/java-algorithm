package baekjoon;

import java.io.*;
import java.util.*;

/**
 * 속도
 * Scanner < BufferedReader
 * System.out < BufferedWriter
 * split < StringTokenizer
 * LinkedList < ArrayDeque
 */
public class FindMin_11003 {

    private static int n, l;
    private static Deque<Pair> dq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        dq = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            int temp = Integer.parseInt(st.nextToken());
            // 새로 들어오는 값보다 큰 수들 제거(출력될 일이 없기 때문)
            while (!dq.isEmpty() && dq.getLast().value > temp) {
                dq.removeLast();
            }
            dq.addLast(new Pair(i, temp));
            // L 범위 넘어간 수 제거
            if (dq.getFirst().index <= i - l) {
                dq.removeFirst();
            }
            System.out.print(dq.getFirst().value + " ");
        }
    }

    static class Pair {

        int index;
        int value;

        public Pair(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }
}
