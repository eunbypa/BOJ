import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        dp = new int[21][21][21];
        int a, b, c;
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()) {
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            if(a == -1 && b == -1 && c == -1) break;
            sb.append("w(").append(a).append(", ").append(b).append(", ").append(c).append(") = ");
            sb.append(w(a, b, c)).append("\n");
            st = new StringTokenizer(br.readLine());
        }

        System.out.println(sb.toString());
    }

    // dp로 메모이제이션한 재귀
    static int w(int a, int b, int c){
        if(a <= 0 || b <= 0 || c <= 0) return 1;
        if(a > 20 || b > 20 || c > 20) return dp[20][20][20] = w(20, 20, 20);
        if(dp[a][b][c] != 0) return dp[a][b][c];
        if(a < b && b < c)
            return dp[a][b][c] = w(a, b, c - 1) + w(a, b - 1, c - 1) - w(a, b - 1, c);
        return dp[a][b][c] = w(a - 1, b, c) + w(a - 1, b - 1, c) + w(a - 1, b, c - 1) - w(a-1, b-1, c-1);
    }

}