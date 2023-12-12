import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Map<Integer, Integer> map;
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int n;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            map = new HashMap<>();
            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map.put(Integer.parseInt(st.nextToken()), 1);
            }
            M = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                n = Integer.parseInt(st.nextToken());
                if(map.get(n) == null) sb.append(0);
                else sb.append(1);
                sb.append("\n");
            }
        }

        System.out.println(sb.toString());
    }


}