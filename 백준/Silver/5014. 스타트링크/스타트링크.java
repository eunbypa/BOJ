import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int F,S,G,U,D;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        F = Integer.parseInt(st.nextToken()); // 최대 높이
        S = Integer.parseInt(st.nextToken()); // 시작
        G = Integer.parseInt(st.nextToken()); // 도착
        U = Integer.parseInt(st.nextToken()); // 업
        D = Integer.parseInt(st.nextToken()); // 다운

        int min = bfs();
        if(min == -1) System.out.println("use the stairs");
            else System.out.println(min);
    }
    public static int bfs() {
        Queue<int[]> q = new LinkedList<>();
        boolean[] visited = new boolean[F + 1];
        q.offer(new int[]{S, 0});
        visited[S] = true;
        int[] cur;
        while(!q.isEmpty()){
            cur = q.poll();
            if(cur[0] == G){
                return cur[1];
            }
            // Up
            if(cur[0] + U <= F && !visited[cur[0]+U]) {
                visited[cur[0]+U] = true;
                q.offer(new int[]{cur[0] + U, cur[1] + 1});
            }
            // Down
            if(cur[0] - D >= 1 && !visited[cur[0]-D]) {
                visited[cur[0]-D] = true;
                q.offer(new int[]{cur[0] - D, cur[1] + 1});
            }

        }
        return -1;
    }
}
