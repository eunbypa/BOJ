import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static final int sanggun = 1;
    static int N, M;
    static int[][] edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        edges = new int[N + 1][N + 1];
        StringTokenizer st;
        int a,b;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            edges[a][b] = 1;
            edges[b][a] = 1;
        }

        System.out.println(getInvitedNum());
    }

    public static int getInvitedNum() {
        int cnt = 0;
        Queue<Integer> q = new LinkedList<>();
        int[] visited = new int[N + 1];
        for (int i = 2; i <= N; i++) {
            if(edges[1][i] == 0) continue;
            visited[i] = 1;
            cnt++;
            q.add(i);
        }
        int cur;
        while(!q.isEmpty()) {
            cur = q.poll();
            for (int i = 2; i <= N; i++) {
                if(edges[cur][i] == 1 && visited[i] == 0) {
                    cnt++;
                    visited[i] = 1;
                }
            }
        }
        return cnt;
    }

}