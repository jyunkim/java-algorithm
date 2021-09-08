package programmers.kakao;

import java.util.*;

public class CheckDistance {

    // 상하좌우 이동
    int[] dx = {0, 0, -1, 1};
    int[] dy = {1, -1, 0, 0};

    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        for (int i = 0; i < 5; i++) {
            // 대기실 하나씩 검사
            answer[i] = check(places[i]);
        }
        return answer;
    }

    public int check(String[] place) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                // P일 경우 거리두기 준수 확인
                if (place[i].charAt(j) == 'P') {
                    if (!isSafe(place, i, j)) return 0;
                }
            }
        }
        return 1;
    }

    // 주어진 위치의 사람이 거리두기를 준수하는지 확인(bfs)
    public boolean isSafe(String[] place, int x, int y) {
        Queue<Position> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[5][5];

        queue.offer(new Position(x, y));
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            Position p = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                int distance = Math.abs(x - nx) + Math.abs(y - ny);

                if (nx < 0 || nx > 4 || ny < 0 || ny > 4) continue;
                if (visited[nx][ny] || distance > 2) continue;

                if (place[nx].charAt(ny) == 'P') return false;
                else if ((place[nx].charAt(ny) == 'O')) {
                    queue.offer(new Position(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }
        return true;
    }

    // 대기실 내 좌표
    static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
