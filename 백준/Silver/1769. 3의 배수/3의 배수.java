import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static char[] X;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        X = br.readLine().toCharArray();
        int sum = 0, cnt = 0;
        int l = X.length;
        // 1자리 제외
        if(l != 1) cnt++;
        for (int i = 0; i < l; i++) {
            sum += X[i]-'0';
        }
        int tmp;
        while (sum >= 10) {
            cnt++;
            tmp = sum;
            sum = 0;
            while(tmp > 0) {
                sum += tmp % 10;
                tmp /= 10;
            }
        }
        sb.append(cnt).append("\n");
        if(sum == 3 || sum == 6 || sum == 9) {
            sb.append("YES");
        }else {
            sb.append("NO");
        }
        System.out.println(sb.toString());
    }

}