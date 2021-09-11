import java.util.*;

public class CountMap {

    public static void main(String[] args) {
        String[] arr = {"b", "c", "b", "c", "c", "d", "a"};
        System.out.println(countV1(arr));
        System.out.println(countV2(arr));
        System.out.println(countV3(arr));

        // 정렬이 필요할 경우 treeMap 사용
        Map<String, Integer> sortedMap = new TreeMap<>(countV1(arr));
        System.out.println(sortedMap);
    }

    public static Map<String, Integer> countV1(String[] arr) {
        Map<String, Integer> map = new HashMap<>();

        for (String s : arr) {
            if (map.containsKey(s)) {
                map.replace(s, map.get(s) + 1);
            } else {
                map.put(s, 1);
            }
        }
        return map;
    }

    public static Map<String, Integer> countV2(String[] arr) {
        Map<String, Integer> map = new HashMap<>();

        for (String s : arr) {
            map.putIfAbsent(s, 0);
            map.replace(s, map.get(s) + 1);
        }
        return map;
    }

    public static Map<String, Integer> countV3(String[] arr) {
        Map<String, Integer> map = new HashMap<>();

        for (String s : arr) {
            map.computeIfPresent(s, (key, value) -> value + 1);
            map.computeIfAbsent(s, key -> 1);
        }
        return map;
    }
}
