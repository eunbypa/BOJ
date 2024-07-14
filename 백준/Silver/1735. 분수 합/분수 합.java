import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int a, b;
        int[][] fractions = new int[2][2];
        for (int i = 0; i < 2; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            fractions[i][0] = a; // 분자
            fractions[i][1] = b; // 분모
        }
        a = fractions[0][0] * fractions[1][1] + fractions[1][0] * fractions[0][1];
        b = fractions[0][1] * fractions[1][1];
        // 분자와 분모의 최대 공약수 구해서 나눠서 기약분수 구하기
        int gcd = (a > b) ? getGCD(a, b) : getGCD(b, a);
        a /= gcd;
        b /= gcd;
        StringBuilder sb = new StringBuilder();
        sb.append(a).append(" ").append(b);
        System.out.println(sb);
    }

    // 유클리드 호제법 사용
    public static int getGCD(int a, int b) {
        int r = a % b;
        if(r == 0) return b;
        int tmp;
        while(b % r != 0) {
            tmp = b % r;
            b = r;
            r = tmp;
        }

        return r;
    }
}