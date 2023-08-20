import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int N, max;
    static List<Integer> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            max = 0;
            N = Integer.parseInt(br.readLine());
            list = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                list.add(Integer.parseInt(st.nextToken()));
            }
            Collections.sort(list);
            // 제일 큰값은 양옆 비교
            max = Math.max(list.get(N - 1) - list.get(N - 2), list.get(N - 1) - list.get(N - 3));
            for (int i = N-2; i >= 2; i--) {
                max = Math.max(max, list.get(i) - list.get(i - 2));
            }
            // 제일 작은 두값 비교
            max = Math.max(max, list.get(1) - list.get(0));
            sb.append(max);
            if(t == T-1) break;
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

}