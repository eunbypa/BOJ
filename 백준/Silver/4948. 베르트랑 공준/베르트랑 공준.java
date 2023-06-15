import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int n, cnt, max = 2 * 123456;
    static int[] dp; // 각 숫자까지의 소수의 개수 저장
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        dp = new int[max+1];
        for (int i = 2; i <= max; i++) {
            if(isPrime(i)){
                dp[i] = dp[i-1]+1;
            }else dp[i] = dp[i-1];
        }
        n = Integer.parseInt(br.readLine()); // 테스트 케이스
        int s, e;
        while(n != 0){
            s = n;
            e = 2*n;
            cnt = dp[e] - dp[s]; // 2*n 까지의 소수 개수에서 n 까지의 소수 개수를 빼면 범위 내 소수의 개수가 나온다!
            sb.append(cnt+"\n");
            cnt = 0;
            n = Integer.parseInt(br.readLine());
        }

        System.out.println(sb.toString());
    }

    //에라토스테네스의 체
    private static boolean isPrime(int i) {
        int sqrt = (int)Math.sqrt(i);
        for (int j = 2; j <= sqrt; j++) {
            if(i % j == 0) return false;
        }
        return true;
    }


}