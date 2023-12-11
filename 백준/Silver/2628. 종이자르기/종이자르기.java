import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int r,c;
    static int[] rowCut;
    static int[] colCut;
    static int[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        c = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        rowCut = new int[r];
        colCut = new int[c];
        visited = new int[r][c];
        int N = Integer.parseInt(br.readLine());
        int type, num;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            type = Integer.parseInt(st.nextToken());
            num = Integer.parseInt(st.nextToken());
            if(type == 0) rowCut[num] = 1;
            else colCut[num] = 1;
        }
        int max = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if(visited[i][j] == 1) continue;
                max = Math.max(max, check(i, j));
            }
        }
        System.out.println(max);
    }

    public static int check(int cr, int cc) {
        int nr = cr+1, nc = cc+1;
        while(nr < r && rowCut[nr] == 0) nr++;
        while(nc < c && colCut[nc] == 0) nc++;
        for (int i = cr; i < nr; i++) {
            for (int j = cc; j < nc; j++) {
                visited[i][j] = 1;
            }
        }
        return (nr - cr) * (nc - cc);
    }

}