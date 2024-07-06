import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int i = 1, cnt = 0;
        // 제곱수마다 창문이 열림(약수가 홀수)
        while(i*i <= N) {
            cnt++;
            i++;
        }
        System.out.println(cnt);
    }
}
