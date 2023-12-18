import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, kjm, ihs;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        kjm = Integer.parseInt(st.nextToken());
        ihs = Integer.parseInt(st.nextToken());
        int cnt = 1;
        while(!isMeet()) {
            cnt++;
            N = (N%2 == 0) ?  N/2 : N/2 + 1;
            kjm = (kjm%2 == 0) ? kjm/2 : kjm/2 + 1;
            ihs = (ihs%2 == 0) ? ihs/2 : ihs/2 + 1;
        }
        System.out.println(cnt);
    }

    // 둘이 만났는지 확인
    public static boolean isMeet() {
        return ((kjm % 2 == 1 && ihs % 2 == 0) && (kjm + 1 == ihs)) ||
                ((kjm % 2 == 0 && ihs % 2 == 1) && (kjm  == ihs + 1));
    }

}