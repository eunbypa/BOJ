import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());

        System.out.println(getAnswer(R, G));
    }

    // 1. R과 G의 최대공약수 구하기
    // 2. 최대공약수의 약수 구하기
    static StringBuilder getAnswer(int r, int g) {
        int gcd = r >= g ? getGCD(r, g) : getGCD(g, r);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i*i <= gcd; i++) {
            if(gcd % i != 0) continue;
            sb.append(i).append(" ").append(r / i).append(" ").append(g / i).append("\n");
            if(i*i == gcd) break;
            sb.append(gcd / i).append(" ").append(r / (gcd/i)).append(" ").append(g / (gcd/i)).append("\n");
        }

        return sb;
    }

    static int getGCD(int a, int b) {
        int tmp;
        while(a % b != 0) {
            tmp = a % b;
            a = b;
            b = tmp;
        }

        return b;
    }

}