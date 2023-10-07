import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main{
    static int N,M;
    static Map<String, String> map = new HashMap<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        String site, password;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            site = st.nextToken();
            password = st.nextToken();
            map.put(site, password);
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            site = st.nextToken();
            sb.append(map.get(site)).append("\n");
        }
        System.out.println(sb.toString());
    }

}