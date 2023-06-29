import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] edges;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 도시(정점)의 개수
        edges = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                edges[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < N; i++) {
            bfs(i);
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(edges[i][j]);
                if(j == N-1) continue;
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    //가중치가 없으므로 bfs
    static void bfs(int s){ // 시작점
        Queue<Integer> q = new LinkedList<>();
        q.offer(s);
        int cur;
        boolean[] visited = new boolean[N];
        while(!q.isEmpty()){
            cur = q.poll();
            for (int i = 0; i < N; i++) {
                if(edges[cur][i] == 0 || visited[i]) continue; // 간선 없음
                visited[i] = true;
                edges[s][i] = 1; // 인접행렬 갱신
                q.offer(i);
            }
        }
    }


}