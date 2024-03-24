import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static int[][] dListByG;
    static int[] dr = {0, -1, 0, 1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[101][101];
        dListByG = new int[11][1024];
        StringTokenizer st;
        int x,y,d,g;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            setDragonCurve(x, y, d, g);
        }
        int cnt = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                // 정사각형 체크
                if(isPartOfDragonCurve(i,j)) cnt++;
            }
        }
        System.out.println(cnt);
    }

    // 4 꼭짓점이 모두 드래곤 커브에 포함되는지 검사
    private static boolean isPartOfDragonCurve(int i, int j) {
        return (map[i][j] == 1) && (map[i+1][j] == 1)
                && (map[i][j+1] == 1) && (map[i+1][j+1] == 1);
    }

    // 드래콘 커브 세팅
    private static void setDragonCurve(int x, int y, int d, int g) {
        setDListByG(d, g);
        map[y][x] = 1;
        int size = getSize(g);
        int nx = x, ny = y;
        for (int i = 0; i < size; i++) {
            ny += dr[dListByG[g][i]];
            nx += dc[dListByG[g][i]];
            map[ny][nx] = 1;
        }
    }

    private static int getSize(int g) {
        int n = 1;
        for (int i = 0; i < g; i++) {
            n *= 2;
        }
        return n;
    }

    // 세대에 따른 드래곤 커브 구성 방향 리스트 세팅
    private static void setDListByG(int d, int g) {
        // 시작 방향
        dListByG[g][0] = d;
        int rem = g, cur = 1, idx = 1;
        while(rem-- > 0) {
            for (int i = cur-1; i >= 0; i--) {
                dListByG[g][idx++] = rotate(dListByG[g][i]);
            }
            cur *= 2;
        }
    }

    // 방향 회전
    private static int rotate(int d) {
        return (d+1)%4;
    }


}