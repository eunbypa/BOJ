import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static List<Integer>[] list;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());
            list = new ArrayList[N + 1];
            visited = new boolean[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                list[j] = new ArrayList<>();
                list[j].add(Integer.parseInt(st.nextToken()));
            }
            int cnt = 0;
            for (int j = 1; j <= N; j++) {
                if(visited[j]) continue;
                visited[j] = true;
                dfs(list[j].get(0), j);
                cnt++;
            }
            sb.append(cnt).append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void dfs(int cur, int first) {
        visited[cur] = true;
        if(cur == first) return;
        dfs(list[cur].get(0), first);
    }

}