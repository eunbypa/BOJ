import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int X,Y;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        int min = -1;
        int Z = calculate(X,Y);
        int l = 0, r = (int)1e9, m;
        if(Z < 99) {
            while(l <= r) {
                m = (l + r) / 2;
                if(Z != calculate(X+m, Y+m)) {
                    min = m;
                    r = m - 1;
                }else {
                    l = m + 1;
                }
            }
        }
        System.out.println(min);
    }
    // Z를 계산하는 함수
    public static int calculate(int x, int y) {
        return (int) ((long) y * 100 / x);
    }
}