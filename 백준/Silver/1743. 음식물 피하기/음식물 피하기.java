import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static int max;
    static int[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N+1][M+1];
        int r, c;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            map[r][c] = 1;
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if(map[i][j] == 0) continue;
                max = Math.max(max, bfs(i, j));
            }
        }
        System.out.println(max);
    }

    // flood-fill
    public static int bfs(int r, int c) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{r, c});
        int[] cur;
        int nr,nc;
        int area = 1;
        map[r][c] = 0;
        while(!q.isEmpty()){
            cur = q.poll();
            for (int i = 0; i < 4; i++) {
                nr = cur[0] + dr[i];
                nc = cur[1] + dc[i];
                if(!check(nr,nc) || map[nr][nc] == 0) continue;
                q.offer(new int[]{nr, nc});
                map[nr][nc] = 0;
                area++;
            }
        }
        return area;
    }

    // 영역 밖인지 검사하는 함수
    public static boolean check(int r, int c) {
        return r >= 1 && c >= 1 && r <= N && c <= M;
    }

}