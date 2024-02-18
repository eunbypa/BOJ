import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] connected;
    static List<Integer>[] edges;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        connected = new int[N + 1][N + 1];
        edges = new ArrayList[N + 1];
        M = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int n;
        for (int t = 1; t <= N; t++) {
            edges[t] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                n = Integer.parseInt(st.nextToken());
                if(n == 1) edges[t].add(i);
                connected[t][i] = n;
            }
        }
        for (int i = 1; i <= N; i++) {
            bfs(i);
        }
        st = new StringTokenizer(br.readLine());
        boolean success = true;
        int b;
        b = Integer.parseInt(st.nextToken());
        for (int i = 1; i < M; i++) {
            n = Integer.parseInt(st.nextToken());
            if(b != n && connected[b][n] == 0) success = false;
            b = n;
        }
        String result = (success) ? "YES" : "NO";
        System.out.println(result);
    }

    // bfs
    private static void bfs(int n){
        Queue<Integer> q = new LinkedList<>();
        int[] visited = new int[N + 1];
        q.offer(n);
        visited[n] = 1;
        int cur, next, size;
        while(!q.isEmpty()) {
            cur = q.poll();
            size = edges[cur].size();
            for (int i = 0; i < size; i++) {
                next = edges[cur].get(i);
                if(visited[next] == 1) continue;
                visited[next] = 1;
                connected[n][next] = 1;
                q.offer(next);
            }
        }
    }

}