import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int N,M;
    static Map<String, String> map = new HashMap<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int idx = 0;
        String name;
        for (int i = 0; i < N; i++) {
            idx++;
            name = br.readLine();
            map.put(name, String.valueOf(idx));
            map.put(String.valueOf(idx), name);
        }
        for (int i = 0; i < M; i++) {
            sb.append(map.get(br.readLine())).append("\n");
        }
        System.out.println(sb.toString());
    }

}