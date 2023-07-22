import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.math.BigInteger;

public class Main {
    static int N;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        if(N <= 20) hanoi(N, 1, 2, 3);
        BigInteger cnt = new BigInteger("2");
        // n이 100인 경우 long의 범위를 초과
        System.out.println(cnt.pow(N).subtract(new BigInteger("1")));
        if(N <= 20) System.out.println(sb.toString());
    }

    // 하노이 탑
    public static void hanoi(int cur, int from, int mid, int to) {
        if(cur == 1){
            sb.append(from + " " + to + "\n");
        }else{ 
            hanoi(cur-1, from, to, mid);
            // 출력
            sb.append(from + " " + to + "\n");
            hanoi(cur-1, mid, from, to);
        }
    }

}