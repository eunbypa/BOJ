import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N,M;
    static int B, W;
    static char[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0,-1, 0, 1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[M][N];
        for (int i = 0; i < M; i++) {
            map[i] = br.readLine().toCharArray();
        }
        // bfs
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] == '.') continue; // 체크 끝
                if(map[i][j] == 'B') B += bfs(i, j);
                if(map[i][j] == 'W') W += bfs(i, j);
            }
        }
        System.out.println(W + " " + B);
    }

    public static int bfs(int r, int c) {
        Queue<int[]> q = new LinkedList<>();
        int cnt = 1;
        char team = map[r][c];
        map[r][c] = '.';
        q.offer(new int[]{r, c});
        int nr, nc;
        int[] cur;
        while(!q.isEmpty()){
            cur = q.poll();
            for (int i = 0; i < 4; i++) {
                nr = cur[0] + dr[i];
                nc = cur[1] + dc[i];
                if(!check(nr,nc) || map[nr][nc] == '.' || map[nr][nc] != team) continue;
                map[nr][nc] = '.';
                cnt++;
                q.offer(new int[]{nr, nc});
            }
        }
        return cnt * cnt;
    }

    public static boolean check(int r, int c) {
        return r >= 0 && c >= 0 && r < M && c < N;
    }
}