import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, cnt;
    static List<Integer>[] edges;
    static boolean[] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edges = new ArrayList[N+1];
        visited = new boolean[N+1];
        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }
        int u, v;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            edges[u].add(v);
            edges[v].add(u);
        }
        for (int i = 1; i <= N; i++) {
            if(visited[i]) continue; // 이미 방문한 연결 요소는 생략
            bfs(i);
            cnt++; // 집합 수 1 증가
        }
        System.out.println(cnt);
    }

    static void bfs(int s){ // 시작점
        Queue<Integer> q = new LinkedList<>();
        q.offer(s);
        visited[s] = true;
        int cur, next, size;
        while(!q.isEmpty()) {
            cur = q.poll();
            size = edges[cur].size();
            for (int i = 0; i < size; i++) {
                next = edges[cur].get(i);
                if (visited[next]) continue; // 이미 방문한 상태
                visited[next] = true; // 방문 처리
                q.offer(next);
            }
        }
    }

}