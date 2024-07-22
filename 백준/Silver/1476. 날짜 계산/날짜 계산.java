import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int E,S,M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        E = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int y = S;
        while(!findAnswer(y)) {
            y += 28;
        }
        System.out.println(y);
    }

    public static boolean findAnswer(int y) {
        int e = y % 15, s = y % 28, m = y % 19;
        if(y % 15 == 0) e = 15;
        if(y % 28 == 0) s = 28;
        if(y % 19 == 0) m = 19;
        return e == E && s == S && m == M;
    }
}