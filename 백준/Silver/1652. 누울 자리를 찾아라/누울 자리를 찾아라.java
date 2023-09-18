import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }
        int row = 0, col = 0;
        // 가로 체크
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            cnt = 0;
            for (int j = 0; j < N; j++) {
                if(map[i][j] == '.') cnt++;
                else {
                    if(cnt >= 2){
                        row++;
                    }
                    cnt = 0;
                }
            }
            if(cnt >= 2){
                row++;
            }
        }
        // 세로 체크
        cnt = 0;
        for (int i = 0; i < N; i++) {
            cnt = 0;
            for (int j = 0; j < N; j++) {
                if(map[j][i] == '.') cnt++;
                else {
                    if(cnt >= 2){
                        col++;
                    }
                    cnt = 0;
                }
            }
            if(cnt >= 2){
                col++;
            }
        }
        System.out.println(row + " " + col);
    }

}