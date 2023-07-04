import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
    private static StringBuilder sb = new StringBuilder();
    private static int M,N,x,y;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            // M*a + x = N*b + y 인 값을 찾는다
            // 그 값을 O라고 하면,
            // 0 - x 는 M으로 나눠 떨어져야 함
            // 0 - y 는 N으로 나눠 떨어져야 함
            int a, b, rem1, rem2;
            if(M >= N){
                a = M;
                b = N;
                rem1 = x;
                rem2 = y;
            }else{
                a = N;
                b = M;
                rem1 = y;
                rem2 = x;
            }
            int gcd = getGcd(a, b);
            int lcm = M*N/gcd; // 최소 공배수
            int cur = rem1; // N과 M중 큰값에 해당하는 나머지가 시작점이 됨
            while(cur <= lcm){
                if((cur >= rem2) && (cur-rem2) % b == 0) break; // 정답
                cur += a;
            }
            if(cur > lcm) cur = -1; // 표현 불가능함을 의미
            sb.append(cur + "\n");
        }
        System.out.println(sb.toString());
    }
    // 최대 공약수 구하는 함수
    static int getGcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return getGcd(b, a % b);
        }
    }
}