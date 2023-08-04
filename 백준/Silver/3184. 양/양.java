import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static char[][] map;
    static int R,C;
    static int wolf, sheep;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }
        // 마당을 탈출할 수 있는 곳 제외(테두리)
        int er = R-1, ec = C-1;
        for (int i = 1; i < er; i++) {
            for (int j = 1; j < ec; j++) {
                if(map[i][j] == '#' || visited[i][j]) continue;
                bfs(i, j);
            }
        }
        System.out.println(sheep +" "+wolf);
    }
    // 너비 우선 탐색
    public static void bfs(int r, int c) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{r, c});
        visited[r][c] = true;
        int[] cur;
        int nr, nc;
        int s = 0, w = 0; // 영역 내 양, 늑대 수
        while(!q.isEmpty()){
            cur = q.poll();
            if(map[cur[0]][cur[1]] == 'v') w++;
            if(map[cur[0]][cur[1]] == 'o') s++;
            for (int i = 0; i < 4; i++) {
                nr = cur[0] + dr[i];
                nc = cur[1] + dc[i];
                if(!check(nr,nc) || map[nr][nc] == '#' || visited[nr][nc]) continue;
                visited[nr][nc] = true;
                q.offer(new int[]{nr, nc});
            }
        }
        if(s > w) sheep += s; // 양의 승리
        else wolf += w; // 늑대의 승리
    }
    // 영역 벗어나는지 확인
    public static boolean check(int r, int c) {
        return r >= 0 && c >= 0 && r < R && c < C;
    }
}