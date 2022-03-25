package baekjoon;

import java.io.*;
import java.util.*;

public class LectureRoomAssignment_11000 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int answer = 1;
        int n = Integer.parseInt(br.readLine());

        List<Lecture> lectures = new ArrayList<>();
        Queue<Integer> inLecture = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            lectures.add(new Lecture(s, t));
        }
        Collections.sort(lectures);

        inLecture.offer(lectures.get(0).t);

        // 시작 시간이 빠르고 먼저 끝나는 강의부터 처리
        for (int i = 1; i < lectures.size(); i++) {
            Integer t = inLecture.peek();

            if (t <= lectures.get(i).s) { // 다음 수업 시작
                inLecture.poll();
            } else { // 강의실 추가 배정
                answer++;
            }
            inLecture.offer(lectures.get(i).t);
        }

        System.out.println(answer);
    }

    static class Lecture implements Comparable<Lecture> {
        int s;
        int t;

        public Lecture(int s, int t) {
            this.s = s;
            this.t = t;
        }

        @Override
        public int compareTo(Lecture o) {
            return s - o.s;
        }
    }
}
