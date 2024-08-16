import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final String TRUE = "OK";
    static final String FAKE = "FAKE";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        String M;
        for (int t = 0; t < T; t++) {
            M = br.readLine();
            if(isTrue(M)) sb.append(TRUE);
            else sb.append(FAKE);
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public static boolean isTrue(String s) {
        int l = s.length();
        int[] apbCntArr = new int[26];
        for (int i = 0; i < l; i++) {
            apbCntArr[s.charAt(i) - 'A']++;
            if(apbCntArr[s.charAt(i) - 'A'] == 3) {
                if(i != l-1 && s.charAt(i) == s.charAt(i+1)) {
                    apbCntArr[s.charAt(i) - 'A'] = 0;
                    i++;
                    continue;
                }
                return false;
            }
        }
        return true;
    }

}