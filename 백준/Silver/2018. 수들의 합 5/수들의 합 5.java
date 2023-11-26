import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int ans = 1, cnt = 1;
        int b = 0;
        boolean fail = false;
        while(b < N) {
            b += cnt;
            if(b >= N) break;
            cnt++;
            if((N - b) % cnt == 0) ans++;
        }
        System.out.println(ans);
    }

}