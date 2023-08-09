import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n,m;
    static int[][] map;
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,-1,0,1};
    static boolean[][] visited;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];
        int r = 0, c = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2){
                    r = i;
                    c = j;
                }
            }
        }
        bfs(r, c);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(visited[i][j]){
                    sb.append(map[i][j]);
                }else{
                    if(map[i][j] == 0) sb.append(map[i][j]);
                    else sb.append(-1);
                }
                if(j == m-1) break;
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    // 목표 지점에서 모든 지점까지의 최단거리를 bfs로 구하기
    static void bfs(int r, int c){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{r, c, 0});
        int[] cur;
        int nr, nc;
        visited[r][c] = true;
        map[r][c] = 0;
        while(!q.isEmpty()){
            cur = q.poll();
            for (int i = 0; i < 4; i++) {
                nr = cur[0] + dr[i];
                nc = cur[1] + dc[i];
                if(!check(nr,nc) || map[nr][nc] == 0) continue;
                if(visited[nr][nc]) continue;
                map[nr][nc] = cur[2] + 1;
                visited[nr][nc] = true;
                q.offer(new int[]{nr, nc, cur[2] + 1});
            }
        }
    }

    private static boolean check(int nr, int nc) {
        return nr >= 0 && nc >= 0 && nr < n && nc < m;
    }


}