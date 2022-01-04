package programmers.kakao;

// Unsovled

// BFS - 가중치가 없거나 동일한 그래프에서의 최단 경로
// 다익스트라 - 음이 아닌 가중 그래프에서의 최단 경로
public class MazeEscape {

    int answer = Integer.MAX_VALUE;

    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        for (int[] road : roads) {
            // 출발 방을 찾아 탐색 시작
            if (road[0] == start) {
                dfs(end, roads, traps, start, 0);
            }
        }
        return answer;
    }

    public void dfs(int end, int[][] roads, int[] traps, int room, int count) {
        // 도착 방에 도착했을 때 더 적은 시간이 걸렸으면 갱신
        if (room == end) {
            answer = Math.min(answer, count);
            return;
        }

        // 트랩일 경우
        for (int trap : traps) {
            if (room == trap) {
                for (int[] road : roads) {
                    // 연결된 화살표 방향 변경
                    if (road[0] == trap || road[1] == trap) {
                        int temp;
                        temp = road[0];
                        road[0] = road[1];
                        road[1] = temp;
                    }
                }
            }
        }

        // 갈 수 있는 방으로 이동
        for (int[] road : roads) {
            if (road[0] == room) {
                dfs(end, roads, traps, road[1], count + road[2]);
            }
        }
    }
}
