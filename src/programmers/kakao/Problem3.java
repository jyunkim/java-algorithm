package programmers.kakao;

import java.util.*;

public class Problem3 {

    public int[] solution(int[] fees, String[] records) {
        int[] answer;

        int fixedTime = fees[0];
        int fixedCost = fees[1];
        int unitTime = fees[2];
        int unitCost = fees[3];

        // 자동차별 누적 주차 시간
        Map<String, Integer> totalTimeMap = new HashMap<>();
        // 입차된 자동차
        List<String> parked = new LinkedList<>();

        for (String record : records) {
            boolean isParked = false;
            for (String park : parked) {
                String[] splitPark = park.split(" ");
                String[] splitRecord = record.split(" ");

                // 자동차가 입차되어 있는 경우
                if (splitPark[1].equals(splitRecord[1])) {
                    parked.remove(park);
                    isParked = true;

                    String[] splitParkTime = splitPark[0].split(":");
                    String[] splitRecordTime = splitRecord[0].split(":");

                    int totalHour = Integer.parseInt(splitRecordTime[0]) - Integer.parseInt(splitParkTime[0]);
                    int totalMinute = Integer.parseInt(splitRecordTime[1]) - Integer.parseInt(splitParkTime[1]);
                    int totalTime = totalHour * 60 + totalMinute;

                    // 누적 주차 시간 계산
                    totalTimeMap.putIfAbsent(splitRecord[1], 0);
                    totalTimeMap.replace(splitRecord[1], totalTimeMap.get(splitRecord[1]) + totalTime);

                    break;
                }
            }
            // 자동차가 입차되어 있지 않은 경우
            if (!isParked) parked.add(record);
        }

        // 오늘 출차되지 않은 자동차 처리
        for (String park : parked) {
            String[] splitPark = park.split(" ");
            String[] splitParkTime = splitPark[0].split(":");
            String carId = splitPark[1];

            int totalHour = 23 - Integer.parseInt(splitParkTime[0]);
            int totalMinute = 59 - Integer.parseInt(splitParkTime[1]);
            int totalTime = totalHour * 60 + totalMinute;

            totalTimeMap.putIfAbsent(carId, 0);
            totalTimeMap.replace(carId, totalTimeMap.get(carId) + totalTime);
        }

        answer = new int[totalTimeMap.size()];
        List<String> cars = new ArrayList<>(totalTimeMap.keySet());
        Collections.sort(cars);

        for (Map.Entry<String, Integer> entry : totalTimeMap.entrySet()) {
            int totalCost = 0;
            if (entry.getValue() <= fixedTime) {
                totalCost = fixedCost;
            } else {
                totalCost = fixedCost + (int)Math.ceil((entry.getValue() - fixedTime) / (double)unitTime) * unitCost;
            }
            answer[cars.indexOf(entry.getKey())] = totalCost;
        }

        return answer;
    }
}
