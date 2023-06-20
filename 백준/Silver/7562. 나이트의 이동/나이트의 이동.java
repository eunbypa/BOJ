import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int l;
    static int[] start, end;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int t = 0; t < T; t++) {
            l = Integer.parseInt(br.readLine());
            start = new int[2];
            end = new int[2];
            visited = new boolean[l][l];
            st = new StringTokenizer(br.readLine());
            start[0] = Integer.parseInt(st.nextToken());
            start[1] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            end[0] = Integer.parseInt(st.nextToken());
            end[1] = Integer.parseInt(st.nextToken());
            int cnt = bfs();
            sb.append(cnt+"\n");
        }
        System.out.println(sb.toString());
    }
    // 너비 우선 탐색
    /**
     * @return 최소 이동 횟수
     */
    static int bfs(){
        int cnt = 0;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{start[0], start[1], 0});
        int[] cur;
        int nr, nc;
        while(!q.isEmpty()){
            cur = q.poll();
            if(cur[0] == end[0] && cur[1] == end[1]){ // 도착
                cnt = cur[2];
                break;
            }
            for (int i = 0; i < 4; i++) {
                //우선 2칸 전진
                nr = cur[0] + 2*dr[i];
                nc = cur[1] + 2*dc[i];
                //해당 방향에 수직인 방향으로 1칸 이동
                for (int j = 0; j < 4; j++) {
                    if(!checkD(i, j)) continue;
                    if(!check(nr+dr[j],nc+dc[j]) || visited[nr+dr[j]][nc+dc[j]]) continue;
                    visited[nr+dr[j]][nc+dc[j]] = true;
                    q.offer(new int[]{nr+dr[j], nc+dc[j], cur[2] + 1});
                }
            }
        }

        return cnt;
    }
    // 범위 밖인지 검사
    static boolean check(int r, int c){
        return r >= 0 && c >= 0 && r < l && c < l;
    }
    // 수직인 방향인지 검사
    static boolean checkD(int d, int d2){
        if(d == 0 || d == 2)
            if(d2 == 1 || d2 == 3) return true;
        if(d == 1 || d == 3)
            if(d2 == 0 || d2 == 2) return true;
        return false;
    }
}