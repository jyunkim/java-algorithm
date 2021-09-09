package programmers.kakao;

import java.util.*;

// 1. 방문한 곳을 다시 방문할 수 있어야 함
// 2. 중간 지점까지는 비용이 커도 도착점에서 비용이 작을 수 있음
// -> 방문한 곳을 다시 방문할 때는 같은 방향에서 방문했을 때 비용이 더 작을 경우 방문
// => 무한 루프 방지, 다른 방향에서 같은 곳을 방문하면 모두 저장한 뒤에 방향이 합쳐질 때 가장 비용이 적은 것을 선택
public class RaceRoad {

    // 상하좌우 이동
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    int answer = Integer.MAX_VALUE;
    int[][][] costs; // 방향을 고려한 비용을 담는 board

    public int solution(int[][] board) {
        costs = new int[board.length][board.length][4];
        bfs(board);
        return answer;
    }

    public void bfs(int[][] board) {
        Queue<Coordinate> queue = new ArrayDeque<>();
        queue.offer(new Coordinate(0, 0));

        while (!queue.isEmpty()) {
            Coordinate coordinate = queue.poll();
            int x = coordinate.x;
            int y = coordinate.y;

            // 도착점일 경우
            if (x == board.length - 1 && y == board.length - 1) {
                answer = Math.min(answer, costs[x][y][coordinate.direction]);
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= board.length || ny < 0 || ny >= board.length) continue;
                if (board[nx][ny] == 1) continue;

                int temp = coordinate.cost;
                if (coordinate.direction == -1 || coordinate.direction == i) { // 시작점이거나 같은 방향으로 이동했을 경우
                    temp += 100;
                } else { // 코너를 돌았을 경우
                    temp += 600;
                }

                // 처음 방문하거나 같은 방향에서 방문했을 때 비용이 더 작을 경우 방문
                if (costs[nx][ny][i] == 0 || temp < costs[nx][ny][i]) {
                    costs[nx][ny][i] = temp;
                    Coordinate newCoordinate = new Coordinate(nx, ny);
                    newCoordinate.direction = i;
                    newCoordinate.cost = temp;
                    queue.offer(newCoordinate);
                }
            }
        }
    }

    static class Coordinate {
        int x;
        int y;
        int direction; // 이전 좌표에서 어느 방향으로 이동했는지
        int cost;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
            direction = -1; // 시작점의 경우 방향 x
            cost = 0;
        }
    }
}
