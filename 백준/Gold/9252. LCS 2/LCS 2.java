import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static String A,B;
    static int[][] lcs;
    static char[] result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        A = br.readLine();
        B = br.readLine();
        lcs = new int[A.length()+1][B.length()+1];
        int l = A.length(), l2 = B.length();
        for (int i = 1; i <= l; i++) {
            for (int j = 1; j <= l2; j++) {
                if(A.charAt(i-1) == B.charAt(j-1)) {
                    lcs[i][j] = lcs[i - 1][j - 1] + 1;
                }else {
                    lcs[i][j] = Math.max(lcs[i][j - 1], lcs[i - 1][j]);
                }
            }
        }
        System.out.println(lcs[l][l2]);
        if(lcs[l][l2] != 0) {
            StringBuilder sb = new StringBuilder();
            int idx = lcs[l][l2];
            while (idx != 0) {
                if (lcs[l][l2-1] == lcs[l][l2])
                    l2--;
                else if(lcs[l-1][l2] == lcs[l][l2])
                    l--;
                else {
                    sb.append(A.charAt(l-1));
                    l--;
                    l2--;
                    idx--;
                }
            }
            System.out.println(sb.reverse().toString());
        }
    }

}