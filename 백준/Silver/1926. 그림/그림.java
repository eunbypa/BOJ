import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n,m,cnt,max;
    static int[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(map[i][j] == 0) continue;
                cnt++; // 그림 개수 1 증가
                max = Math.max(max, bfs(i, j));
            }
        }
        sb.append(cnt + "\n" + max);
        System.out.println(sb.toString());
    }

    // flood-fill
    public static int bfs(int r, int c) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{r, c});
        map[r][c] = 0; // 방문 체크
        int[] cur;
        int nr, nc, area = 1;
        while(!q.isEmpty()){
            cur = q.poll();
            for (int i = 0; i < 4; i++) {
                nr = cur[0] + dr[i];
                nc = cur[1] + dc[i];
                if(!check(nr,nc) || map[nr][nc] == 0) continue;
                map[nr][nc] = 0;
                area++;
                q.offer(new int[]{nr, nc});
            }
        }
        return area;
    }

    // 영역 밖인지 검사하는 함수
    public static boolean check(int r, int c) {
        return r >= 0 && c >= 0 && r < n && c < m;
    }

}