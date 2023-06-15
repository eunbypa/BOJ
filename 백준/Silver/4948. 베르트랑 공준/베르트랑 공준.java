import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int n, cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); // 테스트 케이스
        int s, e;
        while(n != 0){
            s = n+1;
            e = 2*n;
            for (int i = s; i <= e; i++) {
                if(isPrime(i)){ // 소수
                    cnt++;
                }
            }
            sb.append(cnt+"\n");
            cnt = 0;
            n = Integer.parseInt(br.readLine());
        }

        System.out.println(sb.toString());
    }

    //에라토스테네스의 체
    private static boolean isPrime(int i) {
        for (int j = 2; j*j <= i; j++) {
            if(i % j == 0) return false;
        }
        return true;
    }


}