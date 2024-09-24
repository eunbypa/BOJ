import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final char SHEEP = 'S';
    static final char WOLF = 'W';
    static final char EMPTY = '.';
    static final char FENCE= 'D';


    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        char[][] map = new char[R][C];

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        System.out.println(getAnswer(R, C, map));
    }


    // 울타리를 놓아도 늑대가 양이 있는 칸으로 갈 수 있는지 확인
    static String getAnswer(int R, int C, char[][] map) {
        StringBuilder sb = new StringBuilder();
        int result = 1;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(map[i][j] != SHEEP) continue;
                if(isSheepAndWolfAttached(i, j, R, C, map)) {
                    result = 0;
                    break;
                }
            }
            if(result == 0) break;
        }

        sb.append(result).append("\n");
        // 울타리로 방어 가능한 경우만 목장 상태 출력
        if(result == 1) {
            for (int i = 0; i < R; i++) {
                sb.append(String.valueOf(map[i])).append("\n");
            }
        }
        return sb.toString();
    }
    
    // 양과 늑대가 인접하는지 확인
    static boolean isSheepAndWolfAttached(int r, int c, int R, int C, char[][] map) {
        int nr, nc;
        for (int i = 0; i < 4; i++) {
            nr = r + dr[i];
            nc = c + dc[i];
            if(isOut(nr, nc, R, C)) continue;
            if(map[nr][nc] == WOLF) return true;
            else if(map[nr][nc] == EMPTY) {
                // 비어있는 칸이면 울타리 설치
                map[nr][nc] = FENCE;
            }
        }
        return false;
    }

    // 맵을 벗어나는지 확인
    static boolean isOut(int r, int c, int R, int C) {
        return r < 0 || r >= R || c < 0 || c >= C;
    }

}