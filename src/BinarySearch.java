import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinarySearch {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 3, 5, 6));
        System.out.println(lowerBound(list, 3));
        System.out.println(upperBound(list, 3));
    }

    private static int lowerBound(List<Integer> list, int key) {
        int low = 0;
        int high = list.size() - 1;
        int lowerBound = list.size();

        while (low <= high) {
            int mid = (low + high) / 2;

            if (key <= list.get(mid)) {
                lowerBound = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return lowerBound;
    }

    private static int upperBound(List<Integer> list, int key) {
        int low = 0;
        int high = list.size() - 1;
        int lowerBound = list.size();

        while (low <= high) {
            int mid = (low + high) / 2;

            if (key < list.get(mid)) {
                lowerBound = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return lowerBound;
    }
}
