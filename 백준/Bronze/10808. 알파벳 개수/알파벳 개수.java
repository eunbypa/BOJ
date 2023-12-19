import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static String S;
    static int[] apb;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        int l = S.length();
        apb = new int[26];
        for (int i = 0; i < l; i++) {
            apb[S.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            sb.append(apb[i]);
            if(i == 25) break;
            sb.append(" ");
        }
        System.out.println(sb.toString());
    }



}