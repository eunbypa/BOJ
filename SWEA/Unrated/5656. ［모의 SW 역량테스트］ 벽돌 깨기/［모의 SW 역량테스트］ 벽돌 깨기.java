import java.util.*;
import java.io.*;
 
public class Solution {
    static int N, W, H, tot, min = 300;
    static int[][] map;
    static int[][] copy;
    static int[] selected;
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,-1,0,1};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T;
        T=Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++)
        {
            min = 300;
            tot = 0;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            map = new int[H][W];
            copy = new int[H][];
            selected = new int[N];
            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] != 0) tot++;
                }
            }
            dfs(0);
            sb.append("#"+test_case+" "+min+"\n");
        }
        System.out.println(sb.toString());
    }
    //W 개에서 N개를 중복으로 뽑는 중복순열
    static void dfs(int cnt) {
        if(min == 0) return; // 프루닝
        if(cnt == N) {
            int r = 0, c = 0, sum = 0;
            for (int i = 0; i < H; i++) {
                copy[i] = Arrays.copyOf(map[i], W);
            }
            for (int i = 0; i < N; i++) {
                r = 0;
                c = selected[i];
                while(r < H && copy[r][c] == 0) {
                    r++;
                }
                if(r == H) continue; // 벽돌이 아예 없는 열
                sum+=deleteBlock(r,c);
            }
            min = Math.min(min, tot-sum);
            return;
        }
        for (int i = 0; i < W; i++) {
            if(map[H-1][i] == 0) continue; // 벽돌이 없는 열
            selected[cnt] = i;
            dfs(cnt+1);
        }
    }
    //벽돌깨기 시작 bfs
    static int deleteBlock(int r, int c) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {r,c,copy[r][c]});
        copy[r][c] = 0;
        int nr,nc,length,cnt = 1;
        int[] cur;
        while(!q.isEmpty()) {
            cur = q.poll();
            for (int i = 0; i < 4; i++) {
                nr = cur[0];
                nc = cur[1];
                length = cur[2];
                for (int j = 0; j < length-1; j++) {
                    nr += dr[i];
                    nc += dc[i];
                    if(nr >= 0 && nc >= 0 && nr < H && nc < W && copy[nr][nc] != 0) {
                        q.offer(new int[] {nr,nc,copy[nr][nc]});
                        copy[nr][nc] = 0;
                        cnt++;
                    }
                }
            }
        }
        int j, idx, tmp;
        for (int i = 0; i < W; i++) {
            j = H-1;
            idx = H-1;
            while(j >= 0) {
                if(copy[j][i] == 0) {
                    while(j >= 0 && copy[j][i] == 0) {
                        j--;
                    }
                    if(j < 0) break;
                    tmp = copy[j][i];
                    copy[j][i] = 0;
                    copy[idx--][i] = tmp;
                }else {
                    tmp = copy[j][i];
                    copy[j][i] = 0;
                    copy[idx--][i] = tmp;
                }
                j--;
            }
        }
        return cnt;
    }
}