import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static char[][] map;
    static int ans;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[5][5];
        for (int i = 0; i < 5; i++) {
            map[i] = br.readLine().toCharArray();
        }
        dfs(new int[7], 0, 0, 0);
        System.out.println(ans);
    }

    public static void dfs(int[] selected, int dy, int cur, int cnt) {
        if(cnt == 7){
            if(isConnected(selected) && dy <= 3) {
                ans++;
            }
            return;
        }
        int nr, nc, ndy;
        for (int i = cur; i < 25; i++) {
            nr = i / 5;
            nc = i % 5;
            if(isDoYeonWin(dy, nr, nc)) continue;
            selected[cnt] = i;
            ndy = (map[nr][nc] == 'Y') ? dy + 1 : dy;
            dfs(selected, ndy, i+1,cnt+1);
            selected[cnt] = 0;
        }
    }

    public static boolean isConnected(int[] selected) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(selected[0]);
        int cur, cr, cc, nr, nc;
        int[] visited = new int[7];
        int cnt = 0;
        while(!q.isEmpty()) {
            cur = q.poll();
            cr = cur / 5;
            cc = cur % 5;
            for (int i = 0; i < 4; i++) {
                nr = cr + dr[i];
                nc = cc + dc[i];
                for (int j = 0; j < 7; j++) {
                    if(nr < 0 || nc < 0 || nr >= 5 || nc >= 5) continue;
                    if(selected[j] == cur) continue;
                    if(visited[j] == 0 && nr*5 + nc == selected[j]) {
                        visited[j] = 1;
                        cnt++;
                        q.offer(selected[j]);
                    }
                }
            }
        }
        return cnt == 7;
    }

    public static boolean isDoYeonWin(int dy, int r, int c) {
        if(map[r][c] == 'S') return false;
        return (dy + 1) > 3;
    }

}