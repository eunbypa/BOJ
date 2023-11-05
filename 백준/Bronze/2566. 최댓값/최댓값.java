import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[9][9];
        StringTokenizer st;
        int max = -1;
        StringBuilder sb = new StringBuilder();
        int r = 0, c = 0;
        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] > max) {
                    max = map[i][j];
                    r = i+1;
                    c = j+1;
                }
            }
        }
        sb.append(max).append("\n").append(r).append(" ").append(c).append("\n");
        System.out.println(sb.toString());
    }

}