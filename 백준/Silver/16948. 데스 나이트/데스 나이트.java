import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final int MAX_VALUE = 40000;
    static final int[] dr = {-2, -2, 0, 0, 2, 2};
    static final int[] dc = {-1, 1, -2, 2, -1, 1};


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r1 = Integer.parseInt(st.nextToken());
        int c1 = Integer.parseInt(st.nextToken());
        int r2 = Integer.parseInt(st.nextToken());
        int c2 = Integer.parseInt(st.nextToken());


        System.out.println(getAnswer(N, r1, c1, r2, c2));
    }

    // bfs
    static int getAnswer(int N, int r1, int c1, int r2, int c2) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{r1, c1, 0});
        int[][] dp = new int[N][N];
        init(dp);
        dp[r1][c1] = 0;
        int[] cur;
        int nr, nc;
        while(!q.isEmpty()) {
            cur = q.poll();
            if(cur[0] == r2 && cur[1] == c2) {
                // 도착
                return cur[2];
            }

            for (int i = 0; i < 6; i++) {
                nr = cur[0] + dr[i];
                nc = cur[1] + dc[i];
                // 체스판 바깥으로 나가는 경우
                if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
                if(cur[2] + 1 < dp[nr][nc]) {
                    dp[nr][nc] = cur[2] + 1;
                    q.offer(new int[]{nr, nc, dp[nr][nc]});
                }
            }
        }

        return -1;
    }

    static void init(int[][] arr) {
        int r = arr.length, c = arr[0].length;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                arr[i][j] = MAX_VALUE;
            }
        }
    }


}