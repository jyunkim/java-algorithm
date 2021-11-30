package baekjoon;

import java.util.*;
import java.io.*;

public class Game2048_12100 {

    static int n;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        int[][] board = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(board, 0);
        System.out.println(answer);
    }

    public static void dfs(int[][] board, int count) {
        // 5번 움직였으면 최대값 계산
        if (count == 5) {
            int max = 0;
            for (int[] arr : board) {
                for (int num : arr) {
                    if (num > max) max = num;
                }
            }

            if (max > answer) answer = max;
            return;
        }

        // 4가지 방향으로 움직여봄
        for (int i = 0; i < 4; i++) {
            dfs(move(board, i), count + 1);
        }
    }

    public static int[][] move(int[][] board, int direction) {
        int[][] newBoard = new int[n][n];

        for (int i = 0; i < n; i++) {
            newBoard[i] = board[i].clone(); // deep copy
        }

        switch (direction) {
            case 0: // 위
                for (int i = 0; i < n; i++) {
                    List<Integer> list = new ArrayList<>();
                    for (int j = 0; j < n; j++) {
                        list.add(newBoard[j][i]);
                    }

                    list = merge(list);

                    for (int j = 0; j < n; j++) {
                        newBoard[j][i] = list.get(j);
                    }
                }
                break;
            case 1: // 아래
                for (int i = 0; i < n; i++) {
                    List<Integer> list = new ArrayList<>();
                    for (int j = n - 1; j >= 0; j--) {
                        list.add(newBoard[j][i]);
                    }

                    list = merge(list);

                    for (int j = n - 1; j >= 0; j--) {
                        newBoard[j][i] = list.get(j);
                    }
                }
                break;
            case 2: // 왼쪽
                for (int i = 0; i < n; i++) {
                    List<Integer> list = new ArrayList<>();
                    for (int j = 0; j < n; j++) {
                        list.add(newBoard[i][j]);
                    }

                    list = merge(list);

                    for (int j = 0; j < n; j++) {
                        newBoard[i][j] = list.get(j);
                    }
                }
                break;
            case 3: // 오른쪽
                for (int i = 0; i < n; i++) {
                    List<Integer> list = new ArrayList<>();
                    for (int j = n - 1; j >= 0; j--) {
                        list.add(newBoard[i][j]);
                    }

                    list = merge(list);

                    for (int j = n - 1; j >= 0; j--) {
                        newBoard[i][j] = list.get(j);
                    }
                }
                break;
        }
        return newBoard;
    }

    // 인자로 들어온 리스트를 합침
    public static List<Integer> merge(List<Integer> list) {
        List<Integer> newList = new ArrayList<>();
        int index = 0;
        boolean isMerged = true;

        for (int i = 0; i < n; i++) {
            int num = list.get(i);

            if (num != 0) {
                if (!isMerged && num == newList.get(index - 1)) { // 앞선 숫자가 합쳐지지 않았고, 현재 숫자와 같을 경우 합침
                    newList.set(index - 1, num * 2);
                    isMerged = true;
                } else { // 앞선 숫자가 합쳐졌거나, 현재 숫자와 같지 않을 경우 그대로 삽입
                    newList.add(num);
                    isMerged = false;
                    index++;
                }
            }
        }

        // 빈 공간을 0으로 채워서 길이 맞춰줌
        int size = newList.size();
        for (int i = 0; i < n - size; i++) {
            newList.add(0);
        }

        return newList;
    }
}
