import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static String S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        int l = S.length();
        int min = l;
        int cnt = 0, cnt2 = 0;
        // 0으로 맞출 경우
        for (int i = 0; i < l; i++) {
            if(S.charAt(i) == '0') {
                if(cnt != 0) cnt2++;
                cnt = 0;
                continue;
            }
            cnt++;
        }
        if(cnt != 0) cnt2++;
        min = cnt2;
        cnt = 0;
        cnt2 = 0;
        // 1으로 맞출 경우
        for (int i = 0; i < l; i++) {
            if(S.charAt(i) == '1') {
                if(cnt != 0) cnt2++;
                cnt = 0;
                continue;
            }
            cnt++;
        }
        if(cnt != 0) cnt2++;
        min = Math.min(min, cnt2);
        System.out.println(min);
    }
}