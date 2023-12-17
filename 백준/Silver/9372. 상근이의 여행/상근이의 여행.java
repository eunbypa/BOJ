import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M;
    static List<Integer>[] edges;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int min, a, b;
        for (int t = 0; t < T; t++) {
            min = 0;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            edges = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) {
                edges[i] = new ArrayList<>();
            }
            M = Integer.parseInt(st.nextToken());
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                edges[a].add(b);
                edges[b].add(a);
            }
            min = bfs();
            sb.append(min+"\n");
        }
        System.out.println(sb.toString());
    }

    public static int bfs() {
        Queue<Integer> q = new LinkedList<>();
        int[] visited = new int[N + 1];
        // 1번부터 시작
        visited[1] = 1;
        q.offer(1);
        int next, size, cnt = 0;
        while(!q.isEmpty()) {
            next = q.poll();
            size = edges[next].size();
            for (int i = 0; i < size; i++) {
                if(visited[edges[next].get(i)] == 1) continue;
                visited[edges[next].get(i)] = 1;
                q.offer(edges[next].get(i));
                cnt++;
            }
        }
        return cnt;
    }


}