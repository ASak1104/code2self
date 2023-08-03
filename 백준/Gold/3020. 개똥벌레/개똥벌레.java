import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Main {
    static StreamTokenizer sttk = new StreamTokenizer(new InputStreamReader(System.in));
    static List<Integer> downToUp;
    static List<Integer> upToDown;

    static int readInt() throws IOException {
        sttk.nextToken();
        return (int) sttk.nval;
    }

    public static void main(String[] args) throws IOException {
        int n = readInt();
        int h = readInt();

        downToUp = new ArrayList<>(n >>> 1);
        upToDown = new ArrayList<>(n >>> 1);

        for (int i = 0; i < (n >>> 1); i++) {
            downToUp.add(readInt());
            upToDown.add(readInt());
        }

        downToUp.sort(Comparator.naturalOrder());
        upToDown.sort(Comparator.naturalOrder());

        int min = n;
        int count = 0;

        for (int i = 1; i <= h; i++) {
            int sum = bisect(upToDown, h - i + 1) + bisect(downToUp, i);

            if (sum < min) {
                min = sum;
                count = 1;
            } else if (sum == min) {
                count++;
            }
        }

        System.out.printf("%d %d", min, count);
    }

    static int bisect(List<Integer> list, int v) {
        int ret = list.size();
        int s = 0;
        int e = list.size() - 1;

        while (s <= e) {
            int mid = (s + e) >>> 1;

            if (list.get(mid) < v) {
                s = mid + 1;
            } else {
                e = mid - 1;
                ret = mid;
            }
        }

        return list.size() - ret;
    }
}