import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int w,h;
    static int[][] map;
    static int[] dr = {-1, 0, 1, 0, 1, 1, -1, -1};
    static int[] dc = {0, -1, 0, 1, 1, -1, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        boolean fin = false;
        int cnt = 0;
        while(!fin) {
            cnt = 0;
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            if(w == 0 && h == 0) {
                fin = true;
                continue;
            }
            map = new int[h][w];
            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if(map[i][j] == 0) continue;
                    cnt++;
                    bfs(i, j);
                }
            }
            sb.append(cnt+"\n");
        }
        System.out.println(sb.toString());
    }

    // flood-fill
    private static void bfs(int i, int j) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{i, j});
        map[i][j] = 0;
        int[] cur;
        int nr,nc;
        while(!q.isEmpty()){
            cur = q.poll();
            for (int k = 0; k < 8; k++) {
                nr = cur[0] + dr[k];
                nc = cur[1] + dc[k];
                if(!check(nr,nc) || map[nr][nc] == 0) continue;
                map[nr][nc] = 0;
                q.offer(new int[]{nr, nc});
            }
        }
    }

    private static boolean check(int nr, int nc) {
        return nr >= 0 && nc >= 0 && nr < h && nc < w;
    }
}