package baekjoon;

import java.util.*;
import java.io.*;

// 매번 선행 건물의 건설 시간을 계산하지 않고 첫번째 계산 시 선행 건물의 건설 시간 저장(메모이제이션) -> 일종의 dp
public class GameDevelop_1516 {

    private static Building[] buildings;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        buildings = new Building[n + 1];

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int buildTime = Integer.parseInt(st.nextToken());
            Building building = new Building(buildTime);

            while (true) {
                int prerequisite = Integer.parseInt(st.nextToken());
                if (prerequisite == -1) {
                    break;
                }
                building.prerequisites.add(prerequisite);
            }
            buildings[i] = building;
        }

        for (int i = 1; i <= n; i++) {
            Building building = buildings[i];
            setTotalTime(building);
            System.out.println(building.totalTime);
        }
    }

    private static void setTotalTime(Building building) {
        // 선행 건물이 없을 경우 총 건설 시간 = 자신의 건설 시간
        if (building.prerequisites.isEmpty()) {
            building.totalTime = building.buildTime;
            return;
        }
        // 총 건설 시간 = 자신의 건설 시간 + 선행 건물의 총 건설 시간 중 가장 큰 값
        int maxTotalTime = 0;
        for (Integer prerequisite : building.prerequisites) {
            Building preBuilding = buildings[prerequisite];
            // 선행 건물의 총 건설 시간이 계산되지 않았으면 계산
            if (preBuilding.totalTime == 0) {
                setTotalTime(preBuilding);
            }
            maxTotalTime = Math.max(maxTotalTime, preBuilding.totalTime);
        }
        building.totalTime = building.buildTime + maxTotalTime;
    }

    static class Building {
        int buildTime;
        int totalTime = 0;
        List<Integer> prerequisites = new ArrayList<>();

        public Building(int buildTime) {
            this.buildTime = buildTime;
        }
    }
}
