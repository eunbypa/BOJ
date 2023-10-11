import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int tmp = N;
        int cnt = 0; // 자릿수
        while(tmp > 0) {
            tmp /= 10;
            cnt++;
        }
        long result = 0L;
        int ten = 10;
        for (int i = 1; i < cnt; i++) {
            result += i * (ten - ten/10);
            ten *= 10;
        }
        result += cnt * (N - ten / 10 + 1);
        System.out.println(result);
    }

}