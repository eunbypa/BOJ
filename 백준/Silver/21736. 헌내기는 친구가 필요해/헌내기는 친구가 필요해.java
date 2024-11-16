import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final char DOYEON = 'I';
    static final char PERSON = 'P';
    static final char WALL = 'X';
    static final String FAILED = "TT";
    static final int[] DR = {-1, 0, 1, 0};
    static final int[] DC = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        char[][] map = new char[N][M];
        int[] doyeonLoc = {-1, -1};

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            if(doyeonLoc[0] != -1) continue;
            for (int j = 0; j < M; j++) {
                if(map[i][j] == DOYEON) {
                    doyeonLoc[0] = i;
                    doyeonLoc[1] = j;
                    break;
                }
            }
        }

        System.out.println(getAnswer(N, M, map, doyeonLoc));
    }

    // BFS
    static StringBuilder getAnswer(int N, int M, char[][] map, int[] doyeonLoc) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(doyeonLoc);
        int[][] visited = new int[N][M];
        visited[doyeonLoc[0]][doyeonLoc[1]] = 1;
        int[] cur;
        int pCnt = 0, nr, nc;
        while(!q.isEmpty()) {
            cur = q.poll();
            if(map[cur[0]][cur[1]] == PERSON) pCnt++;

            for (int i = 0; i < 4; i++) {
                nr = cur[0] + DR[i];
                nc = cur[1] + DC[i];
                if(nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
                if(map[nr][nc] == WALL || visited[nr][nc] == 1) continue;
                visited[nr][nc] = 1;
                q.offer(new int[]{nr, nc});
            }
        }

        StringBuilder sb = new StringBuilder();

        if(pCnt == 0) sb.append(FAILED);
        else sb.append(pCnt);

        return sb;
    }


}