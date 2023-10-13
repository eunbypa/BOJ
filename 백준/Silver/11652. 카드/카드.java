import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static Map<Long, Integer> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Long n;
        Integer cnt;
        int max = 0;
        for (int i = 0; i < N; i++) {
            n = Long.parseLong(br.readLine());
            cnt = map.remove(n);
            if(cnt == null) cnt = 0;
            max = Math.max(max, cnt + 1);
            map.put(n, cnt + 1);
        }
        Long ans = null;

        for (Map.Entry<Long, Integer> entry : map.entrySet()) {
            if(entry.getValue() == max) {
                if(ans == null) ans = entry.getKey();
                else ans = Math.min(ans, entry.getKey());
            }
        }
        System.out.println(ans);
    }

}