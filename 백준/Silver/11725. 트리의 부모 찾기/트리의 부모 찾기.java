import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static List<Integer>[] edges;
    static int[] p;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        p = new int[N + 1];
        edges = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }
        StringTokenizer st;
        int a, b;
        int d;
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            edges[a].add(b);
            edges[b].add(a);
        }
        bfs();
        for (int i = 2; i <= N; i++) {
            sb.append(p[i]).append("\n");
        }

        System.out.println(sb.toString());
    }

    // 루트부터 bfs
    public static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        int[] visited = new int[N + 1];
        visited[1] = 1;
        q.offer(1);
        int cur, next, size;
        while(!q.isEmpty()) {
            cur = q.poll();
            size = edges[cur].size();
            for (int i = 0; i < size; i++) {
                next = edges[cur].get(i);
                if(visited[next] == 1) continue;
                visited[next] = 1;
                p[next] = cur;
                q.offer(next);
            }
        }
    }


}