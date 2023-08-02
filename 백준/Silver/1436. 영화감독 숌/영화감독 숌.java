import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int ans = 666;
        int cnt = 1;
        while(cnt != N){
            ans++;
            if(check(ans)) cnt++;
        }
        System.out.println(ans);
    }

    // 종말의 수인지 확인
    public static boolean check(int n) {
        int idx = 0;
        int cnt = 0;
        while(n > 0){
            idx = n % 10;
            if(idx == 6) cnt++;
            else cnt = 0;
            if(cnt == 3) return true;
            n /= 10;
        }
        return false;
    }
}