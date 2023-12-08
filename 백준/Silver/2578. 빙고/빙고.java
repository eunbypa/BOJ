import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static int[][] bingo;
    static int[][] loc;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[5][5];
        bingo = new int[5][5];
        loc = new int[26][2];
        StringTokenizer st;
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                loc[map[i][j]][0] = i;
                loc[map[i][j]][1] = j;
            }
        }
        int cnt = 0;
        int n;
        boolean fin = false;

        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                cnt++;
                n = Integer.parseInt(st.nextToken());
                bingo[loc[n][0]][loc[n][1]] = 1;
                if(checkBingo()) {
                    fin = true;
                    break;
                }
            }
            if(fin) break;
        }
        System.out.println(cnt);
    }

    // 빙고 여부 확인
    public static boolean checkBingo() {
        int line = 0;
        int cnt = 0;
        //가로
        for (int i = 0; i < 5; i++) {
            cnt = 0;
            for (int j = 0; j < 5; j++) {
                if(bingo[i][j] == 1) cnt++;
            }
            if(cnt == 5) line++;
        }
        //세로
        for (int i = 0; i < 5; i++) {
            cnt = 0;
            for (int j = 0; j < 5; j++) {
                if(bingo[j][i] == 1) cnt++;
            }
            if(cnt == 5) line++;
        }
        //대각선
        cnt = 0;
        for (int i = 0; i < 5; i++) {
            if(bingo[i][i] == 1) cnt++;
        }
        if(cnt == 5) line++;
        cnt = 0;
        for (int i = 0; i < 5; i++) {
            if(bingo[4-i][i] == 1) cnt++;
        }
        if(cnt == 5) line++;
        return line >= 3;
    }
}