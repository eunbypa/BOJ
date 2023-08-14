import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static Map<Integer, Integer> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        int n;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            n = Integer.parseInt(st.nextToken());
            map.put(n, 1);
        }
        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            n = Integer.parseInt(st.nextToken());
            if(map.containsKey(n)){
                // 가지고 있음
                sb.append(1);
            } else sb.append(0);
            if(i == M-1) break;
            sb.append(" ");
        }
        System.out.println(sb.toString());
    }
}