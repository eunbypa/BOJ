import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N, cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 1; i <= N; i++) {
            if(isSuccess(i)) cnt++;
        }
        System.out.println(cnt);
    }

    // 등차수열인지 확인하는 함수
    public static boolean isSuccess(int n) {
        int idx = n % 10;
        int diff = 10;
        n /= 10;
        while(n > 0) {
            if(diff == 10){
                diff = idx - (n % 10);
            }else{
                if(diff != (idx - (n % 10))) return false;
            }
            idx = n % 10;
            n /= 10;
        }
        return true;
    }
}