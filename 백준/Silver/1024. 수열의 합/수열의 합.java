import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int MAX_VAL = 100;
    static final int FAIL = -1;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        System.out.println(getAnswer(N, L));
    }

    static StringBuilder getAnswer(int N, int L) {
        StringBuilder sb = new StringBuilder();
        int a;

        for (int i = L; i <= MAX_VAL; i++) {
            if((2*N - i*i + i) % (2*i) != 0) continue;
            a = (2 * N - i * i + i) / (2 * i);
            if(a < 0) continue;
            for (int j = 0; j < i; j++) {
                sb.append(a + j);
                if(j == i-1) break;
                sb.append(" ");
            }
            return sb;
        }

        sb.append(FAIL);
        return sb;
    }



}