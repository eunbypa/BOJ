import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, s;
    static int[] A;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        A = new int[n];
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        s = Integer.parseInt(br.readLine());

        System.out.println(bfs());
    }

    public static int bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.offer(s-1);
        int[] visited = new int[n];
        visited[s-1] = 1;
        int cnt = 1, cur;
        while(!q.isEmpty()) {
            cur = q.poll();
            // 왼쪽 or 오른쪽
            if(cur - A[cur] >= 0 && visited[cur - A[cur]] == 0) {
                cnt++;
                visited[cur - A[cur]] = 1;
                q.offer(cur - A[cur]);
            }
            if(cur + A[cur] < n && visited[cur + A[cur]] == 0) {
                cnt++;
                visited[cur + A[cur]] = 1;
                q.offer(cur + A[cur]);
            }
        }
        return cnt;
    }



}