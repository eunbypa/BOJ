import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static char[][] map;
    static int[][] visited;
    static int R,C;
    static int[] dr = {-1, 0, 1};
    static int[] dc = {1, 1, 1}; // 오른쪽 위, 오른쪽, 오른쪽 아래
    static boolean find;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new int[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }
        int cnt = 0;
        for (int i = 0; i < R; i++) {
            find = false;
            dfs(i, 0);
            if(find) cnt++;
        }

        System.out.println(cnt);
    }

    public static void dfs(int r, int c) {
        if(c == C-1) {
            find = true;
            return;
        }
        int nr, nc;
        for (int i = 0; i < 3; i++) {
            nr = r + dr[i];
            nc = c + dc[i];
            // 파이프로 이을 수 있는지 확인
            // 맵 밖으로 벗어나는 경우
            if(nr < 0 || nr >= R || nc >= C) continue;
            // 해당 위치에 건물이 있거나, 이미 놓은 파이프가 있는 경우
            if(map[nr][nc] == 'x' || visited[nr][nc] == 1) continue;
            visited[nr][nc] = 1;
            dfs(nr, nc);
            if(find) return;
        }
    }

}