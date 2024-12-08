import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int[] DR = {-1, 0, 1, 0};
    static final int[] DC = {0, -1, 0, 1};
    static final char EMPTY = '.';
    static final char WALL = '#';
    static final char FIRE = '*';
    static final char SANGEUN = '@';
    static final String FAIL = "IMPOSSIBLE";


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int w,h;
        int[] start;
        char[][] map;
        Queue<int[]> fireQ;

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            map = new char[h][w];
            start = new int[]{-1, -1};
            fireQ = new LinkedList<>();
            for (int i = 0; i < h; i++) {
                map[i] = br.readLine().toCharArray();
                for (int j = 0; j < w; j++) {
                    if(map[i][j] == FIRE) {
                        fireQ.add(new int[]{i, j});
                    }
                    if(map[i][j] != SANGEUN) continue;
                    start[0] = i;
                    start[1] = j;
                }
            }

            sb.append(getAnswer(fireQ, map, w, h, start));
            sb.append("\n");
        }

        System.out.println(sb);
    }

    // bfs 활용
    static StringBuilder getAnswer(Queue<int[]> fireQ, char[][] map, int w, int h, int[] start) {
        StringBuilder sb = new StringBuilder();
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {start[0], start[1], 0});
        int[][] visited = new int[h][w];
        visited[start[0]][start[1]] = 1;
        int[] cur;
        int nr, nc, turn = 0;

        while (!q.isEmpty()) {
            cur = q.poll();
            // 불 붙은 상태 갱신
            if(turn != cur[2]) {
                updateFire(fireQ, map, h, w);
                turn = cur[2];
            }

            // 현재 이동한 위치가 불이 붙은 곳이면 안됨
            if(map[cur[0]][cur[1]] == FIRE) continue;
            // 탈출할 수 있는 상황
            if(canEscape(map, h, w, cur[0], cur[1])) {
                sb.append(cur[2]+1);
                return sb;
            }

            for (int i = 0; i < 4; i++) {
                nr = cur[0] + DR[i];
                nc = cur[1] + DC[i];

                if(map[nr][nc] == WALL || map[nr][nc] == FIRE || visited[nr][nc] == 1) continue;
                visited[nr][nc] = 1;
                q.offer(new int[]{nr, nc, cur[2] + 1});
            }

        }

        sb.append(FAIL);
        return sb;
    }

    // 불 위치 갱신
    static void updateFire(Queue<int[]> fireQ, char[][] map, int h, int w) {
        int size = fireQ.size();
        int[] cur;
        for (int i = 0; i < size; i++) {
            cur = fireQ.poll();
            setFire(fireQ, map, h, w, cur);
        }
    }

    static void setFire(Queue<int[]> fireQ, char[][] map, int h, int w, int[] loc){
        int nr, nc;
        for (int i = 0; i < 4; i++) {
            nr = loc[0] + DR[i];
            nc = loc[1] + DC[i];

            if(isOut(nr, nc, h, w) || map[nr][nc] == WALL ||  map[nr][nc] == FIRE) continue;
            map[nr][nc] = FIRE;
            fireQ.add(new int[]{nr, nc});
        }
    }

    static boolean canEscape(char[][] map, int h, int w, int r, int c) {
        for (int i = 0; i < 4; i++) {
            if(isOut(r+DR[i], c+DC[i], h, w)) return true;
        }

        return false;
    }

    static boolean isOut(int r, int c, int h, int w) {
        return r < 0 || c < 0 || r >= h || c >= w;
    }

}