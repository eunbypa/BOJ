import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int APB_CNT = 26;
    static final char A = 'A';

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1 = br.readLine();
        String s2 = br.readLine();

        int l1 = s1.length(), l2 = s2.length();
        int[][] dp = new int[l1+1][l2+1];
        int ans = 0;

        for (int i = 1; i <= l1; i++) {
            for (int j = 1; j <= l2; j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1] + 1;
                ans = Math.max(dp[i][j], ans);
            }
        }

        System.out.println(ans);
    }


}